package AutisMono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AutisMono.dao.PatientRepository;
import AutisMono.entity.Medecin;
import AutisMono.entity.Patient;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Edition des catégories, sans gestion des erreurs
 */
@Controller
@RequestMapping(path = "/patient")
public class PatientController {

    @Autowired
    private PatientRepository dao;

    /**
     * Affiche toutes les catégories dans la base
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('DonnesPatient.html')
     */
    @GetMapping(path = "show")
    public String afficheToutesLesDonnées(Model model) {
        //model.addAttribute("patients", dao.findById(3));
        return "DonneesPatient";
    }
    
    @GetMapping(path = "showAll")
    public String afficheTousLesPatients(@AuthenticationPrincipal Medecin medecin, Model model) {
        model.addAttribute("patients", dao.findAll());
        return "listePatients";
    }

    /**
     * Montre le formulaire permettant d'ajouter un patient
     *
     * @param patient initialisé par Spring, valeurs par défaut à afficher dans le formulaire
     * @return le nom de la vue à afficher ('formulairePatient.html')
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjout(@ModelAttribute("patient") Patient patient) {
        return "formulairePatient";
    }

    /**
     * Appelé par 'formulairePatient.html', méthode POST
     *
     * @param patient Une galerie initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des galeries
     */
    @PostMapping(path = "save")
    public String ajouteDonnéePuisMontreLaListe(Patient patient, RedirectAttributes redirectInfo) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            dao.save(patient);
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "Le patient '" + patient.getNom() + "' a été correctement enregistré";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE' 
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : Le patient '" + patient.getNom() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'DonneesPatient.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
    }

    /**
     * Appelé par le lien 'Supprimer' dans 'DonnesPatient.html'
     *
     * @param patient à partir de l'id de la galerie transmis en paramètre, Spring fera une requête SQL SELECT pour
     * chercher le patient dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des patients
     */
    @GetMapping(path = "delete")
    public String supprimeUneDonnéePuisMontreLaListe(@RequestParam("id") Patient patient, RedirectAttributes redirectInfo) {
        String message = "Le patient '" + patient.getNom() + "' a bien été supprimée";
        try {
            dao.delete(patient); // Ici on peut avoir une erreur (Si il y a des expositions pour cette galerie par exemple)
        } catch (DataIntegrityViolationException e) {
            // violation de contrainte d'intégrité si on essaie de supprimer une galerie qui a des expositions
            message = "Erreur : Impossible de supprimer le patient '" + patient.getNom() ;
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'DonneesMedecin.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }
    
    @GetMapping(path = "contact")
    public String montreLeContactMedecin(@ModelAttribute("patient") Patient patient) {
        return "contact";
    }
    
    @GetMapping(path = "suivi")
    public String montreLeSuivi(@ModelAttribute("patient") Patient patient) {
        return "suivi";
    }
}