package pages.cores;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.interfaces.ICheckoutPage;

import java.util.List;

public class CheckoutPage extends BasePage implements ICheckoutPage {
    @Override
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

    @Override
    public String reviewOrderDetails() {

        List<WebElement> productRows = driver.findElements(By.cssSelector(".cart_info > table > tbody > tr"));

        for (int i = 0; i < productRows.size() ; i++) {
            String productName = productRows.get(i).findElement(By.cssSelector(".cart_description h4 a")).getText();
            String category = productRows.get(i).findElement(By.cssSelector(".cart_description p")).getText();
            String price = productRows.get(i).findElement(By.cssSelector(".cart_price p")).getText();
            String quantity = productRows.get(i).findElement(By.cssSelector(".cart_quantity button")).getText();
            String itemTotal = productRows.get(i).findElement(By.cssSelector(".cart_total_price")).getText();

        }


            return "";
    }

    @Override
    public CheckoutPage addComment(String comment) {
        return null;
    }

    @Override
    public PaymentPage submitOrder() {
        return null;
    }

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

}
