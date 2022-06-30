package StepDefinitions;

import Pages.DemoSitePage;
import Pages.ManagerHomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginStep {
    DemoSitePage demoSitePage;
    ManagerHomePage managerHomePage;
    private static WebDriver driver;

    @Given("browser is open")
    public void browserIsOpen() {
        System.out.println("Inside Step - browser is open");

        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

        Set<String> windowHandles = driver.getWindowHandles();
        List<String> windowStrings = new ArrayList<>(windowHandles);
        String reqWindow = windowStrings.get(1);
        driver.switchTo().window(reqWindow);

        driver.manage().window().maximize();
    }


    @And("user is on login page")
    public void userIsOnLoginPage() {
        System.out.println("Inside Step - user is on login page");
        driver.navigate().to("https://demo.guru99.com/v4/");
    }

    @Given("run test case: {string}")
    public void runTestCase(String testCase) {
        System.out.println("Run test case: " + testCase);
        demoSitePage = new DemoSitePage(driver);
        managerHomePage = new ManagerHomePage(driver);
    }

    @And("user click on login button as {string}")
    public void userClickOnLoginButtonAs(String key, DataTable dataTable) {
        System.out.println("Inside Step - click on login button");

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++) {
            String sKey = data.get(i).get("KEY");
            if (key.equals(sKey)) {
                if (key.equals("07")) {
                    demoSitePage.clickOnLoginBtn();
                } else {
                    demoSitePage.clickOnLoginBtn();
                    demoSitePage.waitForPageLoaded();
                }
            }
        }
    }

    @When("user enters username and password")
    public void userEntersUsernameAndPassword(DataTable dataTable) {
        System.out.println("Inside Step - user enters username and password");

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String userName = data.get(0).get("userName");
        String passWord = data.get(0).get("passWord");
        if (userName != null) {
            demoSitePage.enterUserName(userName);
        } else {
            demoSitePage.tapAction();
        }
        if (passWord != null) {
            demoSitePage.enterPassWord(passWord);
        } else {
            demoSitePage.tapAction();
        }
    }

    @Then("user is navigated to the home page as {string}")
    public void userIsNavigatedToTheHomePageAs(String key, DataTable dataTable) {
        System.out.println("Inside Step - verify information after login success");

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++) {
            String sKey = data.get(i).get("KEY");
            if (key.equals(sKey)) {
                String messageWelcome = data.get(i).get("welcomeToManager'sPage");
                if (messageWelcome != null)
                    Assert.assertEquals(messageWelcome, managerHomePage.getMessageWelcomeToManagerPage());
            }
        }
    }

    @And("close browser")
    public void closeBrowser() {
        driver.close();
        driver.quit();
    }

    @And("check error message when username and password invalid as {string}")
    public void checkErrorMessageWhenUsernameAndPasswordInvalidAs(String key, DataTable dataTable) {
        System.out.println("Inside Step - verify error message when input invalid username and password");

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++) {
            String sKey = data.get(i).get("KEY");
            if (key.equals(sKey)) {
                String message = data.get(i).get("message");
                if (message != null) {
                    Assert.assertEquals(message, demoSitePage.getErrorMessageUsernameAndPasswordInvalid());
                }
            }
        }
    }

    @And("check validate all field as {string}")
    public void checkValidateAllFieldAs(String key, DataTable dataTable) {
        System.out.println("Inside Step - check validate all field");

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++) {
            String sKey = data.get(i).get("KEY");
            if (key.equals(sKey)) {
                String type = data.get(i).get("Type");
                String message = data.get(i).get("message");

                if (type.equals("username")) {
                    if (message != null) {
                        Assert.assertEquals(message, demoSitePage.getErrMsgUserIDMustBeNotBlank());
                    }
                }

                if (type.equals("password")) {
                    if (message != null) {
                        Assert.assertEquals(message, demoSitePage.getErrMsgPasswordMustBeNotBlank());
                    }
                }
            }
        }
    }

    @And("check reset function work as {string}")
    public void checkResetFunctionWorkAs(String key, DataTable dataTable) {
        System.out.println("Inside Step - check reset function work");

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++) {
            String sKey = data.get(i).get("KEY");
            if (key.equals(sKey)) {
                System.out.println("Inside Step - check remove data in User ID field");
                Assert.assertEquals("", demoSitePage.getUserName());

                System.out.println("Inside Step - check remove data in Password field");
                Assert.assertEquals("", demoSitePage.getPassword());
            }
        }
    }

    @And("click on reset button as {string}")
    public void clickOnResetButtonAs(String key, DataTable dataTable) {
        System.out.println("Inside Step - click on reset button");

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < data.size(); i++) {
            String sKey = data.get(i).get("KEY");
            if (key.equals(sKey)) {
                demoSitePage.clickOnResetBtn();
            }
        }
    }
}
