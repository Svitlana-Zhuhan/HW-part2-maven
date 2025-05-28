package lesson32_DDTBrowserFactoryTest;

import lesson32_DDTBrowserFactory.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;

    @DataProvider(name = "browsers")
    public Object[][] browsers() {
        return new Object[][]{
                {"chrome"},
                {"firefox"}
        };
    }

    @Test(dataProvider = "browsers")
    public void testInvalidLogin(String browser) {
        driver = BrowserFactory.getDriver(browser);
        SoftAssert softAssert = new SoftAssert();

        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");

        driver.findElement(By.cssSelector("button.header_signin")).click();

        driver.findElement(By.id("signinEmail")).sendKeys("test@hillel.ua");

        driver.findElement(By.id("signinPassword")).sendKeys("1111");

        driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[@class='alert alert-danger' and text()='Wrong email or password']")
        ));

        softAssert.assertTrue(errorMsg.isDisplayed(), "Error message is not displayed");
        softAssert.assertEquals(errorMsg.getText().trim(), "Wrong email or password", "Error message text mismatch");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

