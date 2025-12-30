package testCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.apache.logging.log4j.core.appender.rolling.action.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

/*
Data is valid - login success - test pass - logout
Data is valid - login failed - test fail 

Data is invalid - login success - test failed - logout
Data is invalid - login failed - test pass
*/

public class TC004_LoginDDT extends BaseClass {

	/*
	@DataProvider(name = "LoginData")
	public Object[][] getData() {
	    return new Object[][] {
	        { "valid@email.com", "validPwd", "valid" },
	        { "valid@email.com", "wrongPwd", "Invalid" },
	        { "wrong@email.com", "validPwd", "Invalid" }
	    };
	}

*/

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    //dataProviderClass = DataProviders.class)


	public void verify_LoginDDT(String email, String pwd, String exp)
	{
		
		logger.info("************* Starting of TC004_LoginDDT ************* ");		
		
		try {
		//HomePage
		
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		//Login
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAddress(email);
		lp.setPassword(pwd);
		
		 // 🔹 Debug info before clicking login
	    logger.info("Email: " + email + " | Password: " + pwd + " | Expected: " + exp);
	    
		lp.click_Login();
		
		// ✅ WAIT FOR LOGIN RESULT
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		boolean targetPage;
		
		//MyAccountPage
		MyAccountPage mac = new MyAccountPage(driver);
		targetPage = mac.isMyAccountPageExists();
		
	    // 🔹 Debug actual result
	    logger.info("Actual Login Result: " + targetPage);	
	    
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				mac.clickLogout();
				Assert.assertTrue(true);
				
			}
			
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				mac.clickLogout();
				Assert.assertTrue(false);
				
			}
			else
			{
					Assert.assertTrue(true);
					
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		

		
		logger.info("************* Finished of TC004_LoginDDT ************* ");	

	}
	
}
