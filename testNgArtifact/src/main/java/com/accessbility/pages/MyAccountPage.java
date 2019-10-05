package com.accessbility.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Chandandeep Singh on 05-10-2019.
 */
public class MyAccountPage extends BasePage {

    @FindBy(css="span[class='navigation_page']")
    public WebElement myaccount;

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader(){
        return myaccount.getText();
    }

}
