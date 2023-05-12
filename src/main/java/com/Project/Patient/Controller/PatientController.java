package com.Project.Patient.Controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Project.Patient.Entity.Appointment;
import com.Project.Patient.Payload.PatientDTO;
import com.Project.Patient.Payload.apiResponse;
import com.Project.Patient.Service.PatientService;



@RestController
@RequestMapping("/api/patientt") //baseURL

public class PatientController {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	 private PatientService patientService;
	
	//so that we don't expose the patient directly
	//post
	@PostMapping("/")
	
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientDTO patientDTO)
	{
    	PatientDTO createPatientDTO = this.patientService.createPatient(patientDTO);
    	return new ResponseEntity<>(createPatientDTO, HttpStatus.CREATED);
    }
	
	

	@PostMapping("/addappointment")
	@CrossOrigin(origins = "http://localhost:3000")
	public String addAppointment(@RequestBody Appointment app) {
		return restTemplate.postForObject("http://localhost:8888/api/appointment/created"
				, app, String.class);
		
	} 
	
	
	@DeleteMapping("/deleteAppointment/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public apiResponse deleteAppointment(@PathVariable Long id) {
		Map<String,Long> param=new HashMap<String,Long>();
		param.put("p_id", id);
	restTemplate.delete("http://localhost:8888/api/appointment/{p_id}",param);
	 return new apiResponse("user Deleted",true);
	}
	

	//update-PUT
	@PutMapping("/{patientId}")
	
	public ResponseEntity<PatientDTO> updatePatient(@Valid @RequestBody PatientDTO patientDTO, @PathVariable Long patientId)
	{
		
		PatientDTO updatedPatient =this.patientService.updatePatient(patientDTO, patientId);
		return ResponseEntity.ok(updatedPatient);
	}
	
	//delete
	@DeleteMapping("/{patientId}")
	public ResponseEntity<apiResponse> deletePatient(@PathVariable("patientId")Long pid){
		this.patientService.deletePatient(pid);
		return new ResponseEntity<apiResponse>(new apiResponse("user Deleted",true),HttpStatus.OK);
	}
	
	
	//get - all patients
	@GetMapping("/")
	public ResponseEntity<List<PatientDTO>> getAllPatient(){
		return ResponseEntity.ok(this.patientService.getAllPatient());
	}
	
	//for single user 
	@GetMapping("/{patientId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<PatientDTO> getSinglePatient(@PathVariable Long patientId){
		return ResponseEntity.ok(this.patientService.getPatientById(patientId));
	}
	
	//fetch all doctors
	
	@GetMapping("/fetch")
	public ArrayList fetchAllDoc() {
		return restTemplate.exchange("http://localhost:9999/api/doctors",
				HttpMethod.GET,null,ArrayList.class).getBody();
	}
	
	
	//get all specialzations
	@GetMapping("/specialization")
	public ArrayList fetchAllSpecialization() {
		return restTemplate.exchange("http://localhost:9999/api/specialization/", 
				HttpMethod.GET,null,ArrayList.class).getBody();
	}
	
	
	
	

}
