package stepDefinations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataProvider.ConfigFileReader;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SaveDataFileSteps {
	
		ConfigFileReader cf=new ConfigFileReader();
		private Response response;
		private RequestSpecification httpRequest;

		private String baseURL = cf.getApplicationUrl();
		
		@Given("^I send request to the API$")
		public void SendReq()
		{   
			RestAssured.baseURI = baseURL;
			httpRequest = RestAssured.given();
		}
		
		@When("^Recieve response from API$")
		public void verifyResponse()
		{
			response = httpRequest.request(Method.GET, "/allahabad");
			int statusCode = response.getStatusCode();
			if(statusCode==200)
				System.out.println("API retuns the status of 200");
		}
		
		@Then("^Save data to the File$")
		public void saveData() throws IOException {

			  String TestFile = "D:\\temp.txt";
			  File FC = new File(TestFile);//Created object of java File class.
			  FC.createNewFile();//Create file.
			  
			  //Writing In to file.
			  //Create Object of java FileWriter and BufferedWriter class.
			  FileWriter FW = new FileWriter(TestFile);
			  BufferedWriter BW = new BufferedWriter(FW);
			  BW.write(response.asString());
			  BW.close();
		}
		
		
		
}
