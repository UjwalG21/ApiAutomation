package com.restassured.api.crud.operations.bddstyle;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.restassured.RestAssured;

public class GetRequestBdd {
	@Description("getRequestUsingBdd")
	@Test
	public void getRequestUsingBDD() {

		RestAssured.given().baseUri("https://restful-booker.herokuapp.com").basePath("/booking/250").when().log().all()
				.get().then().log().all().statusCode(200);
	}

}
