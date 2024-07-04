package com.restassured.api.gson.method;

import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CreateBookingUsingGson {

	@Test
	public void addBookings() {

		RequestSpecification requestSpecification = RestAssured.given();
		Response response;
		ValidatableResponse validatableResponse;

		String baseUrl = "https://restful-booker.herokuapp.com";
		String basePath = "/booking";

		Booking booking = new Booking();
		booking.setFirstname("Thomas");
		booking.setLastname("Harry");
		booking.setTotalprice(10000);
		booking.setDepositpaid(true);

		BookingDates bookingDates = new BookingDates();
		bookingDates.setCheckin("2024-01-10");
		bookingDates.setCheckout("2024-01-11");
		booking.setBookingdates(bookingDates);
		booking.setAdditionalneeds("Breakfast");

		Gson gson = new Gson();
		String jsonPayload = gson.toJson(booking);

		// given

		requestSpecification.baseUri(baseUrl);
		requestSpecification.basePath(basePath);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.body(jsonPayload);

		// when
		response = requestSpecification.when().log().all().post();

		// then
		validatableResponse = response.then().log().all();
		validatableResponse.statusCode(200);
	}
}
