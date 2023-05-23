package praktikum.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static praktikum.src.HeaderElements.BURGER_LOGO;
import static praktikum.src.HeaderElements.CONSTRUCTOR_BUTTON;


public class CabinetPage {

    private static final String CABINET_NAME_INPUT = ".//input[@name='Name']";
    private static final String CABINET_EMAIL_INPUT = ".//input[@name='name']";
    private static final String LOGOUT_BUTTON = ".//button[text()='Выход']";
    private final WebDriver driver;
    public CabinetPage(WebDriver driver){
        this.driver = driver;
    }

    public String getCabinetName(){
        return driver.findElement(By.xpath(CABINET_NAME_INPUT)).getAttribute("value");
    }

    public String getCabinetEmail(){
        return driver.findElement(By.xpath(CABINET_EMAIL_INPUT)).getAttribute("value");
    }

    public void openConstructorPage(){
        driver.findElement(By.xpath(CONSTRUCTOR_BUTTON)).click();
    }

    public void openMainPage(){
        driver.findElement(By.xpath(BURGER_LOGO)).click();
    }

    public void logOut(){
        driver.findElement(By.xpath(LOGOUT_BUTTON)).click();
    }
}