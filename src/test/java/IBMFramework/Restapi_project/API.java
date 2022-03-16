package IBMFramework.Restapi_project;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import Pojo.pojoclass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API {
	
	@Test(enabled=false)
	public void testcase1() {
		
		Response res=RestAssured.get("http://localhost:3000/training");
		String responsebody=res.asString();
		//System.out.println("responsebody");
		System.out.println(res.getStatusCode());//print status code
		System.out.println(res.getStatusLine());// print status code entire line
		System.out.println(res.getHeaders());//print the headers
		System.out.println(res.jsonPath().getString("body"));
		
	}

	
	@Test(enabled=false)
	public void tetcase2() {
		//String a ="1";
		RestAssured.baseURI="http://localhost:3000";
		//RestAssured.given().get("/training").then().statusCode(200).log().all();
		RestAssured.given().get("/training").then().statusCode(201).log().body();
			RestAssured.given().get("/training").then().statusCode(201).log().all();
		//RestAssured.given().contentType(ContentType.JSON).queryParam("body", "abc").when().get("/training"+a).then().statusCode(200).log().all();
		RestAssured.given().contentType(ContentType.JSON).queryParam("body", "abc").when().get("/training").then().statusCode(200).log().all();
		
	}
	@Test(enabled=false)
		public void tetcase3() {
			RestAssured.baseURI="https://petstore.swagger.io/v2/";
			RestAssured.given().get("/user/login").then().statusCode(200).log().all();
		RestAssured.given().contentType(ContentType.JSON).queryParam("username", "abcd").queryParam("password", "abc123").when().get("/user/login").then().statusCode(200).log().all();
		RestAssured.given().contentType(ContentType.JSON).queryParam("username", "cdef").queryParam("password", "scd123").when().get("/user/login").then().statusCode(200).log().all();

	}
	
	@Test
	public void post(){
	  RestAssured.baseURI  = "https://petstore.swagger.io/v2/"; 
	  Response res = RestAssured.given()
	     .contentType("application/json")
	     .body("{\"username\":\"string\",\"firstName\":\"string\",\"lastName\":\"string\",\"email\":\"string\",\"password\":\"string\",\"phone\":\"string\",}")
	     .when()
	     .post("");
	  String body = res.getBody().asString();
	  System.out.println(body);
	}
	
	
	@Test(enabled=false)//json object exapmle
public void testcase4() {
	JSONObject obj=new JSONObject();
	obj.put("id", 42567);
	obj.put("name", "xya");
	obj.put("status", "availabe");
	System.out.println(obj.toJSONString());
	
	JSONObject category=new JSONObject();
	category.put("id", 42);
	category.put("name", "abc");
	System.out.println(category.toJSONString());
	
	
}
	@Test(enabled=false)//json object exapmle
	public void	testcase5() {
		RestAssured.baseURI="http://localhost:3000/";
	//	given().contentType(ContentType.JSON).when().post("/training").then().statusCode(201).log().all();
	}
	
	
	@Test(enabled=false)
	public void Jsontostring() {
		
		RestAssured.baseURI="http://localhost:3000/";
		String reqbody="{\"id\":2,\"body\":\"xyzabc\",\"postId\":55}";
		RestAssured.given().contentType(ContentType.JSON).body(reqbody).when().post("/training").then().log().all();
	}

	@Test(enabled=false)
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
	
	
	
	
