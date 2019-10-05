package com.accessibility.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.accessbility.pages.BasePage;

public class ObjLogin extends BasePage {

	
	
	
	
	
	private String a = "adas";
	private By userName = By.id("username");
	private By password = By.name("j_password");
	private By submitButton = By.xpath("/html/body/div[1]/div/div[2]/div/form/div[3]/div/button");

	public ObjLogin(WebDriver driver) {
		super(driver);
	}

	public WebElement getUserName() {
		return driver.findElement(userName);
	}
	


	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement getButton() {
		return driver.findElement(submitButton);
	}
	

}
