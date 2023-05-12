package com.Project.Patient.Service.Impl;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;import com.Project.Patient.Entity.Appointment;

import com.Project.Patient.Entity.Patient;
import com.Project.Patient.Exception.ResourceNotFound;
import com.Project.Patient.Payload.PatientDTO;
import com.Project.Patient.Repository.PatientRepository;
import com.Project.Patient.Service.PatientService;

@Service

public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//CRUD operations 
	@Override
	public PatientDTO createPatient(PatientDTO patientDTO) {

		Patient patient= this.modelMapper.map(patientDTO, Patient.class);
		patient.setPassword(this.passwordEncoder.encode(patient.getPassword()));

	    Patient savedPatient =this.patientRepo.save(patient);
		return this.patientToDTO(savedPatient);
		
//		Patient patient =this.dtoToPatient(patientDTO);
//		Patient savedPatient =this.patientRepo.save(patient);
//		return this.patientToDTO(savedPatient);
	}
	
	

	
	
	

	
	
	
	
	
	

	@Override
	public PatientDTO updatePatient(PatientDTO patientDTO, Long p_id) {
		
		Patient patient =this.patientRepo.findById(p_id)
				.orElseThrow(()->new ResourceNotFound("Patient","id",p_id));
		
      patient.setName(patientDTO.getName());
      patient.setAddress(patientDTO.getAddress());
      patient.setEmail(patientDTO.getEmail());
      patient.setPassword(patientDTO.getPassword());
      
      Patient updatedPatient = this.patientRepo.save(patient);
      PatientDTO patientDTO1 =this.patientToDTO(updatedPatient);
		return patientDTO1;
	}

	
	//getting appointments 
	@Override
	public PatientDTO getPatientById(Long p_id) {
		Patient patient= this.patientRepo.findById(p_id).orElseThrow(()->new ResourceNotFound("patient","id", p_id));
		
		// fetch appointments of the above user 
		//http://localhost:8888/api/appointment/patient/4
		ArrayList<Appointment > result=restTemplate.
		getForObject(
				"http://localhost:8888/api/appointment/patient/"+patient.getId(),ArrayList.class);
		
		patient.setAppointments(result);
		
		
		return this.patientToDTO(patient);
	}
	

	@Override
	public List<PatientDTO> getAllPatient() {
	List<Patient> patients=	this.patientRepo.findAll();
	
	List<PatientDTO> patientDTOs= patients.stream().map(patient->this.patientToDTO(patient)).collect(Collectors.toList());
	
	return  patientDTOs;
	}

	@Override
	public void deletePatient(Long p_id) {
		Patient patient=this.patientRepo.findById(p_id).orElseThrow(()->new ResourceNotFound("patient", "id", p_id));
		this.patientRepo.delete(patient);
		
		
	}
	
	
	//below we see that to conversion form on object to another is done manually 
	
	private Patient dtoToPatient(PatientDTO patientDTO) {
		Patient patient = this.modelMapper.map(patientDTO, Patient.class);
		//we are using modelmapper and converting the dto to entity 
		
//		patient.setId(patientDTO.getId());
//		patient.setFirst_name(patientDTO.getFirst_name());
//		patient.setLast_name(patientDTO.getLast_name());
//		patient.setPassword(patientDTO.getPassword());
//		patient.setAddress(patientDTO.getAddress());
//		patient.setEmail(patientDTO.getEmail());
		return patient;
		
	}
	
	public PatientDTO patientToDTO(Patient patient)
	{
		PatientDTO patientDTO=this.modelMapper.map(patient, PatientDTO.class);
		
//		patientDTO.setId(patient.getId());
//		patientDTO.setFirst_name(patient.getFirst_name());
//		patientDTO.setLast_name(patient.getFirst_name());
//		patientDTO.setPassword(patient.getPassword());
//		patientDTO.setAddress(patient.getAddress());
//		patientDTO.setEmail(patient.getEmail());
		return patientDTO;
	}}