package tests;

import helpers.Utils;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AuthorizationPage;
import pages.MailboxPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MailboxTests {
    private ThreadLocal<RemoteWebDriver> driverThreadLocal = new ThreadLocal<>();
    private WebDriver driver;
    private AuthorizationPage authorizationPage;
    private MailboxPage mailboxPage;

    @BeforeMethod
    public final void setEnvironment() throws MalformedURLException {
        String urlLogin = "https://passport.yandex.ru/";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.MAC);
        driverThreadLocal.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities));
        driver=driverThreadLocal.get();
        driver.get(urlLogin);
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Авторизация в почтовом ящике / Отправление сообщения с количеством писем")
    public final void testTask() throws IOException, InterruptedException {
        String login = "almaz.dabisov";
        String password = "almazeikum196345";
        String address = "dabisov.almaz@gmail.com";
        String subject = "Тестовое задание. Дабисов.";
        String user = "dabisov.almaz@gmail.com";
        String searchLine = user + " от:" + user;

        StringBuilder mail = new StringBuilder();
        mail.append("Общее количество писем: ");
        SoftAssert softAssert = new SoftAssert();

        authorizationPage = new AuthorizationPage(driver);
        mailboxPage = authorizationPage.inputLogin(login)
                .submitLoginClick()
                .inputPassword(password)
                .submitPassClick()
                .profilePictureClick()
                .goToMailboxPageClick();
        softAssert.assertEquals(driver.getCurrentUrl(), "https://mail.yandex.ru/");
        mail.append(Utils.getLastWordBySeparator(mailboxPage.getValueFromMessageCountBox(), " "));
        mail.append("\nКоличество писем от ")
                .append(user)
                .append(": ");
        mailboxPage.setLineInMailboxSearch(searchLine)
                .submitSearchInMailboxClick();
        mail.append(Utils.getFirstWordBySeparator(mailboxPage.getValueFoundedMail()," "));
        mailboxPage.newMailClick();
        softAssert.assertTrue(mailboxPage.checkOpeningMailForm());
        mailboxPage.setAddressRecipient(address)
                .setMailSubject(subject)
                .setMailText(mail.toString())
                .sendMailClick();
        softAssert.assertTrue(mailboxPage.checkSending());
        softAssert.assertAll();
    }

    @AfterMethod
    public final void tearDown() {
        driver.quit();
    }
}
