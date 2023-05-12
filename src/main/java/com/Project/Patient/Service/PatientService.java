package com.Project.Patient.Service;

import java.util.ArrayList;
import java.util.List;

import com.Project.Patient.Entity.Doctor;
import com.Project.Patient.Payload.PatientDTO;

public interface PatientService {
	 
	PatientDTO createPatient(PatientDTO patient);
	
	PatientDTO updatePatient(PatientDTO patient, Long id);
	
	PatientDTO getPatientById(Long id);
	
	List<PatientDTO> getAllPatient();
	
	void deletePatient (Long id);



	
}

