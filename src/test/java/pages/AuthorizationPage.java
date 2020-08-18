package pages;

import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationPage {
    private WebDriver driver;

    @FindBy(css="input[id='passp-field-login']")
    private WebElement inputLoginElement;

    @FindBy(css="button[class='Button2 Button2_size_auth-l Button2_view_action Button2_width_max Button2_type_submit']")
    private WebElement submitLoginElement;

    @FindBy(css="input[id='passp-field-passwd']")
    private WebElement inputPasswordElement;

    @FindBy(css="div[class='user-pic user-pic_has-plus_ user-account__pic']")
    private WebElement profilePictureElement;

    @FindBy(css="a[href='https://mail.yandex.ru']")
    private WebElement goToMailboxPage;

    public AuthorizationPage(WebDriver webDriver){
        driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ввести логин")
    public AuthorizationPage inputLogin(String login){
        Waiters.WaitUntilElementNotClickable(driver, inputLoginElement);
        inputLoginElement.sendKeys(login);
        return this;
    }
    @Step("Подвердить введенный логин")
    public AuthorizationPage submitLoginClick(){
        Waiters.WaitUntilElementNotClickable(driver, submitLoginElement);
        submitLoginElement.click();
        return this;
    }
    @Step("Подвердить введенный пароль")
    public AuthorizationPage submitPassClick() throws InterruptedException {
        Waiters.WaitUntilElementNotClickable(driver, submitLoginElement);
        submitLoginElement.click();
        return new AuthorizationPage(driver);
    }
    @Step("Ввести пароль")
    public AuthorizationPage inputPassword(String password){
        Waiters.WaitUntilElementNotClickable(driver, inputPasswordElement);
        inputPasswordElement.sendKeys(password);
        return this;
    }
    @Step("Перейти к профилю")
    public AuthorizationPage profilePictureClick() throws InterruptedException {
//        Waiters.WaitUntilElementNotClickable(driver, profilePictureElement);
        Thread.sleep(3000);
        profilePictureElement.click();
        return this;
    }
    @Step("Перейти к почте")
    public MailboxPage goToMailboxPageClick(){
        Waiters.WaitUntilElementNotClickable(driver, goToMailboxPage);
        goToMailboxPage.click();
        return new MailboxPage(driver);
    }
}
