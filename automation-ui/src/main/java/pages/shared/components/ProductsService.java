package pages.shared.components;

import models.ProductCache;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.cart.CartPage;
import pages.products.ProductPage;

import java.time.Duration;
import java.util.*;

import static services.CartService.getCartItems;


public class ProductsService  {

    // Initialization
    private static final Logger log = LoggerFactory.getLogger(ProductsService.class);
    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;

    public ProductsService(WebDriver driver) {
       this.driver = driver;
       this.actions = new Actions(driver);
       this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Add Product to Cart")
    public ProductsService addToCart(String productName,int quantity) {
        return this.addToCart(Map.of(productName, quantity));
    }



    @Step("Ensure the the Product added to Cart")
    public boolean verifyAddMessage() {
        WebElement verifyAddMessage = driver.findElement(By.cssSelector("#cartModal > div > div > div.modal-body > p:nth-child(1)"));
        wait.until(ExpectedConditions.visibilityOf(verifyAddMessage));
        return verifyAddMessage.isDisplayed() && verifyAddMessage.getText().contentEquals("Your product has been added to tests.cart.");
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
    @Step("Add selected products")
    public ProductsService addToCart(Map<String, Integer> products) {

        //show products and quantity for each one
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            log.info("Product: {} - Quantity: {}", entry.getKey(), entry.getValue());
        }

        // save all web elements to do actions on
        List<WebElement> matchedCards = new ArrayList<>();
        for (String name : products.keySet()) {
            WebElement matchedCard = driver.findElement(By.xpath(
                    "//div[contains(@class,'single-products') and .//div[contains(@class,'productinfo')]//p[normalize-space()='%s']]"
                            .formatted(name)));
            matchedCards.add(matchedCard);
        }




        int prodIndex=0;

        for (Map.Entry<String, Integer> product : products.entrySet()) {
            WebElement matchedCard = matchedCards.get(prodIndex);


            try {
                for (int q = 0; q < product.getValue(); q++) {
                    actions.moveToElement(matchedCard).perform();
                    WebElement overlay = wait.until(ExpectedConditions.visibilityOf(
                            matchedCard.findElement(By.cssSelector(".product-overlay"))
                    ));

                    WebElement addToCart = overlay.findElement(By.cssSelector("a.add-to-cart"));

                    actions.moveToElement(addToCart).perform();
                    wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
                    log.info("Added '{}' to tests.cart (qty: {}/{})", product.getKey(), q + 1, product.getValue());


                    boolean isLastProduct = (prodIndex == products.size() - 1);
                    boolean isLastQuantity = (q == product.getValue() - 1);

                    if (!(isLastProduct && isLastQuantity)) {
                        waitForCartModal();
                        continueShopping();
                        waitForCartModalToDisappear();
                    }
                    if (q == product.getValue() - 1)
                        log.info("Product #{} Added", prodIndex + 1);
                }

            } catch (Exception e) {
                log.error("Error adding Product #{} to tests.cart.\nError: {}", prodIndex, e.getMessage());

            }
            prodIndex++;

        }
        return this;
    }



    @Step("View Cart")
    public CartPage viewCart() {

        WebElement viewCartLink = driver.findElement(By.linkText("View Cart"));
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();

        return new CartPage(driver);
    }

    public ProductPage viewProduct() {
        List<ProductCache> cached = getCartItems().stream().toList();
        Random random = new Random();
        ProductCache productCache =  cached.get(random.nextInt(cached.size()));
        gotoProductPage(productCache.getProductName());
        return new ProductPage(driver);
    }

    public ProductPage viewProduct(String productName) {
        gotoProductPage(productName);
        return new ProductPage(driver);
    }


    // helper functions
    private void waitForCartModalToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("cartModal")));
        } catch (Exception e) {
            log.warn("Cart modal did not disappear in time.");
        }
    }

    private void waitForCartModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));
    }


    private void gotoProductPage(String productName) {
        String xpath = "//p[normalize-space(text())='" + productName + "']/ancestor::div[contains(@class,'product-image-wrapper')]//a[contains(@href,'/product_details')]";
        WebElement productLink = driver.findElement(By.xpath(xpath));
        productLink.click();
    }

}
