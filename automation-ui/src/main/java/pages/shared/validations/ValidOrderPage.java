package pages.shared.validations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.shared.components.BasePage;
import pages.HomePage;

public class ValidOrderPage extends BasePage {
    public ValidOrderPage(WebDriver driver) {
        super(driver);
    }

    public ValidOrderPage downloadInvoice() {
        return this;
    }

    public String verifyOrderSuccess() {
        String success_xpath = "//div[@id='success_message']";
        WebElement successMessage = driver.findElement(By.xpath(success_xpath));
        return successMessage.getText();
    }

    public HomePage continueToHomePage() {
        return new HomePage(driver);
    }
}
