package com.restassured.api.crud.operations.nonbddstyle;

import static org.assertj.core.api.Assertions.assertThat;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CreateIds {

	RequestSpecification requestSpecification = RestAssured.given();
	Response response;
	ValidatableResponse validatableResponse;
	String baseUri = "https://restful-booker.herokuapp.com";
	String basePath = "/auth";
	String postPath = "/booking";
	String token;
	Integer bookingid;
	String firstName;

	@Test(priority = 0)
	public void generateToken() {
		System.out.println("running genrating token......");
		String payload = "{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\" : \"password123\"\r\n" + "}";

		// given
		requestSpecification.baseUri(baseUri);
		requestSpecification.basePath(basePath);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.body(payload);

		// when
		response = requestSpecification.when().log().all().post();

		// then
		validatableResponse = response.then();
		validatableResponse.statusCode(200);

		// extract token
		token = response.then().log().all().extract().path("token");
		Assert.assertNotNull(token);
		System.out.println("Generated Token:" + token);
	}

	@Test(priority = 1)
	public void generateBookingId() {
		System.out.println("running genrating bookingId......");
		String payload = "{\r\n" + "    \"firstname\" : \"Alfred\",\r\n" + "    \"lastname\" : \"Leo\",\r\n"
				+ "    \"totalprice\" : 20654,\r\n" + "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2024-01-10\",\r\n" + "        \"checkout\" : \"2025-10-10\"\r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"dinner\"\r\n" + "}";

		// given
		requestSpecification.baseUri(baseUri);
		requestSpecification.basePath(postPath);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.body(payload);

		// when
		response = requestSpecification.when().log().all().post();

		// then
		validatableResponse = response.then();
		validatableResponse.statusCode(200);

		// extract token
		bookingid = response.then().log().all().extract().path("bookingid");
		firstName = response.then().log().all().extract().path("booking.firstname");
		System.out.println("Generated BookingId:" + bookingid);
		System.out.println("Generated FirstName:" + firstName);
		Assert.assertNotNull(bookingid);
		Assert.assertNotNull(firstName);
	}

	@Test(priority = 2)
	public void updateBooking() {
		System.out.println("running update booking......");
		String newBasePath = postPath + "/" + bookingid;

		// given
		String payload = "{\r\n" + "    \"firstname\" : \"David\",\r\n" + "    \"lastname\" : \"William\",\r\n"
				+ "    \"totalprice\" : 30126,\r\n" + "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2024-08-20\",\r\n" + "        \"checkout\" : \"2025-01-10\"\r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}";

		requestSpecification.baseUri(baseUri);
		requestSpecification.basePath(newBasePath);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.cookie("token", token);
		requestSpecification.body(payload);

		// when
		response = requestSpecification.log().all().put();

		// then
		validatableResponse = response.then();
		validatableResponse.statusCode(200);

		// print response
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);

		// 1st way to validate response i.e using matchers
		validatableResponse.body("firstname", Matchers.equalTo("David"));
		validatableResponse.body("lastname", Matchers.equalTo("William"));

		// 2nd way to validate response i.e using TestNg assert (using method extract)
		String fnameResponse = response.then().log().all().extract().path("firstname");
		Assert.assertEquals(fnameResponse, "David");

		// 3rd way to validate response i.e.TestNg Assertion with jsonpath
		JsonPath jsonPath = new JsonPath(jsonResponse);
		String jsonPathFname = jsonPath.getString("firstname");
		String jsonPathLname = jsonPath.getString("lastname");
		Integer jsonPathTotal = jsonPath.getInt("totalprice");
		System.out.println("Firstname:" + jsonPathFname + "LAstname:" + jsonPathLname + "Total:" + jsonPathTotal);
		Assert.assertEquals(jsonPathFname, "David");
		Assert.assertEquals(jsonPathLname, "William");
		Assert.assertEquals(jsonPathTotal, 30126);

		// 4th way to validate response i.e assertJ
		assertThat(jsonPathFname).isNotNull().isNotBlank().isEqualTo("David");

	}
}
