package utils;

import DTO.ProductInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private   WebDriver driver;
    private   Actions actions;
    private  WebDriverWait wait;
    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);
    public ProductRepository(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public ProductRepository() {}

    public  List<ProductInfo> loadProducts() {

        List<ProductInfo> products = new ArrayList<>();
        List<WebElement> productsElements = driver.findElements(By.cssSelector("div.col-sm-4"));
            for (WebElement item : productsElements) {
                try {


                    actions.moveToElement(item).perform();
                    wait.wait();
                    WebElement product = item.findElement(By.cssSelector(".overlay-content"));
                    String productName = product.findElement(By.tagName("p")).getText();
                    String price = product.findElement(By.tagName("h2")).getText();
                    String productId = product.findElement(By.tagName("div")).getDomAttribute("data-product-id");
                    products.add(ProductInfo.builder().productId(productId).productName(productName).productPrice(price).build());
                    log.info("Product #{} called {} price{} loaded successfully", productId, productName, price);
                } catch (Exception e) {
                    log.error("Error adding product: {}", e.getMessage());
                }
            }
        return products;
    }
}
