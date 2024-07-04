package RestAsssuredProject.com.restasssured.api;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.restassured.RestAssured;

public class DemoTestNg {

	// all apis in bddd style - i.e given when then
	@Description("Retrive All pings using get method")
	@Test
	public void getAllPings() {
		RestAssured.given().baseUri("https://restful-booker.herokuapp.com").basePath("/ping").when().get().then()
				.statusCode(201);
	}

	@Description("Retrive All bookings using get method")
	@Test
	public void getAllBookings() {
		RestAssured.given().baseUri("https://restful-booker.herokuapp.com").basePath("/booking").when().log().all()
				.then().log().all().statusCode(200);
	}

	@Description("Retrive All bookings using get method")
	@Test
	public void getAllBookingss() {
		RestAssured.given().baseUri("https://restful-booker.herokuapp.com").basePath("/booking").when().get().then()
				.statusCode(200);
	}
}