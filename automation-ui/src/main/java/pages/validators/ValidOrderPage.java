package pages.validators;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.cores.HomePage;

public class ValidOrderPage extends BasePage {
    public ValidOrderPage(WebDriver driver) {
        super(driver);
    }

    public ValidOrderPage downloadInvoice() {
        return this;
    }

    public String verifyOrderSuccess() {
        return "";
    }

    public HomePage continueToHomePage() {
        return new HomePage(driver);
    }
}
