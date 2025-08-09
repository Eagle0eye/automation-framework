package components;

import DTO.ProductInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class RecommendedProducts {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    private final ModelDialog modal ;
    public RecommendedProducts(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        modal = new ModelDialog(driver);
    }

    public void selectProducts(Set<ProductInfo> products) {
        for (ProductInfo product : products) {
            boolean found = trySelectProduct(product.getName());
            if (!found) {
                nextBar(); // Move to next carousel page
                found = trySelectProduct(product.getName());
            }
            if (!found) {
                System.out.println("Product not found in carousel: " + product.getName());
            }
        }
    }

    private boolean trySelectProduct(String productName) {
        List<WebElement> items = driver.findElements(By.cssSelector(".recommended_items .productinfo"));
        for (WebElement item : items) {
            try {
                WebElement nameElement = item.findElement(By.tagName("p"));
                if (nameElement.getText().trim().equalsIgnoreCase(productName.trim())) {
                    WebElement addToCart = item.findElement(By.cssSelector("a.add-to-cart"));
                    wait.until(ExpectedConditions.elementToBeClickable(addToCart));
                    actions.moveToElement(addToCart).click().perform();
                    modal.continueButton();
                    System.out.println("Added to cart: " + productName);
                    return true;
                }
            } catch (NoSuchElementException ignored) {}
        }
        return false;
    }

    private void nextBar() {
        try {
            WebElement next = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".recommended-item-control.right")));
            actions.moveToElement(next).click().perform();
        } catch (Exception e) {
            System.out.println("⚠️ Failed to click next carousel arrow: " + e.getMessage());
        }
    }

    private void previousBar() {
        try {
            WebElement prev = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".recommended-item-control.left")));
            actions.moveToElement(prev).click().perform();
        } catch (Exception e) {
            System.out.println("⚠️ Failed to click previous carousel arrow: " + e.getMessage());
        }
    }
}
