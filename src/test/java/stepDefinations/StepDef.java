package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.TestDataBuilder;
import resources.Utils;

public class StepDef extends Utils{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuilder data = new TestDataBuilder();
	
		@Given("Add place payload")
		public void add_place_payload() throws IOException {
			 res = given().spec(RequestSpecification()).body(data.addPlacePayload());
		}

		@When("user called {string} using Post http call")
		public void user_called_using_post_http_call(String string) {
			resspec = new ResponseSpecBuilder().expectStatusCode(200)
					.expectContentType(ContentType.JSON).build();
			response = res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
			
			String responseString = response.asString();
			System.out.println(responseString);
		    
		}
		@Then("api call get success with status code as {int}")
		public void api_call_get_success_with_status_code_as(Integer int1) {
		    assertEquals(response.getStatusCode(), 200);
		
		}
		@Then("{string} response code is {string}")
		public void response_code_is(String key, String value) {
			String res =response.asString();
			JsonPath js = new JsonPath(res);
			assertEquals(js.get(key).toString(), value);
			
		   
		}
		@Then("{string} is response body is {string}")
		public void is_response_body_is(String keyValue, String expected) {
			String res =response.asString();
			JsonPath js = new JsonPath(res);
			assertEquals(js.get(keyValue).toString(), expected);
		}




}
