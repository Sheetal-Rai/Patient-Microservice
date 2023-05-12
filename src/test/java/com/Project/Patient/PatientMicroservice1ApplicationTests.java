package com.Project.Patient;
import org.json.*;  
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.Project.Patient.Payload.JwtAuthResponse;

import  static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;


@SpringBootTest
@SuppressWarnings("unused")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class PatientMicroservice1ApplicationTests {

	
	
	
	
	@Test
	@Order(1)
	public void testSignUp() {
	String jsonBody= "{\"name\":\"test\", \"address\":\"Sample\", \"email\":\"xyz@gmail.com\", \"password\":\"123sample\"}";
	String response=given()
			.header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(jsonBody)
			.when()
			.post("http://localhost:8989/api/v1/auth/signUp")
			.then()
			.assertThat().statusCode(201)
			.extract().response().asString();
		
	}
	
	
	@Test
	@Order(2)
  public void testLogin() {
		String jsonBody="{ \"username\":\"sheetal@gmail.com\", \"password\":\"qwert\"}";
		String token=given()
				.header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonBody)
				.when()
				.post("http://localhost:8989/api/v1/auth/login")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString();
				
				
	}

	
	@Test
    @Order(3)
    public void testGetPatients() {
        
       
        String result = given()
        		 .when()
	                .get("http://localhost:8989/api/patientt/fetch")
	                .then()
	                .assertThat().statusCode(200)
	                .extract().response().asString() ;
    }
	
	
	@Test
	@Order(4)
	public void testGetDoctorList() {
		 String res = given()
	                .when()
	                .get("http://localhost:8989/api/patientt/")
	                .then()
	                .assertThat().statusCode(200)
	                .extract().response().asString() ;
		
	}
	
	@Test
	@Order(5)
	public void testSinglePatient() {
		String res= given()
				.when()
                .get("http://localhost:8989/api/patientt/1")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString() ;
	}
	
	@Test
	@Order(6)
	public void testDeletePatient() throws JSONException  {
		   String jsonBody="{ \"username\":\"sheetal@gmail.com\", \"password\":\"qwert\"}";
	       String tokenn =  given()
	               .header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
	               .body(jsonBody)
	               .when()
	               .post("http://localhost:8989/api/v1/auth/login")
	               .then()
	               .assertThat().statusCode(200)
	               .extract().response().asString(); 
	       
	       System.out.println(tokenn);
	       
	       JSONObject json;
	       json = new JSONObject(tokenn);
			
			System.out.println(json.getString("token"));
	      
	       
	       String result = given()
	               .header("Authorization", "Bearer " + json.getString("token")).contentType(ContentType.JSON).accept(ContentType.JSON)
	               .when()
	               .delete("http://localhost:8989/api/patientt/3")
	               .then()
	               .assertThat().statusCode(200)
	               .extract().response().asString() ;
	}
	
	
	
	
	
	
	

}
