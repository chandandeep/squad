/**
 * 
 */
package com.accessibility.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * @author Pankaj_Ahuja
 *
 */
public class BrowserUtil {
	
	
	private static WebDriver driver;
	
	public static WebDriver getChromeDriver(String strURL) {
		if(driver == null) {
			driver = invokeChromeDriver(strURL);
		}
		
		return driver;
	}
	
	public static WebDriver invokeChromeDriver(String strURL) {
	System.out.println("11111111");
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chrome_3.exe");
	 ChromeOptions options = new ChromeOptions();
	 options.addArguments("--start-maximized");
	 ChromeDriver driver = new ChromeDriver(options);
	 driver.get(strURL);	 
	 driver.manage().window().maximize();
	
	 return driver;
}
 
	public static WebDriver invokeFirefoxDriver(String strURL) {
		String geckoDriverPath = System.getProperty("user.dir") + "\\drivers\\geckodriver.exe";
        System.setProperty("webdriver.firefox.marionette", geckoDriverPath);
        WebDriver driver = new FirefoxDriver();
        driver.get(strURL);	
        driver.manage().window().maximize();
        return driver;
	}

	public static WebDriver invokeInternetExplorer(String strURL) {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability("requireWindowFocus", true);  
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
		capabilities.setCapability("ie.ensureCleanSession", true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
		
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") + "\\drivers\\IEDriver.exe");

		driver = new InternetExplorerDriver();
		return driver;
	}
}
