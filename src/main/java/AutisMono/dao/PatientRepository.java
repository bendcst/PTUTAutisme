package AutisMono.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import AutisMono.entity.Patient;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring 

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByNommedecin (String nommedecin);
}