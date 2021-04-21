package AutisMono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import AutisMono.dao.MedecinRepository;
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

    //Affiche les données personnelles du médecin
    @GetMapping(path = "show")
    public String afficheToutesLesDonnées(Model model) {
        return "DonneesMedecin";
    }
   
   //Affiche le nom du médecin traitant dans contact
   @GetMapping(path = "showAll")
    public String afficheLesMedecins(@AuthenticationPrincipal Patient patient, Model model) {
        model.addAttribute("medecin",dao.findByNom(patient.getNommedecin()));
        return "contact";
    }
}
