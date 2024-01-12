package KloudqTechnologies.tests;



import KloudqTechnologies.TestComponents.BaseTest;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterTest;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class LoginTest extends BaseTest  {
	@FindBy(xpath="//*[@id=\"kt_login_signin_form\"]/div[2]")
	WebElement errorMessage;
	ExtentReports report;
	LoginTest()
	{
		ExtentHtmlReporter extent =new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/ShieldTest1.html"));
		this.report=new ExtentReports();
		this.report.attachReporter(extent);
	}
	
	@Test(dataProvider="loginData",dataProviderClass=LoginTestExcel.class)
	
	public void Loginuser() throws IOException, InterruptedException {
		
		
	    LoginTestExcel.getData(this.report);
	   
        
	}

	
}
