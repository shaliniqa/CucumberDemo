package stepDefinations;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Steps {

	private Response response;
	private RequestSpecification httpRequest;

	private String baseURL = "http://restapi.demoqa.com/utilities/weather/city";

	@Given("^User hits the given API Url$")
	public void GetWeatherDetails()
	{   
		RestAssured.baseURI = baseURL;
		httpRequest = RestAssured.given();
	}

	@When("^User request for a particular city by get method$")
	public void getCityInfo()
	{
		response = httpRequest.request(Method.GET, "/Hyderabad");
		System.out.println("User hits the API");
	}

	@Then("^Status code is 200$")
	public void checkStatus()
	{
		int statusCode = response.getStatusCode();
		if(statusCode==200)
			System.out.println("API retuns the status of 200");
		 
	}
	@And("^Response is of City \"([^\"]*)\"$")
	public void checkCity(String cityName)
	{
		JsonPath jsonPathEvaluator = response.jsonPath();
		 String responseCity = jsonPathEvaluator.get("City");
		 if(cityName.equals(responseCity))
			 System.out.println("Right City being recived");
	}

}
