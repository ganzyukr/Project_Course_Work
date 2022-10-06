package uiTests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import static org.junit.Assert.assertEquals;

    public class LoginTests extends BasePage {

    public LoginTests(){
        super();
    }

    @Test
    public void loginTest() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickButton(homePage.loginButton);

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();

        //Assert that text is present on the page.
        WebElement element = driver.findElement(By.xpath("//*[text()='Git Pocket Guide']"));
        String actualText = element.getText();

        // Verify Text is as expected
        String expectedText = "Git Pocket Guide";
        assertEquals(actualText, expectedText);

    }

    @Test
    public void logoutTest() throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickButton(homePage.loginButton);

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();

        loginPage.logoutButton();

        //Assert that text is present on the page.
        WebElement element = driver.findElement(By.xpath("//*[text()='Login in Book Store']"));
        String actualText = element.getText();

        // Verify Text is as expected
        String expectedTitle = "Login in Book Store";
        assertEquals(actualText, expectedTitle);

    }
}
