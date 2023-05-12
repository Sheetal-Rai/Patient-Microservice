package com.Project.Patient.Controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project.Patient.Entity.Patient;
import com.Project.Patient.Payload.JwtAuthRequest;
import com.Project.Patient.Payload.JwtAuthResponse;
import com.Project.Patient.Payload.PatientDTO;
import com.Project.Patient.Security.JwtTokenHelper;
import com.Project.Patient.Service.PatientService;

@RestController
@RequestMapping("/api/v1/auth/")
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ModelMapper modelMapper;
	
@Autowired
private PatientService patientService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest request 
			) throws Exception{
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails= this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token= this.jwtTokenHelper.generateToken(userDetails);
		
		
		
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		response.setPatient(this.modelMapper.map((Patient)userDetails,PatientDTO.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String email, String password) throws Exception {
//		
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(email, password);
//		
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("invalid Details!");
			throw new Exception("invalid huhu");
		}
	
}
	
	
	//register new patient

	@PostMapping("/signUp")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientDTO patientDTO){
		PatientDTO newuser=this.patientService.createPatient(patientDTO);
		return new ResponseEntity<PatientDTO>(newuser,HttpStatus.CREATED);
	}
}
