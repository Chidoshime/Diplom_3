import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.page.CurrentPage;
import praktikum.page.RestorePage;
import praktikum.rest.client.UserClient;

import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertEquals;
import static praktikum.src.UrlList.ACCOUNT_PAGE_URL;

public class RestorePageTest {
    private WebDriver driver;
    UserClient userClient = new UserClient();
    @Before
    public void setUp() {
        driver = createWebDriver();
    }
    @After
    public void cleanUp(){
        CurrentPage currentPage = new CurrentPage(driver);
        String accessToken = currentPage.getAuthToken();

        if(accessToken!=null){userClient.delete(accessToken);}

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