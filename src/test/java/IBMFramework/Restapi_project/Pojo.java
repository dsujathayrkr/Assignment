package IBMFramework.Restapi_project;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Pojo.pojoclass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Pojo {
	
	
	
	@Test
	public void pojoexample() throws Throwable {
		
	pojoclass pojoobject=new pojoclass();
	pojoobject.setAddress("xylane");
	pojoobject.setCity("Bangalore");
	pojoobject.setEmployeename("sujatha");
	
	System.out.println(pojoobject.getAddress());
	System.out.println(pojoobject.getCity());
	System.out.println(pojoobject.getEmployeename());
	
	ObjectMapper obj=new ObjectMapper();
	
	String body= obj.writerWithDefaultPrettyPrinter().writeValueAsString(pojoobject);
	System.out.println(body);
	
	
	RestAssured.baseURI="http://localhost:3000";
	RestAssured.given().contentType(ContentType.JSON).body(body).when().post("/training").then().statusCode(201).log().all();
}
}