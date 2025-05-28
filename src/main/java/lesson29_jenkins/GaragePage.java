package lesson29_jenkins;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GaragePage {

    private final WebDriver driver;
    private WebDriverWait wait;

    private final By addCarButton = By.xpath("//button[@class='btn btn-primary' and contains(text(), 'Add car')]");
    private final By addCarBrand = By.id("addCarBrand");
    private final By addCarModel = By.id("addCarModel");
    private final By mileageUpdateParagraph = By.cssSelector("p.car_update-mileage");
    private final By mileageInput = By.id("addCarMileage");
    private final By carLogoImage = By.cssSelector(".car-logo_img");
    private final By carTitle = By.cssSelector(".car_name.h2");
    private final By updateMileageInput = By.cssSelector(".update-mileage-form_input.form-control");

    private String dropdownOptionXpath = "//option";

    private final By addButton = By.xpath("//button[contains(@class, 'btn-primary') and normalize-space(text())='Add']");

    public GaragePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickAddCarButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addCarButton)).click();
    }

    public void selectCarDetails(String brand, String model, int mileage) {
        wait.until(ExpectedConditions.elementToBeClickable(addCarBrand)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format(dropdownOptionXpath, brand)))).click();

        wait.until(ExpectedConditions.elementToBeClickable(addCarModel)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format(dropdownOptionXpath, model)))).click();

        WebElement mileageField = wait.until(ExpectedConditions.visibilityOfElementLocated(mileageInput));
        mileageField.clear();
        mileageField.sendKeys(String.valueOf(mileage));
    }

    public void clickSubmitButton() {
        driver.findElement(addButton).click();
    }

    public String getCarName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(carTitle)).getText();
    }

    public boolean isTodayMileageDateDisplayed() {
        String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String text = driver.findElement(mileageUpdateParagraph).getText();
        return  text.contains(expectedDate);
    }

    public String getMileageValue() {
        return driver.findElement(updateMileageInput).getAttribute("value");
    }

    public boolean isCarImageDisplayed() {
        return  driver.findElement(carLogoImage).isDisplayed();
    }

    public boolean imageUrlEndsWith(String suffix) {
        String src = driver.findElement(carLogoImage).getAttribute("src");
        return src.endsWith(suffix);
    }
   /* public GaragePage(WebDriver driver) {
        super(driver);
    }

    public void clickAddCarButton() {
        driver.findElement(By.xpath("//button[@class='btn btn-primary' and contains(text(), 'Add car')]")).click();
    }

    public void fillCarForm(String brand, String model, String mileage) {
        selectByVisibleText(By.id("addCarBrand"), brand);
        selectByVisibleText(By.id("addCarModel"), model);
        driver.findElement(By.id("addCarMileage")).sendKeys(mileage);
    }

    public void submitAddCar() {
        driver.findElement(By.xpath("//button[contains(@class, 'btn-primary') and normalize-space(text())='Add']")).click();
    }

    public boolean isCarDisplayed(String fullName) {
        return driver.getPageSource().contains(fullName);
    }

    public boolean isMileageCorrect(String mileage) {
        return driver.findElement(By.cssSelector(".update-mileage-form_input.form-control")).getAttribute("value").equals(mileage);
    }

    public boolean isTodayDisplayed() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return driver.getPageSource().contains(today);
    }

    public boolean isCarImageVisible() {
        return driver.findElement(By.cssSelector(".car_logo img")).isDisplayed();
    }

    public boolean isImageUrlCorrect() {
        return driver.findElement(By.cssSelector(".car-logo_img"))
                .getAttribute("src").endsWith("audi.png");
    }*/
}
