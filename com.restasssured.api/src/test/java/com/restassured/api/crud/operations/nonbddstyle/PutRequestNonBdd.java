package com.restassured.api.crud.operations.nonbddstyle;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class PutRequestNonBdd {

	RequestSpecification specification = RestAssured.given();
	Response response;
	ValidatableResponse validatableResponse;
	String baseUrl = "https://restful-booker.herokuapp.com";
	String basePath = "/booking";
	String bookingId = "/226";
	String newBasePath = basePath + bookingId;
	String token = "04c05f190494b9d";

	@Test
	public void putRequestUsingNonBdd() {

		// given
		String payload = "{\r\n" + "    \"firstname\" : \"John\",\r\n" + "    \"lastname\" : \"Oliver\",\r\n"
				+ "    \"totalprice\" : 12098,\r\n" + "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2024-01-10\",\r\n" + "        \"checkout\" : \"2024-01-10\"\r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Breakfast,Dinner,Lunch\"\r\n" + "}";

		specification.baseUri(baseUrl);
		specification.basePath(newBasePath);
		specification.contentType(ContentType.JSON);
		specification.cookie("token", token);
		specification.body(payload);
		// when
		response = specification.log().all().put();

		// then
		validatableResponse = response.then();
		validatableResponse.statusCode(200);
		validatableResponse.body("firstname", Matchers.equalTo("John"));
		validatableResponse.body("lastname", Matchers.equalTo("Oliver"));

	}
}
