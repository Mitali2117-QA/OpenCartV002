package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txt_firstname;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txt_lastname;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txt_telephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txt_confirmPassword; 
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chk_Policy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btn_Continue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
public void setFirstName(String fname)
{
	txt_firstname.sendKeys(fname);
}

public void setLastName(String lname)
{
	txt_lastname.sendKeys(lname);
}

public void setEmail(String email)
{
	txt_email.sendKeys(email);
}
	
public void setTelephone(String tel)
{
	txt_telephone.sendKeys(tel);
}
	
public void setPassword(String pwd)
{
	txt_password.sendKeys(pwd);
}	

public void setConfirmPassword(String pwd)
{
	txt_confirmPassword.sendKeys(pwd);
}

public void setcheckPolicy()
{
	chk_Policy.click();
}

public void setClickButton()
{
	btn_Continue.click();
}

public String getConfirmationMsg()
{
	try {
		return(msgConfirmation.getText());
	}catch(Exception e)
	{
		return(e.getMessage());
	}
	
}
	

}
