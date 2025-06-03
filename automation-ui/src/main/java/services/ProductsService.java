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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProductsService {

    // Initialization
    private static final Logger log = LoggerFactory.getLogger(ProductsService.class);
    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;
    private final ProductFilter productFilter;
    private WebElement currentProduct;

    public ProductsService(WebDriver driver) {
       this.driver = driver;
       this.actions = new Actions(driver);
       this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       this.productFilter = new ProductFilter(driver);
    }

    public ProductFilter filter(){
        return productFilter;
    }

    @Step("Add Product to Cart")
    public ProductsService addToCart(String productName,int quantity) {

        return this.addToCart(Map.of(productName, quantity));
    }

    @Step("Add selected products")
    public ProductsService addToCart(Map<String, Integer> productList) {
        if (productList == null || productList.isEmpty()) {
            log.warn("Product list is empty.");
            throw new RuntimeException("Product list is empty.");
        }

        List<WebElement> productCards = driver.findElements(By.cssSelector("div.col-sm-4"));
        List<WebElement> matchedCards = new ArrayList<>();

        for (WebElement card : productCards) {
            try {
                actions.moveToElement(card).perform();
                WebElement overlay = card.findElement(By.cssSelector(".overlay-content"));
                String productName = overlay.findElement(By.tagName("p")).getText();

                if (productList.containsKey(productName)) {
                    matchedCards.add(card); // store the full card, not overlay
                    log.info("Matched product: {}", productName);
                }

                if (matchedCards.size() == productList.size()) {
                    break;
                }
            } catch (Exception e) {
                log.error("Error reading product card: {}", e.getMessage());
            }
        }
        int count = 0;
        for (WebElement card : matchedCards) {
            actions.moveToElement(card).perform();
            WebElement overlay = wait.until(ExpectedConditions.visibilityOf(
                    card.findElement(By.cssSelector(".overlay-content"))
            ));

            String name = overlay.findElement(By.tagName("p")).getText();
            int quantity = productList.get(name);
            count++;
            for (int i = 0; i < quantity; i++) {
                waitForCartModalToDisappear();
                currentProduct = overlay;
                confirmAddToCart();
                log.info("Added product '{}' to cart ({}/{})", name, i + 1, quantity);

                if (i < quantity - 1 || count != productList.size())
                {
                    continueShopping();
                    actions.moveToElement(card).perform();
                }

            }
        }

        return this;
    }


    @Step("Ensure the the Product added to Cart")
    public boolean verifyAddMessage() {
        WebElement verifyAddMessage = driver.findElement(By.cssSelector("#cartModal > div > div > div.modal-body > p:nth-child(1)"));
        wait.until(ExpectedConditions.visibilityOf(verifyAddMessage));
        return verifyAddMessage.isDisplayed() && verifyAddMessage.getText().contentEquals("Your product has been added to cart.");
    }

    @Step("Continue Shopping")
    public ProductsService continueShopping() {
        By model = new By.ById("cartModal");
        WebElement cartModel = wait.until(ExpectedConditions.visibilityOfElementLocated(model));
        if (verifyAddMessage()) {
            WebElement continueShopping = cartModel.findElement(By.cssSelector("div > div > div.modal-footer > button"));
            continueShopping.click();
        }
        return this;
    }

    private void confirmAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(currentProduct.findElement(By.linkText("Add to cart")))).click();
    }

    @Step("View Cart")
    public CartPage viewCart() {

        WebElement viewCartLink = driver.findElement(By.linkText("View Cart"));
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();

        return new CartPage(driver);
    }


    private void waitForCartModalToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("cartModal")));
        } catch (Exception e) {
            log.warn("Cart modal did not disappear in time.");
        }
    }

}
