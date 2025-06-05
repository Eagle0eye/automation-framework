package utils;

import DTO.ProductInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.cores.ProductsPage;
import utils.enums.BRAND;
import utils.enums.CATEGORY;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductFilter {

    private static final Logger log = LoggerFactory.getLogger(ProductFilter.class);
    private List<ProductInfo> productInfos;
    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;
    public ProductFilter(WebDriver driver)
    {
        this.driver = driver;
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        productInfos = new ArrayList<>();
    }

    public List<ProductInfo> reload(){
        List<WebElement> productsElements = driver.findElements(By.cssSelector("div.col-sm-4"));
        String name = "NOT_FOUND";
        String price= "0.0";
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

                log.info("Extracted: name={}, price={}", name, price);

                productInfos.add(ProductInfo.builder()
                        .name(name)
                        .price(Integer.parseInt(price))
                        .build());

            } catch (Exception e) {
                log.error("Failed to extract some product info: {}", e.getMessage());
            }
        }
        return productInfos;
    }

    /*
    Search about products by:
    - search(CATEGORY)
    - search(BRAND)
     */

    public ProductsPage search(CATEGORY category) {
        String womenMain = "//a[@href='#%s' and normalize-space()='%s']".formatted(category.getMainCategory(), category.getMainCategory());
        WebElement categoryElement = driver.findElement(By.xpath(womenMain));
        categoryElement.click();
        String womenTops = "//div[@id='%s']//a[normalize-space()='%s']".formatted(category.getMainCategory(),category.getText());
        WebElement topsElement = driver.findElement(By.xpath(womenTops));
        topsElement.click();
        return new ProductsPage(driver);
    }


    public ProductsPage search(BRAND brand) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectedBrand = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='brands_products']//a[contains(.,'" + brand.toString() + "')]")
        ));
        selectedBrand.click();

        return new ProductsPage(driver);
    }

}
