package uiTests;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class Tests extends BasePage {

    public Tests(){
        super();
    }


    @Test
    public void loginTest() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickButton(homePage.loginButton);

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();

        // Get page Title
        String actualTitle = driver.getTitle();

        // Verify Title is as expected
        String expectedTitle = "ToolsQA";
        assertEquals(actualTitle, expectedTitle);
    }

//    public void credentialsOfTestUser () {
//        // Provide credentials of Test user
//        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
//        homePage.clickButton(homePage.loginButton);
//
//        // Verify that with text Git Pocket Guide is present on the page
//        String actualTitle = driver.getTitle();
//        String expectedTitle = "Git Pocket Guide";
//        assertEquals(actualTitle, expectedTitle);
//    }

    @Test
    public void logoutTest() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickButton(homePage.loginButton);

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();



        loginPage.logout();

        // Get page Title
        String actualTitle = driver.getTitle();

        // Verify Title is as expected
        String expectedTitle = "ToolsQA";
        assertEquals(actualTitle, expectedTitle);
    }
}
