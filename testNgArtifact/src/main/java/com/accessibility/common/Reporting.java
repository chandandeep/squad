package com.accessibility.common;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class Reporting {

	static ExtentTest test;
	static ExtentReports report;

	
	public static void initiateExtent(String strHostName, String strEnv) {
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		
		report.addSystemInfo("Host Name", "SoftwareTestingMaterial");
		report.addSystemInfo("Environment", "Automation Testing");
        //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
        //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
        report.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
        test = report.startTest("ExtentDemo");
	}
		
		
		
		
	
	
	public static void endExtent() {
		report.endTest(test);
		report.flush();

	}

	public static void Pass(String strExp, String strActual) {
		test.log(LogStatus.PASS, "Expected result:- " + strExp +".... Actual Result:- " + strActual );

	}
	
	public static void Fail(String strExp, String strActual) {
		test.log(LogStatus.FAIL, "Expected result:- " + strExp +".... Actual Result:- " + strActual );

	}
		
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
        //below line is just to append the date format with the screenshot name to avoid duplicate names 
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
//		FileUtils.copyFile(source, finalDestination);
		        //Returns the captured file path
		return destination;
		}
}
