package pages.validators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.cores.HomePage;

public class ValidRegisterPage extends BasePage {

    @FindBy(tagName = "h2") private WebElement createdAccount;
    @FindBy(linkText = "Continue") private WebElement continueButton;

    public ValidRegisterPage(WebDriver driver) {
        super(driver);
    }
    public String verify() {
        String createdAccount = this.createdAccount.getText();
        log.info("Verify Created Account");
        return createdAccount;
    }
    public HomePage continueToHome() {
        continueButton.click();
        log.info("Continue to Home");
        return new HomePage(driver);
    }
}
