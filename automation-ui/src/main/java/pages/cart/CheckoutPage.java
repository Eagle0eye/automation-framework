package pages.cart;

import DTO.CartItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.shared.components.BasePage;
import pages.orders.PaymentPage;

import java.util.List;

public class CheckoutPage extends BasePage  {
    public String verifyAddressDetails() {
        WebElement deliveryAddressBox = driver.findElement(By.id("address_delivery"));
        List<WebElement> addressLines = deliveryAddressBox.findElements(By.tagName("li"));

        StringBuilder fullAddress = new StringBuilder();

        for (int i = 1; i < addressLines.size(); i++) {
            String lineText = addressLines.get(i).getText().trim();
            if (!lineText.isEmpty()) {
                fullAddress.append(lineText).append("\n");
            }
        }
        return fullAddress.toString();
    }

    public String reviewOrderDetails() {

        List<WebElement> productRows = driver.findElements(By.cssSelector(".cart_info > table > tbody > tr"));

        for (int i = 0; i < productRows.size() ; i++) {
            CartItem item = getProductsCheckout(productRows.get(i));

        }


            return "";
    }


    public CheckoutPage addComment(String comment) {
        return null;
    }

    public PaymentPage submitOrder() {
        return null;
    }

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }



    private CartItem getProductsCheckout(WebElement row){
        String name = row.findElement(By.cssSelector(".cart_description h4 a")).getText();
        String price = row.findElement(By.cssSelector(".cart_price p")).getText();
        String quantity = row.findElement(By.cssSelector(".cart_quantity button")).getText();
        String itemTotal = row.findElement(By.cssSelector(".cart_total_price")).getText();

        return CartItem.builder()
                .productName(name)
                .price(price)
                .quantity(quantity)
                .totalPrice(itemTotal)
                .build();
    }
}
