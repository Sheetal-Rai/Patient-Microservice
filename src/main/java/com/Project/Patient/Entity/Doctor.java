package com.Project.Patient.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
private long id;
	
	
	private String name;
	
	
	private String email;
	
	
	private String password;
	
	
	private String address;
}
