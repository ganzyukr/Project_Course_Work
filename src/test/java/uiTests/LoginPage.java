package uiTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {


        @FindBy(xpath = "//button[@id=\"login\"]")
        public WebElement loginButton;

        @FindBy (xpath = "//input[@id=\"userName\"]")
        public WebElement loginField;

        @FindBy (xpath = "//input[@id=\"password\"]")
        public WebElement passwordField;

        public WebDriverWait wait;


        public LoginPage(WebDriver driver) {
            super(driver);
            driver.get("https://demoqa.com/login");
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }

        public void credentialsUserName (String text) {
            typeText(loginField, text);
        }

        public void credentialsPassword (String text) {
            typeText(passwordField, text);
        }

        public void login(){
            this.credentialsUserName("ganzyuk_test");
            this.credentialsPassword("$Ganzyuk_Test123");
            this.clickButton(loginButton);
        }
        }
