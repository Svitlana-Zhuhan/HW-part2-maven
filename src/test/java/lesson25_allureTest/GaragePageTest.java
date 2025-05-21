package lesson25_allureTest;

import io.qameta.allure.Allure;
import jdk.jfr.Description;
import lesson25_allure.GaragePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class GaragePageTest {
    private WebDriver driver;
    private GaragePage garagePage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(description = "Guest car addition verification")
    @Description("Guest enters the page, adds cdr. Verification of correctly displayed data")
    public void testAddCarToGarage() {
        Allure.step("Enter the site as guest");
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");

        Allure.step("Click on Guest Log In button");
        driver.findElement(By.xpath("//button[contains(text(), 'Guest log in')]")).click();

        Allure.step("Verification of navigation to Garage page");
        String currentUrl = driver.getCurrentUrl();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(currentUrl, "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage");

        garagePage = new GaragePage(driver);

        Allure.step("Click on Add Car Button");
        garagePage.ClickAddCar();

        Allure.step("Select car details");
        garagePage.selectCarDetails("Audi", "TT", 20);

        Allure.step("Click on Add button");
        garagePage.clickSubmitButton();

        Allure.step("Verify car data displayed correctly");
        garagePage.getCarName("Audi", "TT", 20);

        Allure.step("Verify Update mileage displays today date");
        garagePage.isTodayMileageDateDisplayed();

        Allure.step("Verify car image displayed");
        garagePage.isCarImageDisplayed();

        Allure.step("Verify the car image url ends with -src");
        garagePage.imageUrlEndsWith("src");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
