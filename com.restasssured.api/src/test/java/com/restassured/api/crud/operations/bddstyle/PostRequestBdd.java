package com.restassured.api.crud.operations.bddstyle;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostRequestBdd {

	@Description("Post request using bdd style")

	@Test
	public void postRquestUsingBdd() {

		String baseUri = "https://restful-booker.herokuapp.com";
		String basePath = "/booking";
		String payload = "{\r\n" + "    \"firstname\" : \"James\",\r\n" + "    \"lastname\" : \"Brownba\",\r\n"
				+ "    \"totalprice\" : 1113,\r\n" + "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2024-01-10\",\r\n" + "        \"checkout\" : \"2024-01-10\"\r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}";

		RestAssured.given().baseUri(baseUri).basePath(basePath).contentType(ContentType.JSON).log().all().body(payload)
				.when().post().then().log().all().statusCode(200);
	}

}
