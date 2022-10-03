package uiTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {

        @FindBy(xpath = "//button[@id=\"login\"]")
        public WebElement loginButton;

        @FindBy (xpath = "//input[@id=\"userName\"]")
        public WebElement userNameField;

        @FindBy (xpath = "//input[@id=\"password\"]")
        public WebElement passwordField;

        @FindBy(xpath = "//*[text()='Log out']")
        public WebElement logoutButton;
        public WebDriverWait wait;

        public LoginPage(WebDriver driver) {
            super(driver);
            driver.get("https://demoqa.com/login");
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }

        public void credentialsUserName (String text) {
            typeText(userNameField, text);
        }

        public void credentialsPassword (String text) {
            typeText(passwordField, text);
        }

        public void login(){
            credentialsUserName("ganzyuk_test");
            credentialsPassword("$Ganzyuk_Test123");
            clickButton(loginButton);
        }

        // The second part
        public void logoutButton() {
            clickButton(logoutButton);
        }
}
