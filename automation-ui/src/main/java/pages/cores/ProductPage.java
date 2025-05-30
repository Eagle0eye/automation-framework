package pages.cores;

import DTO.ProductInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.interactions.Actions;



public class ProductPage extends BasePage {

    private List<ProductInfo> productList;
    private final Actions actions;

    @FindBy(css = "#cartModal > div > div > div.modal-body > p:nth-child(1)")
    private WebElement verifyAddMessage;

    @FindBy(linkText = "View Cart")
    private WebElement viewCart;

    @FindBy(css = "#cartModal > div > div > div.modal-footer > button")
    private WebElement continueShopping;





    public ProductPage(WebDriver driver) {
        super(driver);
        actions = new Actions(driver);
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
            log.warn("ProductSearch list is empty or not loaded yet.");
            return this;
        }
        List<WebElement> productsElements = driver.findElements(By.cssSelector("div.col-sm-4"));
        productNames.forEach(targetName -> {
            for (WebElement item : productsElements) {
                try {
                    actions.moveToElement(item).perform();
                    WebElement product = item.findElement(By.cssSelector(".overlay-content"));
                    String productName = product.findElement(By.tagName("p")).getText();
                    if (targetName.equals(productName)) {
                        product.findElement(By.linkText("Add to cart")).click();
                        if (verifyAddMessage()) {
                            log.info("ProductSearch '{}' added to cart successfully.", targetName);
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
