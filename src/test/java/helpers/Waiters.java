package helpers;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {
    private Waiters (){

    }

    public static void WaitUntilElementNotClickable(final WebDriver driver,
                                                    final WebElement element){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(element));
    }
}
