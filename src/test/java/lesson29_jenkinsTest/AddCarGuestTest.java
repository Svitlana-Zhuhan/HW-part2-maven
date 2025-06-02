package lesson29_jenkinsTest;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import lesson29_jenkins.GaragePage;
import lesson29_jenkins.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;

@Epic("Garage")
@Feature("Add Car")
@Owner("student")
@Severity(SeverityLevel.CRITICAL)
@Link(name = "QAuto", url = "https://qauto.forstudy.space/")
public class AddCarGuestTest {

    WebDriver driver;
    HomePage homePage;
    GaragePage garagePage;


    @BeforeMethod
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        RemoteWebDriver driver = new RemoteWebDriver(
                new URL("http://chrome:8080/wd/hub"),
                options);
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        homePage = new HomePage(driver);
        garagePage = new GaragePage(driver);
    }

    @Test(description = "Перевірка додавання авто з профілем гість")
    @Description("Тест перевіряє успішне додавання Audi TT з пробігом 20")
    public void testAddCarAsGuest(@Optional("chrome") String browser) {
        SoftAssert softAssert = new SoftAssert();

        homePage.clickGuestLogin();
        softAssert.assertTrue(driver.getCurrentUrl().contains("/panel/garage"), "URL is incorrect");

        garagePage.clickAddCarButton();
        garagePage.selectCarDetails("Audi", "TT", Integer.parseInt("20"));
        garagePage.clickSubmitButton();

        softAssert.assertEquals(garagePage.getCarName(), "Audi TT", "Car name not displayed correctly");
        softAssert.assertTrue(garagePage.isTodayMileageDateDisplayed(), "Current date not shown in mileage update");
        softAssert.assertEquals(garagePage.getMileageValue(), "20", "Mileage value is incorrect");
        softAssert.assertTrue(garagePage.isCarImageDisplayed(), "Car image not displayed");
        softAssert.assertTrue(garagePage.imageUrlEndsWith("audi.png"), "Image source does not end with audi.png");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
