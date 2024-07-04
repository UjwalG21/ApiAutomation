package com.restassured.api.base.data;

import org.testng.annotations.Test;

public class BaseUrls {

	@Test(groups = { "smoke" })
	public void testA() {
		System.out.println("This is smoke A");
	}

	@Test(groups = { "smoke" })
	public void testB() {
		System.out.println("This is smoke B");
	}

	@Test(priority = 0)
	public void testC() {
		System.out.println("C");
	}
}
