import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.page.CurrentPage;
import praktikum.page.MainPage;
import praktikum.rest.client.UserClient;

import static driver.WebDriverCreator.createWebDriver;

public class ConstructorTest  {
    UserClient userClient = new UserClient();
    private WebDriver driver;
    @Before
    public void setUp() {
        driver = createWebDriver();
    }

    @Test
    @DisplayName("Проверка перехода на вкладку Бургеры")
    public void clickOnBread() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSauceButton();
        mainPage.clickBreadButton();
        mainPage.checkBunsIsDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода на вкладку Соусы")
    public void clickOnSauce() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSauceButton();
        mainPage.checkSaucesIsDisplayed();

    }

    @Test
    @DisplayName("Проверка перехода на вкладку Начинки")
    public void clickOnIngredient() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickIngredientButton();
        mainPage.checkIngredientIsDisplayed();

    }

    @After
    public void cleanUp(){
        CurrentPage currentPage = new CurrentPage(driver);
        String accessToken = currentPage.getAuthToken();

        if(accessToken!=null){userClient.delete(accessToken);}

        driver.quit();
    }
}