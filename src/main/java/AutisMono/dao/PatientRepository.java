package AutisMono.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import AutisMono.entity.Patient;

// This will be AUTO IMPLEMENTED by Spring 

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}