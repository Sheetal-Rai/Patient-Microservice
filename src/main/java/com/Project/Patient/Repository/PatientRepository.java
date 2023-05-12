package com.Project.Patient.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.Patient.Entity.Patient;


public interface PatientRepository extends JpaRepository<Patient, Long> {
	
Optional<Patient> findByEmail(String email);


}

