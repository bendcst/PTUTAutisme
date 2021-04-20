package AutisMono.service;

import AutisMono.entity.Medecin;
import AutisMono.entity.Patient;
import AutisMono.entity.Utilisateur;

public interface UserService {
    void initializeRolesAndAdmin();

    void savepatient(Patient pat);
    void savemedecin(Medecin med);

    Utilisateur findByUserName(String username);
}