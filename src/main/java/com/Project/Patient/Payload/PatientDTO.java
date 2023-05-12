package com.Project.Patient.Payload;
// used for Data Transfer 

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.Project.Patient.Entity.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor

@Setter
@Getter

public class PatientDTO {

	 private long id;
	 
	 @NotEmpty
	 @NotBlank(message = "patient should not be blank")
	 @Size(min=4,message="name must be more than 4 characters")
	  private String name;
	 
	 
	 
	 @NotEmpty
	 @Size(min=5,max=10,message="password must be less than 10 chars and more than 5chars")
	 
	 private String password;
	 
	 @NotEmpty
	 private String  address;
	 
	 @Email(message="Entered email address is not valid")
	 private String email;

	 private List<Appointment> appointments=new ArrayList<>();
	 
	 @JsonIgnore
	 public String getPassword() {
		 return this.password;
	 }
	 
	 @JsonProperty
	 public void setPassword(String password) {
		 this.password=password;
	 }
	 
	 
}