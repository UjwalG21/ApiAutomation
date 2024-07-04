package com.restassured.api.base.data;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class DataFeedUsingMap {

	@Test
	public void providePayloadUsingMap() {

		Map<String, Object> jsonMap = new LinkedHashMap<String, Object>();

		Faker faker = new Faker();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		Integer totalPrice = faker.random().nextInt(0, 20000);
		boolean depositpaid = faker.random().nextBoolean();

		jsonMap.put("firstname", firstName);
		jsonMap.put("lastname", lastName);
		jsonMap.put("totalprice", totalPrice);
		jsonMap.put("depositpaid", depositpaid);

		Map<String, Object> jsonMapDate = new LinkedHashMap<String, Object>();
		String checkInDate = "2024-01-10";
		String checkOutDate = "2024-02-10";

		jsonMapDate.put("checkin", checkInDate);
		jsonMapDate.put("checkout", checkOutDate);

		jsonMap.put("bookingdates", jsonMapDate);

		jsonMap.put("additionalneeds", "breakfast");

		// System.out.println(jsonMap);

		// create booking
		RequestSpecification requestSpecification = RestAssured.given();
		Response response;
		ValidatableResponse validatableResponse;
		String baseUrl = "https://restful-booker.herokuapp.com";
		String basePath = "/booking";
		// given
		requestSpecification.baseUri(baseUrl);
		requestSpecification.basePath(basePath);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.body(jsonMap);

		// when
		response = requestSpecification.when().log().all().post();

		// then
		validatableResponse = response.then().log().all();
		validatableResponse.statusCode(200);

	}
}
