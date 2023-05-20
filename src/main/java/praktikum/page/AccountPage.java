package praktikum.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static praktikum.src.UrlList.ACCOUNT_PAGE_URL;

public class AccountPage {

    public static final String EMAIL_INPUT = "//label[contains(text(),'Email')]/../input";
    public static final String PASSWORD_INPUT = "//label[contains(text(),'Пароль')]/../input";
    public static final String LOGIN_BUTTON = "//button[text()='Войти']";

    private final WebDriver driver;
    public AccountPage(WebDriver driver){
        this.driver = driver;
    }
    public void open(){
        driver.get(ACCOUNT_PAGE_URL);
    }
    public void fillEmail(String email){
        driver.findElement(By.xpath(EMAIL_INPUT)).clear();
        driver.findElement(By.xpath(EMAIL_INPUT)).sendKeys(email);
    }
    public void fillPassword(String password){
        driver.findElement(By.xpath(PASSWORD_INPUT)).clear();
        driver.findElement(By.xpath(PASSWORD_INPUT)).sendKeys(password);
    }

    public void logIn(){
        driver.findElement(By.xpath(LOGIN_BUTTON)).click();
    }

}