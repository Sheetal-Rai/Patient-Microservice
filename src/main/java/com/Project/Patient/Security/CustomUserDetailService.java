package com.Project.Patient.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Project.Patient.Entity.Patient;
import com.Project.Patient.Exception.EmailNotFoundException;
import com.Project.Patient.Repository.PatientRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	 @Autowired
	private PatientRepository patientRepo;
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading user from DB by user
		
	Patient patient=this.patientRepo.findByEmail(username).orElseThrow(()->new EmailNotFoundException("patient ", "email: ",username));
		
		
		
		return patient;
	}

}
