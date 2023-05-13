import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.page.CurrentPage;
import praktikum.page.RestorePage;

import static org.junit.Assert.assertEquals;
import static praktikum.src.UrlList.ACCOUNT_PAGE_URL;

public class RestorePageTest {
    private WebDriver driver;

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //Настройка для тестирования на Яндекс.Браузере
        //System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\yandexdriver.exe");
        driver = new ChromeDriver(options);
    }
    @After
    public void cleanUp(){
        driver.quit();
    }
    @Test
    public void returnFromRecoveryToLoginSuccessful(){
        RestorePage recoveryPage = new RestorePage(driver);
        recoveryPage.open();
        recoveryPage.returnToAccountPage();

        CurrentPage currentPage = new CurrentPage(driver);
        currentPage.waitForUrl(ACCOUNT_PAGE_URL);

        assertEquals("Не перешли на страницу логина", ACCOUNT_PAGE_URL, currentPage.getPageUrl());
    }
}