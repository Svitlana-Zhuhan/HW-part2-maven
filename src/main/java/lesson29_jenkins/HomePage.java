package lesson29_jenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {


    private final WebDriver driver;

    @FindBy(xpath = "//button[contains(text(), 'Guest log in')]")
    private WebElement guestLoginButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public GaragePage clickGuestLogin() {
        guestLoginButton.click();
        return new GaragePage(driver);
    }

   /* public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void guestLogin() {
        driver.findElement(By.xpath("//button[contains(text(), 'Guest log in')]")).click();
    }*/
}
