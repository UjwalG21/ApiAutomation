package com.restassured.api.crud.operations.nonbddstyle;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class DeleteRequestNonBdd {

	RequestSpecification requestSpecification = RestAssured.given();
	Response response;
	ValidatableResponse validatableResponse;
	String baseUrl = "https://restful-booker.herokuapp.com";
	String basePath = "/booking";
	String bookingId = "/147";
	String newBasePath = basePath + bookingId;
	String token = "7ca39246dd57fe8";

	@Test
	public void deleteBookingUsingNonBdd() {
		// given
		requestSpecification.baseUri(baseUrl);
		requestSpecification.basePath(newBasePath);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.cookie("token", token);
		requestSpecification.log().all();
		// when
		response = requestSpecification.when().log().all().delete();

		// then
		validatableResponse = response.then();
		validatableResponse.statusCode(201);

	}
}
