package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManagerHomePage {
    protected WebDriver driver;

    final String LB_WELCOME_TO_MANAGER = "//tr[@class='heading3']/td";

    public ManagerHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getMessageWelcomeToManagerPage() {
        return elem(LB_WELCOME_TO_MANAGER).getText();
    }

    private void webDriverWait(String locator) {
        By elm = By.xpath(locator);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elm));
    }

    private WebElement elem(String locator) {
        webDriverWait(locator);
        WebElement we = driver.findElement(By.xpath(locator));
        return we;
    }
}
