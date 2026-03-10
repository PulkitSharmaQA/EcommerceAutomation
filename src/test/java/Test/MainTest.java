package Test;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import Base.BaseTest;
import pages.ProductPage;
import pages.loginValidation;

public class MainTest extends BaseTest {
	
	@Test()
	public void loginTest() throws InterruptedException {
		// Create Account 
		loginValidation login = new loginValidation(driver);
		String validUserName=login.signIn( login.randomName() + "9@yahoo.com");
		login.userDetails();
		Assert.assertTrue(login.accountCreationDetail().contains("Congratulations! Your new account has been successfully created!"));
		Assert.assertEquals(validUserName,login.validateUserName());
		

	}
	
	


}
