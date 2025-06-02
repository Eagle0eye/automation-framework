package services;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.cores.CartPage;
import utils.ProductFilter;

import java.time.Duration;
import java.util.List;
import java.util.Map;


public class ProductsService {

    // Initialization
    private static final Logger log = LoggerFactory.getLogger(ProductsService.class);
    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;
    private final ProductFilter productFilter;

    public ProductsService(WebDriver driver) {
       this.driver = driver;
       this.actions = new Actions(driver);
       this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
       this.productFilter = new ProductFilter(driver);
    }

    public ProductFilter filter(){
        return productFilter;
    }

    @Step("Add Product to Cart")
    public void addToCart(String productName,int quantity) {
        this.addToCart(Map.of(productName, quantity));
    }

    @Step("Add some Products to Cart")
    public void addToCart(Map<String,Integer> productList) {

        if (productList == null || productList.isEmpty()) {
            log.warn("ProductSearch list is empty or not loaded yet.");
            throw new RuntimeException("ProductSearch list is empty or not loaded yet.");
        }

        List<WebElement> productsElements = driver.findElements(By.cssSelector("div.col-sm-4"));

        int entryIndex = 0;
        int totalEntries = productList.size();

        for (Map.Entry<String, Integer> targetName : productList.entrySet()) {
            entryIndex++;
            for (WebElement item : productsElements) {
                try {
                    actions.moveToElement(item).perform();
                    WebElement product = item.findElement(By.cssSelector(".overlay-content"));
                    String productName = product.findElement(By.tagName("p")).getText();
                    if (targetName.getKey().equals(productName)) {
                        for (int times = 0; times < targetName.getValue(); times++) {
                            boolean isLast = (entryIndex == totalEntries) && (times == targetName.getValue() - 1);
                            if (isLast) {
                                continue;
                            }
                            continueShopping(product);
                            log.info("Product: {} added to cart successfully.", targetName.getKey());
                        }
                    }
                } catch (Exception e) {
                    log.error("Error selecting product '{}': {}", targetName.getKey(), e.getMessage());
                }
            }
        }

    }


    @Step("Ensure the the Product added to Cart")
    public boolean verifyAddMessage() {
        WebElement verifyAddMessage = driver.findElement(By.cssSelector("#cartModal > div > div > div.modal-body > p:nth-child(1)"));
        wait.until(ExpectedConditions.visibilityOf(verifyAddMessage));
        return verifyAddMessage.isDisplayed() && verifyAddMessage.getText().contentEquals("Your product has been added to cart.");
    }

    @Step("Continue Shopping")
    public void continueShopping(WebElement product) {
        product.findElement(By.linkText("Add to cart")).click();
        WebElement continueShopping = driver.findElement(By.cssSelector("#cartModal > div > div > div.modal-footer > button"));
        if (verifyAddMessage()) {
            continueShopping.click();
        }
    }

    @Step("View Cart")
    public CartPage viewCart(WebElement product) {
        WebElement viewCart = product.findElement(By.linkText("View Cart"));
        viewCart.click();
        return new CartPage(driver);
    }

}
