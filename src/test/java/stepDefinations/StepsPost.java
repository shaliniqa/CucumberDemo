package stepDefinations;

import org.json.simple.JSONObject;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepsPost {
	
	private Response response;
	private RequestSpecification request;

	private String baseURL = "http://restapi.demoqa.com/customer";
	
	@Before()
	public void check()
	{
		System.out.println("post enter");
	}
	
	@Given("^User hit the given API$")
	public void sendRequest()
	{
		RestAssured.baseURI =baseURL;
		request = RestAssured.given();
	}
	
	@When("^User post the particular request$")
	public void prepareRequest()
	{
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender"); 
		requestParams.put("LastName", "Singh");
		 
		requestParams.put("UserName", "simpleuser001");
		requestParams.put("Password", "password1");
		requestParams.put("Email",  "someuser@gmail.com");
		
		request.header("Content-Type", "application/json");
		 
		request.body(requestParams.toJSONString());
		 
		response = request.post("/register");
	}
	
	@Then("^Validate Status Code$")
	public void validateResponse()
	{
		 int statusCode = response.getStatusCode();
		 System.out.println("The status code recieved: " + statusCode);
		 
		 
	}
	@And("^The response recieved$")
	public void PrintResponse()
	{
		System.out.println("Response body: " + response.body().asString());
	}
	
	@After()
	public void PostFinish()
	{
		System.out.println("post finished");
	}
	
}

