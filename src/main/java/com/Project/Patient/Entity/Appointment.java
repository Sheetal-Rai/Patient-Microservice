package com.Project.Patient.Entity;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
private Long appointment_id;
	
	
private Long patientid;



private Long doctorid;

	
	

	private Date appoint_date;
	
	
	private String slot;
}
