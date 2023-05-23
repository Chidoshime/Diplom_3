package praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static praktikum.src.UrlList.REGISTRATION_PAGE_URL;

public class RegistrationPage {

    private static final String NAME_INPUT = ".//form[@class='Auth_form__3qKeq mb-20']/fieldset[1]/descendant::input";
    private static final String EMAIL_INPUT = ".//form[@class='Auth_form__3qKeq mb-20']/fieldset[2]/descendant::input";
    private static final String PASSWORD_INPUT = ".//form[@class='Auth_form__3qKeq mb-20']/fieldset[3]/descendant::input";
    private static final String REGISTRATION_BUTTON = ".//form[@class='Auth_form__3qKeq mb-20']/button";
    private static final String VALIDATION_ERROR = ".//p[text()='Некорректный пароль']";

    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(REGISTRATION_PAGE_URL);
    }

    public void fillName(String name) {
        driver.findElement(By.xpath(NAME_INPUT)).clear();
        driver.findElement(By.xpath(NAME_INPUT)).sendKeys(name);
    }

    public void fillEmail(String email) {
        driver.findElement(By.xpath(EMAIL_INPUT)).clear();
        driver.findElement(By.xpath(EMAIL_INPUT)).sendKeys(email);
    }

    public void fillPassword(String password) {
        driver.findElement(By.xpath(PASSWORD_INPUT)).clear();
        driver.findElement(By.xpath(PASSWORD_INPUT)).sendKeys(password);
    }

    public void waitForUrl(String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void confirmRegistration() {
        driver.findElement(By.xpath(REGISTRATION_BUTTON)).click();
    }

    public String checkValidationError() {
        return driver.findElement(By.xpath(VALIDATION_ERROR)).getText();
    }
}