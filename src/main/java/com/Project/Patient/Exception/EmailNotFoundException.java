package com.Project.Patient.Exception;

public class EmailNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private String  fieldValue;
	
	
	public EmailNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s hI, not found with %s : '%s'",resourceName, fieldName,fieldValue));
	
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public String getResourceName() {
		return resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public String getFieldValue() {
		return fieldValue;
}}
