package KloudqTechnologies.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import KloudqTechnologies.PageObjects.Login;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest  {

	public static WebDriver driver;
	public  ExtentTest logger;
	public static  ExtentReports report;
	
	@BeforeClass
	public static WebDriver initializeDriver() throws IOException {


		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//KloudqTechnologies//resources//GlobalData.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();

		}

		else if (browserName.equalsIgnoreCase("firefox")) 
		{}
		else if (browserName.equalsIgnoreCase("Edge")) 
		{}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	
	

	public static Login launchApplication(String url) throws IOException
	{
		driver=initializeDriver();
		Login landingPage=new Login(driver);

		landingPage.goTo(url);
		return landingPage;

	}
 

	
	
	
 
 
}
