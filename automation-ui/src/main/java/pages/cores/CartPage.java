package pages.cores;

import Cache.CacheService;
import Cache.DTO.ProductCache;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.interfaces.ICartPage;

import java.util.List;
import java.util.Map;

import static Cache.CacheService.removeProductCache;

public class CartPage extends BasePage implements ICartPage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutPage proceedToCheckout() {
        WebElement checkout = driver.findElement(By.linkText("Proceed To Checkout"));
        checkout.click();
        log.info("Proceeding to checkout");
        return new CheckoutPage(driver);
    }

    @Override
    public String isCartEmpty() {
        WebElement emptyCart =  driver.findElement(By.id("empty_cart"));
        WebElement textElement = emptyCart.findElement(By.tagName("p"));
        return textElement.getText();
    }

    @Override
    public String verifyDisplayedCart() {
        try {
            WebElement breadcrumb = driver.findElement(By.cssSelector(".breadcrumbs .breadcrumb li.active"));
            return breadcrumb.getText().trim();
        } catch (Exception e) {
            log.error("Breadcrumb verification failed", e);
        }
        return null;
    }

    @Override
    public boolean verifyItems(Map<String, Integer> items) {
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));

        for (WebElement row : rows) {
            try {
                ProductCache uiProduct = extractProductFromRow(row);

                if (verifyProductMatchesCache(uiProduct.getProductName(), uiProduct.getQuantity(), uiProduct.getPrice() * uiProduct.getQuantity())) {
                    log.warn("Mismatch for product {}: UI quantity {}, UI total {}, doesn't match cache",
                            uiProduct.getProductName(), uiProduct.getQuantity(), uiProduct.getPrice() * uiProduct.getQuantity());
                    return false;
                }

                if (items.containsKey(uiProduct.getProductName()) && !items.get(uiProduct.getProductName()).equals(uiProduct.getQuantity())) {
                    log.warn("Expected quantity mismatch for product {}: expected {}, found {}",
                            uiProduct.getProductName(), items.get(uiProduct.getProductName()), uiProduct.getQuantity());
                    return false;
                }

            } catch (Exception e) {
                log.error("Error verifying product row", e);
                return false;
            }
        }

        return true;
    }

    @Override
    public CartPage cancelProduct(String productName) {
        List<WebElement> productRows = driver.findElements(By.cssSelector("tbody tr"));

        for (WebElement row : productRows) {
            ProductCache uiProduct = extractProductFromRow(row);

            if (uiProduct.getProductName().equalsIgnoreCase(productName)) {
                log.info("Verifying product '{}' before removal...", uiProduct.getProductName());

                if (verifyProductMatchesCache(uiProduct.getProductName(), uiProduct.getQuantity(), uiProduct.getPrice() * uiProduct.getQuantity())) {
                    log.warn("Product '{}' in UI does not match cached data. Skipping deletion.", uiProduct.getProductName());
                    return this;
                }

                WebElement deleteButton = row.findElement(By.cssSelector(".cart_quantity_delete"));
                deleteButton.click();
                removeProductCache(uiProduct.getProductName());
                log.info("Removed product '{}' from UI and cache", uiProduct.getProductName());
                break;
            }
        }
        return this;
    }

    @Override
    public ProductPage gotoProductPageWithHere() {
        WebElement productPage = driver.findElement(By.linkText("here"));
        productPage.click();
        return new ProductPage(driver);
    }

    private boolean verifyProductMatchesCache(String productName, int uiQuantity, int uiTotalPrice) {
        for (ProductCache cached : CacheService.getSavedProducts()) {
            if (cached.getProductName().equalsIgnoreCase(productName)) {
                return cached.getQuantity() == uiQuantity && (cached.getPrice() * cached.getQuantity()) == uiTotalPrice;
            }
        }
        return false;
    }

    private ProductCache extractProductFromRow(WebElement row) {
        String name = row.findElement(By.cssSelector(".cart_description h4 a")).getText().trim();
        int quantity = Integer.parseInt(row.findElement(By.cssSelector(".cart_quantity button")).getText().trim());
        int totalPrice = Integer.parseInt(row.findElement(By.cssSelector(".cart_total_price")).getText().replaceAll("[^0-9]", ""));

        return ProductCache.builder().productName(name).price(totalPrice).quantity(quantity).build();
    }

}
