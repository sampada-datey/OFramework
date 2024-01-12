package KloudqTechnologies.tests;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import KloudqTechnologies.JDBCConnection.ConnectJDBC;
import KloudqTechnologies.PageObjects.DashboardShield;
import KloudqTechnologies.TestComponents.BaseTest;
import org.testng.annotations.Optional;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
public class AdminTestShield  {

	ExtentTest logger;
	ExtentReports report;



	AdminTestShield()
	{
		ExtentHtmlReporter extent =new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/ShieldTest1.html"));
		this.report=new ExtentReports();
		this.report.attachReporter(extent);

	}
	@BeforeTest
	@Parameters("groupNames")
	public void ToSetUPTest(@Optional String groupNames)
	{
		
		        // Retrieve parameter value from TestNG XML
		//groupNames = context.getCurrentXmlTest().getParameter("groupNames");
		 //       System.out.println("Parameter value: " + groupNames);
		  
		        if (groupNames != null && !groupNames.isEmpty()) {
		            String[] groupsArray = groupNames.split(",");
		            for (String group : groupsArray) {
		                System.out.println("Running test in group: " + group.trim());
		                // Perform actions or setup specific to each group
		            }
		        } else {
		            System.out.println("No specific groups provided. Running default test.");
		            // Run default test or perform default actions
		        }
		        
	
	}
	@Test(groups= {"All,Dashboard"},priority = 1) 
	 @Parameters({"companyName","userType"})
	public void Login( @Optional String companyName, String userType) throws IOException, InterruptedException
	{  

		System.out.println("Started Executing Login Component");
		LoginTestExcel AdminTestShield=new LoginTestExcel();
		AdminTestShield.getDataByCompanyName(this.report,companyName,userType);
		 System.out.println("CompanyName:-> " + companyName);
		 System.out.println("UserType:->" + userType);
		
		
		
		//lte.getDataByCompanyName(this.report);
		Thread.sleep(4000);
		
	}  

	@Test(groups= {"All,Admin"},priority = 2)  
	public void Dashboard() throws SQLException, FileNotFoundException, IOException, InterruptedException  
	{  

		ConnectJDBC sqlconnect=new ConnectJDBC();

		sqlconnect.Connect();

		DashboardShield DS=new DashboardShield(BaseTest.driver);
		DS.HeaderCheck(this.report);
		DS.CheckActualConsumptionToday(this.report);
//		DS.CheckCostOFActualPredToday(this.report);
		DS.CheckPowerFactor(this.report);
		DS.CheckAvgKwhToday(this.report);
//		DS.CheckEnergyTreeToday(this.report);

		DS.CheckEcActualPrYesterday(this.report);
		DS.CheckCostActualPrYesterday(this.report);
		DS.CheckAvgKwhYesterday(this.report);
//		DS.CheckEnergyTreeYesterday(this.report);	

		DS.CheckEcActualMonthly(this.report); 
		DS.CheckCoAcMonthly(this.report);
		DS.CheckAvgKwhMonthly(this.report);
//		DS.CheckEnergyTreeMonthly(this.report);

//		DS.CheckEnegryConsumptionTrendGraph(this.report);


		DS.CheckEnergyUsageDaywise(this.report);
		DS.CheckEnergyUsageMonthwise(this.report);
		DS.CheckEnergyUsageDivisionWise(this.report);
		DS.CheckEnergyUsageSiteWise(this.report);	

//		DS.EnergyUsageCostDaywiseGraph(this.report);
		DS.EnergyUsageCostMonthWiseGraph(this.report);
		DS.EnergyUsageCostDivisionWiseGraph(this.report);
		DS.EnergyUsageCostSiteWiseGraph(this.report);

		DS.MaximumDemandIND(this.report);
		DS.MaximumDemandMain(this.report);




		Thread.sleep(5000);

		System.out.println("Executed Dashboard Component of Shield");

	}  




	@Test  (groups= {"Admin"},priority = 3)  
	public void Replay()  
	{  

		System.out.println("Execute Replay Component");  
		// test3.info("Values of Energy Consumption are being Verified");
	}  
	@Test  (groups= {"Admin"},priority = 4)  
	public void Trends()  
	{  

		System.out.println("Execute Trends Component");  
		// test4.info("Values of Energy Consumption are being Verified");
	}  
	@Test  (groups= {"Admin"},priority = 5)  
	public void Administrator()  
	{  

		System.out.println("Execute Administrator Component"); 

	}  
	@Test  (groups= {"Admin"},priority = 6)  
	public void Reports()  
	{  

		System.out.println("Execute Reports Component");  


	}  
	@Test  (groups= {"Admin"},priority = 7)  
	public void teardown()  
	{  

		report.flush();



	}  


}
