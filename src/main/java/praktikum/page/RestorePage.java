package praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static praktikum.src.UrlList.PASSWORD_RESTORE_PAGE_URL;

public class RestorePage {
    public static final String RETURN_TO_ACCOUNT_BUTTON = ".//a[contains(@href,'login')]";
    private final WebDriver driver;
    public RestorePage(WebDriver driver){
        this.driver = driver;
    }
    public void open(){
        driver.get(PASSWORD_RESTORE_PAGE_URL);
    }

    public void returnToAccountPage(){
        driver.findElement(By.xpath(RETURN_TO_ACCOUNT_BUTTON)).click();
    }
}