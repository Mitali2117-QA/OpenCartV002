package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC003_LoginTest extends BaseClass{

	@Test
	public void verify_Login()
	{
		logger.info("****** Starting TC003_LoginTest *******");
		
		try
		{
		//HomePage
		HomePage hp= new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAddress(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.click_Login();
		
		//MyAccount Page
		
		MyAccountPage map= new MyAccountPage(driver);
		boolean targetPage = map.isMyAccountPageExists();
		
		Assert.assertEquals(targetPage, true, "Login Failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Finished TC003_LoginTest ******");

		
	}
	
	
}
