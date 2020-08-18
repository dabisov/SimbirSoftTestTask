package pages;

import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailboxPage {
    private WebDriver driver;

    @FindBy(css="a[class='mail-ComposeButton js-main-action-compose']")
    private WebElement newMailElement;

    @FindBy(css="div[class='composeYabbles']")
    private WebElement addressRecipientElement;

    @FindBy(css="input[class='composeTextField ComposeSubject-TextField']")
    private WebElement mailSubjectElement;

    @FindBy(css="div[placeholder='Напишите что-нибудь']>div")
    private WebElement mailFieldElement;

    @FindBy(css="button[class='control button2 button2_view_default button2_tone_default button2_size_l button2_theme_action button2_pin_circle-circle ComposeControlPanelButton-Button ComposeControlPanelButton-Button_action']")
    private WebElement sendButtonElement;

    @FindBy(css="span[class='mail-NestedList-Item-Info-Extras']")
    private WebElement messageCountElement;

    @FindBy(css="div[class='ComposeDoneScreen-Title']>span")
    private WebElement sendingConfirmFormElement;

    @FindBy(css="input[placeholder='Поиск']")
    private WebElement searchInMailboxElement;

    @FindBy(css="button[class='control button2 button2_view_default button2_tone_mail-suggest-themed button2_size_n button2_theme_normal button2_pin_clear-round button2_type_submit search-input__form-button']")
    private WebElement submitSearchInMailboxElement;

    @FindBy(css="span[class='mail-MessagesSearchInfo-Title_misc nb-with-xs-left-gap']")
    private WebElement messageCountBySearchElement;

    public MailboxPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @Step("Кликнуть на кнопку нового письма")
    public MailboxPage newMailClick(){
        Waiters.WaitUntilElementNotClickable(driver, newMailElement);
        newMailElement.click();
        return this;
    }
    @Step("Ввести адрес получателя")
    public MailboxPage setAddressRecipient(String address){
        Waiters.WaitUntilElementNotClickable(driver, addressRecipientElement);
        addressRecipientElement.sendKeys(address);
        return this;
    }
    @Step("Ввести тему письма")
    public MailboxPage setMailSubject(String subject){
        mailSubjectElement.sendKeys(subject);
        return this;
    }
    @Step("Ввести текст сообщения")
    public MailboxPage setMailText(String mailText){
        mailFieldElement.sendKeys(mailText);
        return this;
    }
    @Step("Кликнуть на кнопку отправки")
    public MailboxPage sendMailClick(){
        sendButtonElement.click();
        return this;
    }
    @Step("Получить общее количество писем")
    public String getValueFromMessageCountBox(){
        Waiters.WaitUntilElementNotClickable(driver, messageCountElement);
        return messageCountElement.getText();
    }
    @Step("Ввести строку в поиск")
    public MailboxPage setLineInMailboxSearch(String line){
        searchInMailboxElement.sendKeys(line);
        return this;
    }
    @Step("Начать поиск")
    public MailboxPage submitSearchInMailboxClick(){
        Waiters.WaitUntilElementNotClickable(driver, submitSearchInMailboxElement);
        submitSearchInMailboxElement.click();
        return this;
    }
    @Step("Получить количество найденных писем")
    public String getValueFoundedMail(){
        Waiters.WaitUntilElementNotClickable(driver, messageCountBySearchElement);
        return messageCountBySearchElement.getText();
    }

    @Step("Проверить открыта ли форма письма")
    public Boolean checkOpeningMailForm() {
        Waiters.WaitUntilElementNotClickable(driver, addressRecipientElement);
        return addressRecipientElement.isDisplayed();
    }

    @Step("Проверить открыта ли сообщение об отправке")
    public Boolean checkSending(){
        Waiters.WaitUntilElementNotClickable(driver, sendingConfirmFormElement);
        return sendingConfirmFormElement.isEnabled();
    }

}
