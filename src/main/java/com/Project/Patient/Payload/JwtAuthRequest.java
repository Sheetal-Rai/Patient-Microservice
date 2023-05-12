package com.Project.Patient.Payload;
import lombok.Data;

@Data
public class JwtAuthRequest {

	
	private String username;
	
	private String password;
}
