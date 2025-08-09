package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ModelDialog {

    private static final Logger log = LoggerFactory.getLogger(ModelDialog.class);

    private final WebDriverWait wait;

    public ModelDialog(WebDriver driver) {
        int DEFAULT_TIMEOUT_SECONDS = 10;
        wait = new WebDriverWait( driver, Duration.ofMillis(DEFAULT_TIMEOUT_SECONDS));

    }
    public String getTitle() {
        String TITLE_XPATH = "//div[@class='modal-header']//h4[contains(@class,'modal-title')]";
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE_XPATH)));
        return title.getText();
    }
    public String getMessage() {
        String MESSAGE_XPATH = "//div[@class='modal-body']//p";
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MESSAGE_XPATH)));
        if (message.isDisplayed()) {
            log.info("Message: {}", message.getText());
        }
        return message.getText();
    }

    public void goToLink() {
        String LINK_XPATH = "//div[@class='modal-body']//a";
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LINK_XPATH)));
        link.click();
    }

    public void continueButton() {
        String BUTTON_XPATH = "//div[@class='modal-footer']//button[contains(@class,'close-modal')]";
        WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BUTTON_XPATH)));
        continueButton.click();
    }
}
