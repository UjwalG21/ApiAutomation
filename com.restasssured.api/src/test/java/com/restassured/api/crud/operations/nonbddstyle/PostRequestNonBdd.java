package com.restassured.api.crud.operations.nonbddstyle;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class PostRequestNonBdd {

	RequestSpecification specification = RestAssured.given();
	Response response;
	ValidatableResponse validatableResponse;
	String baseUri = "https://restful-booker.herokuapp.com";
	String basePath = "/booking";

	@Description("Extract booking id")
	@Test
	public void postRequestUsingNonBddStyle() {

		String payload = "{\r\n" + "    \"firstname\" : \"James\",\r\n" + "    \"lastname\" : \"Brownba\",\r\n"
				+ "    \"totalprice\" : 1113,\r\n" + "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2024-01-10\",\r\n" + "        \"checkout\" : \"2024-01-10\"\r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}";

		// given

		specification.baseUri(baseUri);
		specification.basePath(basePath);
		specification.contentType(ContentType.JSON).log().all();
		specification.body(payload);

		// when
		response = specification.when().log().all().post();
		String postResponse = response.asString();
		System.out.println(postResponse);

		// then
		validatableResponse = response.then();
		validatableResponse.statusCode(200);

	}

}
