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
import AutisMono.dao.PatientRepository;
import AutisMono.entity.Patient;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

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
     * @return le nom de la vue à afficher ('donnéesMedecin.html')
     */
    @GetMapping(path = "show")
    public String afficheToutesLesDonnées(Model model) {
        model.addAttribute("p", dao.findAll());
        return "DonneesMedecin";
    }

    /**
     * Montre le formulaire permettant d'ajouter un medecin
     *
     * @param medecin
     * @return le nom de la vue à afficher ('formulaireMedecin.html')
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjout(@ModelAttribute("medecin") MedecinController medecin) {
        return "formulaireMedecin";
    }

    /**
     * Appelé par 'formulaireMedecin.html', méthode POST
     *
     * @param medecin Une galerie initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des galeries
     */
    @PostMapping(path = "save")
    public String ajouteDonnéePuisMontreLaListe(Medecin medecin, RedirectAttributes redirectInfo) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            dao.save(medecin);
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "Le medecin '" + medecin.getNom() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE' 
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : Le medecin '" + medecin.getNom() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'Donneesmedecin.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
    }

    /**
     * Appelé par le lien 'Supprimer' dans 'afficheMedecin.html'
     *
     * @param medecin à partir de l'id de la galerie transmis en paramètre, Spring fera une requête SQL SELECT pour
     * chercher le medecin dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des medecins
     */
    
    @GetMapping(path = "delete")
    public String supprimeUneDonnéePuisMontreLaListe(@RequestParam("id") Medecin medecin, RedirectAttributes redirectInfo) {
        String message = "Le medecin '" + medecin.getNom() + "' a bien été supprimé";
        try {
            dao.delete(medecin); // Ici on peut avoir une erreur (Si il y a des expositions pour cette galerie par exemple)
        } catch (DataIntegrityViolationException e) {
            // violation de contrainte d'intégrité si on essaie de supprimer un medecin qui a des expositions
            message = "Erreur : Impossible de supprimer le medecin '" + medecin.getNom() ;
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheMedecin.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }
    
   @GetMapping(path = "showAll")
    public String afficheLesMedecins(@AuthenticationPrincipal Patient patient, Model model) {
        model.addAttribute("medecin",dao.findByNom(patient.getNommedecin()));
        return "contact";
    }
}
