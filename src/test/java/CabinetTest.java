import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.page.AccountPage;
import praktikum.page.CabinetPage;
import praktikum.page.CurrentPage;
import praktikum.page.MainPage;
import praktikum.rest.client.UserClient;
import praktikum.rest.model.User;
import praktikum.rest.model.UserGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static praktikum.src.HeaderElements.TOP_CABINET_BUTTON;
import static praktikum.src.UrlList.*;

public class CabinetTest {
    private UserClient userClient;
    private WebDriver driver;

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //Настройка для тестирования на Яндекс.Браузере
        //System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\yandexdriver.exe");
        driver = new ChromeDriver(options);
        userClient = new UserClient();
    }
    @After
    public void cleanUp(){
        CurrentPage currentPage = new CurrentPage(driver);
        String accessToken = currentPage.getAuthToken();

        if(accessToken!=null){userClient.delete(accessToken);}

        driver.quit();
    }

    @Test
    public void redirectFromCabinetToConstructorSuccess(){
        User user = UserGenerator.getRandom();
        userClient.create(user);

        AccountPage accountPage = new AccountPage(driver);
        accountPage.open();
        accountPage.fillEmail(user.getEmail());
        accountPage.fillPassword(user.getPassword());
        accountPage.logIn();

        MainPage mainPage = new MainPage(driver);
        mainPage.openCabinetPage(TOP_CABINET_BUTTON);
        mainPage.waitForUrl(CABINET_PAGE_URL);

        CabinetPage cabinetPage = new CabinetPage(driver);
        cabinetPage.openConstructorPage();

        CurrentPage currentPage = new CurrentPage(driver);
        currentPage.waitForUrl(MAIN_PAGE_URL);

        assertEquals("Не перешли на страницу конструктора", MAIN_PAGE_URL, currentPage.getPageUrl());
    }

    @Test
    public void redirectFromCabinetToMainPageSuccess(){
        User user = UserGenerator.getRandom();
        userClient.create(user);

        AccountPage accountPage = new AccountPage(driver);
        accountPage.open();
        accountPage.fillEmail(user.getEmail());
        accountPage.fillPassword(user.getPassword());
        accountPage.logIn();

        MainPage mainPage = new MainPage(driver);
        mainPage.openCabinetPage(TOP_CABINET_BUTTON);
        mainPage.waitForUrl(CABINET_PAGE_URL);

        CabinetPage cabinetPage = new CabinetPage(driver);
        cabinetPage.openMainPage();

        CurrentPage currentPage = new CurrentPage(driver);
        currentPage.waitForUrl(MAIN_PAGE_URL);

        assertEquals("Не перешли на страницу конструктора", MAIN_PAGE_URL, currentPage.getPageUrl());
    }

    @Test
    public void logoutFromCabinetSuccess(){
        User user = UserGenerator.getRandom();
        userClient.create(user);

        AccountPage accountPage = new AccountPage(driver);
        accountPage.open();
        accountPage.fillEmail(user.getEmail());
        accountPage.fillPassword(user.getPassword());
        accountPage.logIn();

        MainPage mainPage = new MainPage(driver);
        mainPage.openCabinetPage(TOP_CABINET_BUTTON);
        mainPage.waitForUrl(CABINET_PAGE_URL);

        CabinetPage cabinetPage = new CabinetPage(driver);
        cabinetPage.logOut();

        CurrentPage currentPage = new CurrentPage(driver);
        currentPage.waitForUrl(ACCOUNT_PAGE_URL);
        String accessTokenAfterLogout = currentPage.getAuthToken();

        accountPage.fillEmail(user.getEmail());
        accountPage.fillPassword(user.getPassword());
        accountPage.logIn();

        assertNull("Выход из системы не произошел", accessTokenAfterLogout);
    }
}