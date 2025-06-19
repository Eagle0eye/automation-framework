package pages.cart;

import models.ProductCache;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.shared.components.BasePage;
import pages.products.ProductPage;

import java.util.List;
import java.util.Map;

import static services.CartService.getCartItems;
import static services.CartService.removeFromCart;

public class CartPage extends BasePage  {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage proceedToCheckout() {
        WebElement checkout = driver.findElement(By.linkText("Proceed To Checkout"));
        checkout.click();
        log.info("Proceeding to checkout");
        return new CheckoutPage(driver);
    }

    public String isCartEmpty() {

        By emptyCartLocator = By.cssSelector("#empty_cart > p");
        WebElement emptyCart = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartLocator));
        return emptyCart.getText();
    }


    public String verifyDisplayedCart() {
        try {
            WebElement breadcrumb = driver.findElement(By.cssSelector(".breadcrumbs .breadcrumb li.active"));
            return breadcrumb.getText().trim();
        } catch (Exception e) {
            log.error("Breadcrumb verification failed", e);
        }
        return null;
    }

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

    public CartPage cancelProduct(String productName) {
        List<WebElement> productRows = driver.findElements(By.cssSelector("tbody tr"));

        for (WebElement row : productRows) {
            ProductCache uiProduct = extractProductFromRow(row);

            if (uiProduct.getProductName().equalsIgnoreCase(productName)) {
                log.info("Verifying product '{}' before removal...", uiProduct.getProductName());

                if (verifyProductMatchesCache(uiProduct.getProductName(), uiProduct.getQuantity(), uiProduct.getPrice() * uiProduct.getQuantity())) {
                    log.warn("Product '{}' in UI does not match cached data. Skipping deletion.", uiProduct.getProductName());
                    return new CartPage(driver);
                }

                WebElement deleteButton = row.findElement(By.cssSelector(".cart_quantity_delete"));
                deleteButton.click();
                removeFromCart(uiProduct.getProductName());
                log.info("Removed product '{}' from UI and cache", uiProduct.getProductName());
                break;
            }
        }
        return this;
    }

    public ProductPage gotoProductPageWithHere() {
        WebElement productPage = driver.findElement(By.linkText("here"));
        productPage.click();
        return new ProductPage(driver);
    }

    private boolean verifyProductMatchesCache(String productName, int uiQuantity, int uiTotalPrice) {
        for (ProductCache cached : getCartItems()) {
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
