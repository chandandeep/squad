package newDriverSuite;

import com.accessbility.pages.LoginPage;
import com.accessbility.pages.MyAccountPage;
import com.accessibility.common.GenericUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.accessbility.pages.BasePage;
import utils.Utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewClass {
	private BasePage oBasePage = new BasePage(this.driver);
    private static final URL scriptUrl = NewClass.class.getResource("/axe.min.js");
    protected WebDriver driver;


    @BeforeTest(description = "Set up the driver")
	public void abc() {
		oBasePage.CloseAllOpenBrowsers();
		System.out.println("************************************before");

	}


	@Test(description = "Accessiblity of home page")
	public void homePageTest() {
        driver = oBasePage.initiateBrowser("chrome", GenericUtils.getProperty("url"));
        Assert.assertTrue(isViolationPresent());
    }

    @Test(description = "Accessiblity of Login page")
    public void LoginPageTest(){
        driver = oBasePage.initiateBrowser("chrome", GenericUtils.getProperty("url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loadPage(LoginPage.class).clickOnSignIn();
        Assert.assertTrue(isViolationPresent());
    }

    @Test(description = "Accessiblity of My Account page")
    public void MyAccountPageTest() {
        driver = oBasePage.initiateBrowser("chrome", GenericUtils.getProperty("url"));
        MyAccountPage myAccountPage = new LoginPage(driver)
                .loadPage(LoginPage.class)
                .clickOnSignIn()
                .loginToApplication("as@squad.com", "Password1");
        Assert.assertEquals(myAccountPage.getPageHeader(), "My account");
        Assert.assertTrue(isViolationPresent());

    }

    @Test(description = "Accessiblity with no failure")
    public void testAccessibilityWithOptions() {
        driver = oBasePage.initiateBrowser("chrome", GenericUtils.getProperty("url1"));
        List<String> rules = new ArrayList<>();
        rules.add("landmark-one-main");
        rules.add("meta-viewport");
        rules.add("region");

        List<String> failList = Utils.bypassRules(driver, "violations", scriptUrl, rules);
        List<String> passList = Utils.bypassRules(driver, "passes", scriptUrl, rules);

        boolean noViolation = failList.size()==0;
        Utils util = new Utils();
        util.logMessage(failList, "violations");
        Utils.logToAllure("--------------------------------","");
        util.logMessage(passList, "passes");
        Assert.assertTrue(noViolation);

    }



	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}




    public boolean isViolationPresent(){
        List<String> failList = Utils.getValue(driver, "violations", scriptUrl);
        List<String> passList = Utils.getValue(driver, "passes", scriptUrl);

        boolean noViolation = failList.size()==0;
        Utils util = new Utils();
        util.logMessage(failList, "violations");
        Utils.logToAllure("--------------------------------","");
        util.logMessage(passList, "passes");

        return noViolation;
    }
}
