package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
//import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;


public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter; // UI of report
	public ExtentReports extent; // populate comman info on the report
	public ExtentTest test; // creating test cases entries and update status of test methods
	
	String repName;
	
	public void onStart(ITestContext testcontext)
	{
		
		//SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		//Date dt= new Date();
		//String currentDatetimestamp = df.format(dt);
	
	
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName  = "Test - Report - "+ timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(
		        System.getProperty("user.dir")
		        + File.separator + "reports"
		        + File.separator + repName
		); //Specify location of report
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); // title of report
		sparkReporter.config().setReportName("OpenCart Functional Testing"); // Name of report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub module", "Customer");
		extent.setSystemInfo("user Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operationg System", os);
		
		String browser = testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", browser);
		
		/*
		 	List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
		 	if(!includedGroups.isEmpty())
		 	{
		 		extent.setSystemInfo("Group", includedGroups.toString());
		 	}
		 */
	}
	
    // ✅ 2️⃣ RUNS BEFORE EVERY TEST
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName()); // create new entry in report
	//	test.assignCategory(result.getMethod().getGroups());// to display groups in report
		test.log(Status.PASS, "Test case PASSES is : "+result.getName()); // update status
	}
	
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName()); // create new entry in report
//		test.assignCategory(result.getMethod().getGroups());// to display groups in report
		
		test.log(Status.FAIL, "Test case FAILED is : "+result.getName()); // update status
		test.log(Status.INFO, "Test case FAILED cause is : "+result.getThrowable().getMessage());
		
		try 
		{
	       BaseClass base = (BaseClass) result.getInstance();
	      //  String path = base.captureScreen(result.getName());
	      //  extentTest.addScreenCaptureFromPath(path);
	      //  BaseClass base = new BaseClass();
	        
			String imgPath = base.captureScreen(result.getName());
			
			if(imgPath !=null)
			{
			test.addScreenCaptureFromPath(imgPath);
			}
			else
			{
				test.log(Status.INFO, "Screenshot is not captured");
			}
		} 
		catch (IOException e)
		{
	        test.log(Status.INFO, "Exception while taking screenshot: " + e.getMessage());

		}
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
	test = extent.createTest(result.getTestClass().getName()); // create new entry in report
//		test.assignCategory(result.getMethod().getGroups());// to display groups in report
		
		test.log(Status.SKIP, "Test case SKIIPED is : "+result.getName()); // update status
		test.log(Status.INFO, "Test case SKIPPED cause is : "+result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context)
	{
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")
		        + File.separator + "reports"
		        + File.separator + repName;
		File extentReport = new File(pathOfExtentReport);
		
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	





}

	 
	 
	 


