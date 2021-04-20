package AutisMono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AutisMono.entity.Medecin;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import AutisMono.dao.MedecinRepository;

/**
 * Edition des catégories, sans gestion des erreurs
 */
@Controller
@RequestMapping(path = "/medecin")
public class MedecinController {

    @Autowired
    private MedecinRepository dao;

    /**
     * Affiche toutes les catégories dans la base
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('afficheGaleries.html')
     */
    @GetMapping(path = "show")
    public String afficheToutesLesDonnées(Model model) {
        model.addAttribute("p", dao.findAll());
        return "DonneesMedecin";
    }

    /**
     * Montre le formulaire permettant d'ajouter une galerie
     *
     * @param professionnel
     * @return le nom de la vue à afficher ('formulaireGalerie.html')
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjout(@ModelAttribute("professionnel") MedecinController professionnel) {
        return "formulaireMedecin";
    }

    
    /*@GetMapping(path = "showAll")
    public String afficheLesPatients(Model model) {
        return "listePatients";
    }*/
    /**
     * Appelé par 'formulaireGalerie.html', méthode POST
     *
     * @param professionnel Une galerie initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des galeries
     */
    @PostMapping(path = "save")
    public String ajouteDonnéePuisMontreLaListe(Medecin professionnel, RedirectAttributes redirectInfo) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            dao.save(professionnel);
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "Le professionnel de santé '" + professionnel.getNom() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE' 
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : Le professionnel de santé '" + professionnel.getNom() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheGalerie.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
    }

    /**
     * Appelé par le lien 'Supprimer' dans 'afficheGaleries.html'
     *
     * @param professionnel à partir de l'id de la galerie transmis en paramètre, Spring fera une requête SQL SELECT pour
     * chercher la galerie dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des galeries
     */
    
    @GetMapping(path = "delete")
    public String supprimeUneDonnéePuisMontreLaListe(@RequestParam("id") Medecin professionnel, RedirectAttributes redirectInfo) {
        String message = "Le professionnel de santé '" + professionnel.getNom() + "' a bien été supprimé";
        try {
            dao.delete(professionnel); // Ici on peut avoir une erreur (Si il y a des expositions pour cette galerie par exemple)
        } catch (DataIntegrityViolationException e) {
            // violation de contrainte d'intégrité si on essaie de supprimer une galerie qui a des expositions
            message = "Erreur : Impossible de supprimer le professionnel de santé '" + professionnel.getNom() ;
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheGalerie.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }
    
    @GetMapping(path = "showAll")
    public String afficheLesMedecins(Model model) {
        model.addAttribute("medecin", dao.findAll());
        return "contact";
    }
}
