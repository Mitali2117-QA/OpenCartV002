package testCases;

import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.util.Properties;


public class BaseClass {

public static WebDriver driver;
public static Logger logger;
public Properties p;
	
	@BeforeClass
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws Exception
	{
		//loading config.properties file
		logger = LogManager.getLogger(this.getClass());
		
	    FileReader file = new FileReader(
	            System.getProperty("user.dir") + "/src/test/resources/config.properties"
	    );
	    p = new Properties();
	    p.load(file);

		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			//capabilities.setPlatform(Platform.MAC);
			if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("Linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching os");
			}
			
			//Browser
			//capabilities.setBrowserName(br);
			
			switch(br.toLowerCase())
			{
			case "chrome" : capabilities.setBrowserName("chrome"); break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : capabilities.setBrowserName("firefox"); break;
			default : System.out.println("No matching browser");; return;
			}
			driver = new RemoteWebDriver(new URL(p.getProperty("gridURL")),capabilities);
		}
	    
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome" : driver =new ChromeDriver(); break;
			case "edge" : driver =new EdgeDriver(); break;
			case "firefox" : driver =new FirefoxDriver(); break;
			default : logger.error("Invalid browser name"); return;
			}
		}	
	//	driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		 driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomeString()
	{
		
		@SuppressWarnings("deprecation")
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber()
	{
		
		String generatedNum = RandomStringUtils.randomNumeric(10);
		return generatedNum;
	}
	
	public String randomAlphaNum()
	{
		
		String generatedAlphaNum = RandomStringUtils.randomAlphanumeric(8);
		return generatedAlphaNum;
	}
	
	public String captureScreen(String tname) throws IOException
	{
	    if (driver == null) {
	        logger.error("Driver is null. Screenshot not captured.");
	        return null;
	    }
	    
	    if (logger != null) {
	        logger.error("Test failed - capturing screenshot");
	    } 
	    

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")
		        + "/screenshots/" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		//sourceFile.renameTo(targetFile);
		FileUtils.copyFile(sourceFile, targetFile);

		return targetFilePath;
	}
	
}
