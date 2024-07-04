package com.restassured.api.crud.operations.nonbddstyle;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetByIdRequestNonBdd {
	RequestSpecification specification = RestAssured.given();
	Response response;
	String baseUri = "https://restful-booker.herokuapp.com";
	String basePath = "/booking";
	String bookingId = "/301";
	String updatedUrl = basePath + bookingId;

	@Description("getRequestUsingNonBddStyles")
	@Test
	public void getByIDRequestUsingNonBdd() {

		// given
		specification.baseUri(baseUri);
		specification.basePath(updatedUrl);

		// when
		response = specification.when().log().all().get();

		// then
		specification.then().log().all().statusCode(200);

		// print response

		String responseBody = response.asString();
		System.out.println(responseBody);
		JsonPath jsonPath = new JsonPath(responseBody);
		String firstName = jsonPath.getString("firstname");
		String lastName = jsonPath.getString("lastname");
		System.out.println(firstName);
		System.out.println(lastName);
	}
}
