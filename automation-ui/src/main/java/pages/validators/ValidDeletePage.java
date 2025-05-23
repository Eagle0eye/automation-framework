package testing.pages.validators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testing.pages.BasePage;
import testing.pages.cores.HomePage;

public class ValidDeletePage extends BasePage {

    @FindBy(tagName = "h2") private WebElement deleteMessage;
    @FindBy(linkText = "Continue") private WebElement continueButton;

    public ValidDeletePage(WebDriver driver) {
        super(driver);
    }

    public String getDeleteMessage() {
        String text = deleteMessage.getText();
        log.info("Getting verified deleting message");
        return text;
    }

    public HomePage continueToHome() {
        continueButton.click();
        log.info("Going back to HomePage");
        return new HomePage(driver);
    }
}
