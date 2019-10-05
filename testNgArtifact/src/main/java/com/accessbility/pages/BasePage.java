package com.accessbility.pages;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.accessibility.common.BrowserUtil;

/**
 * Created by Pankaj_Ahuja.
 */
public class BasePage {

    protected static WebDriver driver;
    private static final String TASKLIST = "tasklist";
    private static final String KILL = "taskkill /F /IM";
    
    BrowserUtil oBrwUtil = new BrowserUtil();


	public BasePage(WebDriver driver){
		this.driver = driver;
	}

	public<T extends BasePage> T loadPage(Class<T> page) {
		return PageFactory.initElements(driver, page);
	}

   public WebDriver initiateBrowser(String strBrowser, String strURL) {
	   
	   if(strBrowser.toUpperCase().contains("FIREFOX"))
		   driver = BrowserUtil.invokeFirefoxDriver(strURL);
	   else  if(strBrowser.toUpperCase().contains("CHROME"))
		   driver = BrowserUtil.invokeChromeDriver(strURL);
	   else
		   driver = BrowserUtil.invokeInternetExplorer(strURL);
	   
	   return driver;
   }
    
    
    public void killProcess(String strProcessName) {
    	try {
    		if(isProcessRunning(strProcessName)) {
    			System.out.println("Process running as " + strProcessName);
    			Runtime.getRuntime().exec(KILL + strProcessName);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}    	
    }
    
    public boolean isProcessRunning(String strProcessName) throws IOException {
    	Process p = Runtime.getRuntime().exec(TASKLIST);
    	BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
    	
    	String strLine;
    	while((strLine = reader.readLine()) != null){
    		if(strLine.contains(strProcessName))
    			return true;
    	}
    	
    	return false;
    }
    
    public void CloseAllOpenBrowsers() {
    	killProcess("firefox.exe");
    	killProcess("chrome_3.exe");
    	killProcess("iexplore.exe");
    }
      
	public void sendkeys(WebElement element, String strValue) {
		try {
			System.out.println(element);
			element.click();
			Thread.sleep(100);
			element.clear();
			Thread.sleep(100);
			element.sendKeys(strValue);
			
//			ReporterA.pass
		} catch(Exception e) {
//			ReporterA.fail
			
		}
	}
		
	public void scrollElementToView(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		try {
			jsExecutor.executeScript("window.scrollTo(" + element.getLocation().x + "." + (element.getLocation().y - 20) + ");");
		} catch(Exception e) {
			
		}
	}
}

