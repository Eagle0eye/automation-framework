package components;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Scrolling {

    private static final Logger log = LoggerFactory.getLogger(Scrolling.class);
    private final WebDriver driver;

    public Scrolling(WebDriver driver) {
        this.driver = driver;
    }

    public void downWithMouse() {
        scrollWithMouse("window.scrollBy(0, window.innerHeight)");
        log.info("Scrolled down with mouse.");
    }

    public void upWithMouse() {
        scrollWithMouse("window.scrollTo({ top: 0, behavior: 'smooth' });");
        log.info("Scrolled up with mouse.");
    }

    public void downWithArrow() {
        scrollWithKeyboard(Keys.ARROW_DOWN);
        log.info("Scrolled down using arrow key.");
    }

    public void upWithArrow() {
        scrollWithKeyboard(Keys.ARROW_UP);
        log.info("Scrolled up using arrow key.");
    }

     public void scrollToTopByArrowUp() {
        String XPATH = "//a[@id='scrollUp']";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        try {
            WebElement arrowUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH)));
            log.info("Arrow up is visible, clicking...");
            arrowUp.click();
        } catch (TimeoutException e) {
            log.warn("Arrow up is not visible within the timeout.");
        } catch (ElementNotInteractableException e) {
            log.error("Arrow up is present but could not be clicked: {}", e.getMessage());
        } catch (NoSuchElementException e) {
            log.error("Arrow up element not found: {}", e.getMessage());
        }
    }

     private void scrollWithMouse(String script) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(script);
        } catch (Exception e) {
            log.error("Error while scrolling with mouse: {}", e.getMessage());
        }
    }

    private void scrollWithKeyboard(Keys key) {
        try {
            driver.findElement(By.tagName("body")).sendKeys(key);
        } catch (NoSuchElementException e) {
            log.error("Unable to send key to body tag: {}", e.getMessage());
        }
    }
}
