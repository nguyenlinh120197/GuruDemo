package Pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoSitePage {
    protected WebDriver driver;

    final String TXT_USER_ID = "//input[@name='uid']";
    final String TXT_PASSWORD = "//input[@name='password']";
    final String BTN_LOGIN = "//input[@name='btnLogin']";
    final String LB_MSG_USER_ID_MUST_BE_NOT_BLANK = "//label[@id='message23']";
    final String LB_MSG_PASSWORD_MUST_BE_NOT_BLANK = "//label[@id='message18']";
    final String BTN_RESET = "//input[@name='btnReset']";

    public DemoSitePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUserName(String userName) {
        elem(TXT_USER_ID).sendKeys(userName);
    }

    public void enterPassWord(String passWord) {
        elem(TXT_PASSWORD).sendKeys(passWord);
    }

    public void clickOnLoginBtn() {
        elem(BTN_LOGIN).click();
    }

    public String getErrorMessageUsernameAndPasswordInvalid() {
        String errMsgUsernameAndPasswordInvalid = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return errMsgUsernameAndPasswordInvalid;
    }

    public void tapAction() {
        elem(TXT_USER_ID).sendKeys(Keys.TAB);
    }

    public String getErrMsgUserIDMustBeNotBlank() {
        return elem(LB_MSG_USER_ID_MUST_BE_NOT_BLANK).getText();
    }

    public String getErrMsgPasswordMustBeNotBlank() {
        return elem(LB_MSG_PASSWORD_MUST_BE_NOT_BLANK).getText();
    }

    public String getUserName() {
        return elem(TXT_USER_ID).getText();
    }

    public String getPassword() {
        return elem(TXT_PASSWORD).getText();
    }

    public void clickOnResetBtn() {
        elem(BTN_RESET).click();
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

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

}
