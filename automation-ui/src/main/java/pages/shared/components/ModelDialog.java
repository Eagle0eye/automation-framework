package pages.shared.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModelDialog {
    private final Wait wait;
    public ModelDialog(WebDriver driver) {
        wait = new WebDriverWait( driver, Duration.ofMillis(10));

    }
    public String getMessage() {
        return "";
    }

    public void goToLink() {

    }

    public void continueButton() {

    }
}
