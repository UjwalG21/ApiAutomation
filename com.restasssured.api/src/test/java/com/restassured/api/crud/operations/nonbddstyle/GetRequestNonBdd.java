package com.restassured.api.crud.operations.nonbddstyle;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GetRequestNonBdd {

	RequestSpecification specification = RestAssured.given();
	Response response;
	// ResponseBody body;
	String baseUri = "https://restful-booker.herokuapp.com";
	String basePath = "/booking";

	@Description("getRequestUsingNonBddStyles")
	@Test
	public void getRequestUsingNonBdd() {
		// given
		specification.baseUri(baseUri);
		specification.basePath(basePath);
		// when
		response = specification.when().log().all().get();
		// then
		specification.then().log().all().statusCode(200);
		// Print response
		// body = response.getBody();
		String responseBody = response.asString();
		System.out.println(responseBody);
		// print response using jsonpath
		JsonPath jsonPath = new JsonPath(responseBody);
		String firstName = jsonPath.getString("firstname");
		String lastname = jsonPath.getString("lastname");
		System.out.println(firstName);
		System.out.println(lastname);
	}

}
