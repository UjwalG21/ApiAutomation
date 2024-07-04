package com.restassured.api.base.data;

import org.testng.annotations.Test;

public class TestNgMethods {
	@Test
	public void testA() {
		System.out.println("i'll run first");
	}

	@Test(dependsOnMethods = "testA")
	public void testB() {
		System.out.println("This is smoke B");
	}
}
