package praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.restassured.RestAssured.given;
import static praktikum.src.HeaderElements.CONSTRUCTOR_BUTTON;
import static praktikum.src.UrlList.MAIN_PAGE_URL;

import java.time.Duration;

public class MainPage {
    public static final String MIDDLE_CABINET_BUTTON = ".//button[text()='Войти в аккаунт']";
    public static final String BUN_CONSTRUCTOR_BUTTON = ".//span[text()='Булки']";
    public static final String SAUCE_CONSTRUCTOR_BUTTON = ".//span[text()='Соусы']";
    public static final String INGREDIENT_CONSTRUCTOR_BUTTON = ".//span[text()='Начинки']";
    public static final String SAUCE_CONSTRUCTOR_SELECTED = ".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[2][contains(@class, 'current')]";
    public static final String INGREDIENT_CONSTRUCTOR_SELECTED = ".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[3][contains(@class, 'current')]";
    public static final String BUN_CONSTRUCTOR_SELECTED = ".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[1][contains(@class, 'current')]";
    private final WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    public void open(){
        driver.get(MAIN_PAGE_URL);
    }
    public void openCabinetPage(String button){
        driver.findElement(By.xpath(button)).click();
    }
    public void waitForUrl(String url){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.urlToBe(url));
    }
    public void selectConstructorCategory(String category){
        driver.findElement(By.xpath(category)).click();
    }
}