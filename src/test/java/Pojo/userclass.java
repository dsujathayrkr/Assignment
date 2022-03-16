package Pojo;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class userclass {
	

	@Test(priority=2)
	public void Createuser() throws Throwable {
		
		Newpojoproject object=new Newpojoproject();
		object.setUsername("name123");
		object.setFirstname("user123");		
		object.setLastname("last123");
		object.setPassword("password1234");
		object.setEmail("test123@email.com");
		object.setPhoneno("12347890");
	
	System.out.println(object.getUsername());
	System.out.println(object.getFirstname());
	System.out.println(object.getLastname());
	System.out.println(object.getPassword());
	System.out.println(object.getEmail());
	System.out.println(object.getPhoneno());
	
	ObjectMapper obj=new ObjectMapper();
	
	String body= obj.writerWithDefaultPrettyPrinter().writeValueAsString(object);
	System.out.println(body);
	
	
	RestAssured.baseURI="https://petstore.swagger.io/v2";
	RestAssured.given().contentType(ContentType.JSON).body(body).when().post("/user").then().statusCode(200).log().all();
	
	  System.out.println(body);
	}
	
	
		
	  @Test(priority=1)
		public void login(){
	  
	  {
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			RestAssured.given().get("/user/login").then().statusCode(200).log().all();
		RestAssured.given().contentType(ContentType.JSON).queryParam("username", "abcd").queryParam("password", "abc123").when().get("/user/login").then().statusCode(200).log().all();
		//RestAssured.given().contentType(ContentType.JSON).queryParam("username", "cdef").queryParam("password", "scd123").when().get("/user/login").then().statusCode(200).log().all();

	}
	  }
		
			
		@Test(priority=3)
		public void Logout(ITestContext field) 
		{
			RestAssured.baseURI = "https://petstore.swagger.io/v2";
			Response responsebj =	RestAssured.given().contentType(ContentType.JSON).when().get("/user/logout").then().statusCode(200).log().all().extract().response();
						
			int code = responsebj.jsonPath().getInt("code");
			String message = responsebj.jsonPath().getString("message");
			Assert.assertEquals(code, 200);
			Assert.assertEquals(message, "ok");
			System.out.println("User logged in Successfully" );
		}
		
		@Test(dependsOnMethods = {"Logout"})
		public void DeleteUser(ITestContext field)
		{
			String username = (String) field.getAttribute("username");
			RestAssured.baseURI = "https://petstore.swagger.io/v2";
			Response responsebj =	RestAssured.given().contentType(ContentType.JSON).when().delete("/user/" +username).then().statusCode(200).log().all().extract().response();
			int code = responsebj.jsonPath().getInt("code");
			String message = responsebj.jsonPath().getString("message");
			Assert.assertEquals(code, 200);
			Assert.assertEquals(message, username);	
			System.out.println("User deleted Successfully" );
		}
		
}