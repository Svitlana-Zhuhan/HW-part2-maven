package lesson29_jenkins;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCarPopup {

    private final WebDriver driver;

    @FindBy(id = "addCarBrand")
    private WebElement brandDropdown;

    @FindBy(id = "addCarModel")
    private WebElement modelDropdown;

    @FindBy(id = "addCarMileage")
    private WebElement mileageInput;

    @FindBy(xpath = "//button[contains(@class, 'btn-primary') and normalize-space(text())='Add']")
    private WebElement addButton;

    public AddCarPopup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickBrandDropdown() {
        brandDropdown.click();
    }

    public void selectBrand(String brand) {
        Select brandSelect = new Select(brandDropdown);
        brandSelect.selectByVisibleText(brand);
    }

    public void clickModelDropdown() {
        modelDropdown.click();
    }

    public void selectModel(String model) {
        Select modelSelect = new Select(modelDropdown);
        modelSelect.selectByVisibleText(model);
    }

    public void enterMileage(String mileage) {
        mileageInput.sendKeys(mileage);
    }

    public GaragePage clickAddButton() {
        addButton.click();
        return new GaragePage(driver);
    }

    public void addCar(String brand, String model, String mileage) {
        selectBrand(brand);
        selectModel(model);
        enterMileage(mileage);
        clickAddButton();
    }
    /*protected WebDriver driver;

    public AddCarPopup(WebDriver driver) {
        this.driver = driver;
    }

    public void selectByVisibleText(By locator, String text) {
        new Select(driver.findElement(locator)).selectByVisibleText(text);
    }*/
}
