package pages.cores;

import DTO.ProductInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.interactions.Actions;

public class ProductPage extends BasePage {

    private List<ProductInfo> productList;
    private Actions actions;

    @FindBy(css = "#cartModal > div > div > div.modal-body > p:nth-child(1)")
    private WebElement verifyAddMessage;

    @FindBy(linkText = "View Cart")
    private WebElement viewCart;

    @FindBy(css = "#cartModal > div > div > div.modal-footer > button")
    private WebElement continueShopping;


    @FindBy(css = "div.col-sm-4") private List<WebElement> productsElements;



    public ProductPage(WebDriver driver) {
        super(driver);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
    }


    public void loadProducts() {
        productList = new ArrayList<>();
        String name = "NOT_FOUND";
        String price = "NOT_FOUND";
        for (WebElement item : productsElements) {

            try {
                actions.moveToElement(item).perform();
                WebElement product = item.findElement(By.cssSelector(".overlay-content"));

                try {
                    name = product.findElement(By.tagName("p")).getText();
                } catch (Exception e) {
                    log.warn("Name not found in products: {}", e.getMessage());
                }

                try {
                    price = product.findElement(By.tagName("h2")).getText();
                } catch (Exception e) {
                    log.warn("Price not found in product overlay: {}", e.getMessage());
                }

                String productId = product.findElement(By.cssSelector("a.add-to-cart"))
                        .getDomAttribute("data-product-id");

                log.info("Extracted: name={}, price={}, productId={}", name, price, productId);

                productList.add(ProductInfo.builder()
                        .productName(name)
                        .productPrice(price)
                        .productId(productId)
                        .build());

            } catch (Exception e) {
                log.error("Failed to extract some product info: {}", e.getMessage());
            }
        }
    }


    public List<ProductInfo> viewProductList() {
        if(Objects.isNull(productList)) return null;
        return productList;
    }

    public boolean verifyAddMessage() {
        wait.until(ExpectedConditions.visibilityOf(verifyAddMessage));
        return verifyAddMessage.isDisplayed() && verifyAddMessage.getText().contentEquals("Your product has been added to cart.");
    }

    public ProductPage selectProduct(List<String> productNames) {
        if (productList == null || productList.isEmpty()) {
            log.warn("Product list is empty or not loaded yet.");
            return this;
        }

        productNames.forEach(targetName -> {
            for (WebElement item : productsElements) {
                try {
                    actions.moveToElement(item).perform();
                    WebElement product = item.findElement(By.cssSelector(".overlay-content"));
                    String productName = product.findElement(By.tagName("p")).getText();
                    if (targetName.equals(productName)) {
                        product.findElement(By.linkText("Add to cart")).click();
                        if (verifyAddMessage()) {
                            log.info("Product '{}' added to cart successfully.", targetName);
                            continueShopping.click();
                        }
                        break;
                    }

                } catch (Exception e) {
                    log.error("Error selecting product '{}': {}", targetName, e.getMessage());
                }
            }
        });

        return this;
    }




}
