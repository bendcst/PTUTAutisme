package AutisMono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import AutisMono.dao.PatientRepository;
import AutisMono.entity.Medecin;
import AutisMono.entity.Patient;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Edition des catégories, sans gestion des erreurs
 */
@Controller
@RequestMapping(path = "/patient")
public class PatientController {

    @Autowired
    private PatientRepository dao;

  //Affiche les données personnelles du patient
    @GetMapping(path = "show")
    public String afficheToutesLesDonnées(Model model) {
        return "DonneesPatient";
    }
    //Affiche la liste des patients pris en charge par un médecin
    @GetMapping(path = "showAll")
    public String afficheTousLesPatients(@AuthenticationPrincipal Medecin medecin, Model model) {
        model.addAttribute("patients", dao.findByNommedecin(medecin.getNom()));
        return "listePatients";
    }
    
    //Affiche le contact du médecin
    @GetMapping(path = "contact")
    public String montreLeContactMedecin(@ModelAttribute("patient") Patient patient) {
        return "contact";
    }
    
    //Affiche le suivi du patient
    @GetMapping(path = "suivi")
    public String montreLeSuivi(@ModelAttribute("patient") Patient patient) {
        return "suivi";
    }
    
    //Affiche le suivi d'un patient
     @GetMapping(path = "suiviVuMed")
    public String montreLeSuivi1Patient(@ModelAttribute("patient") Patient patient) {
        return "suiviVuMed";
    }
}