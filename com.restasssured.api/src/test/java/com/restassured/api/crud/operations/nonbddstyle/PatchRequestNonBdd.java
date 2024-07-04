package com.restassured.api.crud.operations.nonbddstyle;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class PatchRequestNonBdd {

	RequestSpecification specification = RestAssured.given();
	Response response;
	ValidatableResponse validatableResponse;
	String baseUrl = "https://restful-booker.herokuapp.com";
	String basePath = "/booking";
	String bookingId = "/335";
	String newBasePath = basePath + bookingId;
	String token = "09daf8b24c5725c";

	@Test
	public void patchRequestUsingNonBdd() {

		String payload = "{\r\n" + "    \"firstname\" : \"James\",\r\n" + "    \"lastname\" : \"Charlie\",\r\n"
				+ "    \"totalprice\" : 12098,\r\n" + "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2024-01-10\",\r\n" + "        \"checkout\" : \"2024-01-10\"\r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Breakfast,Dinner,Lunch\"\r\n" + "}";

		// given
		specification.baseUri(baseUrl);
		specification.basePath(newBasePath);
		specification.contentType(ContentType.JSON);
		specification.cookie("token", token);
		specification.body(payload);
		specification.log().all();

		// when
		response = specification.log().all().patch();

		// then
		validatableResponse = response.then().log().all();
		validatableResponse.statusCode(200);

	}

}
