import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.page.*;
import praktikum.rest.client.UserClient;
import praktikum.rest.model.User;
import praktikum.rest.model.UserGenerator;


import static org.junit.Assert.assertEquals;
import static praktikum.src.HeaderElements.TOP_CABINET_BUTTON;
import static praktikum.src.UrlList.CABINET_PAGE_URL;
import static praktikum.page.RegistrationPage.VALIDATION_ERROR;
import static praktikum.src.UrlList.ACCOUNT_PAGE_URL;

public class RegistrationTest {
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
    public void registrationSuccess(){
        User user = UserGenerator.getRandom();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();

        registrationPage.fillName(user.getName());
        registrationPage.fillEmail(user.getEmail());
        registrationPage.fillPassword(user.getPassword());
        registrationPage.confirmRegistration();

        registrationPage.waitForUrl(ACCOUNT_PAGE_URL);

        AccountPage accountPage = new AccountPage(driver);
        accountPage.fillEmail(user.getEmail());
        accountPage.fillPassword(user.getPassword());
        accountPage.logIn();

        MainPage mainPage = new MainPage(driver);
        mainPage.openCabinetPage(TOP_CABINET_BUTTON);
        mainPage.waitForUrl(CABINET_PAGE_URL);

        CabinetPage cabinetPage = new CabinetPage(driver);

        assertEquals("Регистрация не прошла успешно, имя пользователя не совпало", cabinetPage.getCabinetName(), user.getName());
        assertEquals("Регистрация не прошла успешно, email пользователя не совпал", cabinetPage.getCabinetEmail(), user.getEmail().toLowerCase());
    }

    @Test
    public void registrationWithPasswordLessThanSixSymbolsFail(){
        User user = UserGenerator.getRandom();
        user.setPassword("12345");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();

        registrationPage.fillName(user.getName());
        registrationPage.fillEmail(user.getEmail());
        registrationPage.fillPassword(user.getPassword());
        registrationPage.confirmRegistration();

        assertEquals("Сообщение об ошибке не вывелось или не совпало", "Некорректный пароль", driver.findElement(By.xpath(VALIDATION_ERROR)).getText());
    }
}