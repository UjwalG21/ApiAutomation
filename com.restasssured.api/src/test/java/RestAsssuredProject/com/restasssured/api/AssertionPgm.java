package RestAsssuredProject.com.restasssured.api;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Description;

public class AssertionPgm {
	@Description("Program for soft assertion")
	@Test
	public void checkSoftAsesertion() {

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals("Ujwal", "ujwal", "Equal");
		System.out.println("End of pgm");
		softAssert.assertAll();
	}

	@Description("Program for hard assertion")
	@Test
	public void checkHardAsesertion() {

		Assert.assertEquals("Ujwal", "ujwal", "Equal");
		System.out.println("End of pgm");
	}
}
