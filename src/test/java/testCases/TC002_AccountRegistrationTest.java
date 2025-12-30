package testCases;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC002_AccountRegistrationTest extends BaseClass{

	
	@Test
	public void verify_account_registration()
	{
		try
		{
		logger.info("Starting TC002 - AccountRegistrationTesr");
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickRegister();
		
		AccountRegistrationPage reg =new AccountRegistrationPage(driver);
		
		logger.info("Updating customer information");

		reg.setFirstName(randomeString().toUpperCase());
		reg.setLastName(randomeString().toUpperCase());
		reg.setEmail(randomeString()+"@gmail.com");
		reg.setTelephone(randomNumber());
		
		String password = randomAlphaNum();
		reg.setPassword(password);
		reg.setConfirmPassword(password);
		reg.setcheckPolicy();
		reg.setClickButton();
		
		logger.info("validating expected message");

		String confmsg = reg.getConfirmationMsg();
		AssertJUnit.assertEquals(confmsg, "Your Account Has Been Created!");

		}
		catch(Exception e)
		{
			logger.error("Test failed");
			logger.debug("Debugs logs");
			Assert.fail();
		}
		
		logger.info("*****Finished******");
	}
	

}
