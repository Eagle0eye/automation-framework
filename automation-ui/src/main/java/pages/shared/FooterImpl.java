package pages.shared;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FooterImpl implements IFooter {

    private final WebDriverWait wait;

    public  FooterImpl(WebDriver driver) {
        int DEFAULT_TIMEOUT = 10;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
     @Override
    public String verifyTextSUBSCRIPTION() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[normalize-space()='Subscription']")
        ));
        return header.getText();
    }

    @Override
    public FooterImpl enterEmailToSubscribe(String email) {
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("susbscribe_email")
        ));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@id='subscribe']")
        ));
        submitButton.click();
        return this;
    }

    @Override
    public String verifyEmailSubscribed() {
        WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='success-subscribe']//div[contains(@class, 'alert-success')]")
        ));
        return successAlert.getText();
    }
}
