package uiTests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class HomePage extends BasePage {


        @FindBy(xpath = "//button[@id=\"login\"]")
        public WebElement loginButton;

        public WebDriverWait wait;


        public HomePage(WebDriver driver) {
            super(driver);
            driver.get("https://demoqa.com/books");
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }
}
