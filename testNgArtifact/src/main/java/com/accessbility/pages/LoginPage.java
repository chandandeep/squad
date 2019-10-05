package com.accessbility.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Pankaj Ahuja
 */
public class LoginPage extends BasePage {



    @FindBy(css="a[class='login']")
    public WebElement signInButton;

    @FindBy(css="input[name='email']")
    public WebElement loginField;

    @FindBy(css="input[name='passwd']")
    public WebElement passwordField;

    @FindBy(css="button[name='SubmitLogin']")
    public WebElement signinButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public LoginPage clickOnSignIn(){
        signInButton.click();
        return loadPage(LoginPage.class);
    }

    public MyAccountPage loginToApplication(String email, String password){
        sendkeys(loginField, email);
        sendkeys(passwordField, password);
        scrollElementToView(driver, signinButton);
        signinButton.click();
        return loadPage(MyAccountPage.class);
    }
}

