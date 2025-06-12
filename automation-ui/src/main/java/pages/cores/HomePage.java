package pages.cores;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import services.ProductsService;

import java.util.List;
import java.util.Random;


public class HomePage extends BasePage {


    private ProductsService productsService;
    @FindBy(css = ".single-widget > h2:nth-child(1)") private WebElement subscription;

    public HomePage(WebDriver driver) {
        super(driver);
        productsService = new ProductsService(driver);
    }

    public ProductsService order() {
        return this.productsService;
    }

    @Step("verify Home page")
    public String verify() {
        WebElement verifyHomePage = driver.findElement(By.tagName("h2"));
        String message = verifyHomePage.getText();
        log.info("Message: {}", message);
        return message;
    }


    @Step("verify Subscription is Visible")
    public boolean verifySUBSCRIPTIONisVisible() {
        boolean visibility = subscription.isDisplayed();
        log.info("verify SUBSCRIPTION is Visible");
        return visibility;
    }

    @Step("scroll down in Home page")
    public HomePage scrollDownWithoutArrow(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        return this;
    }

    public HomePage selectRandomItems(){
        String xpath = "//div[@id='recommended-item-carousel']//div[contains(@class,'item active')]//div[@class='product-image-wrapper']";

        // Wait for visible items inside the active carousel item
        List<WebElement> visibleItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));

        if (visibleItems.isEmpty()) {
            log.warn("No recommended items found in the active carousel.");
            return this;
        }

        // Pick a random item
        int randomIndex = new Random().nextInt(visibleItems.size());
        WebElement selectedItem = visibleItems.get(randomIndex);

        // Click the "Add to cart" button within the selected item
        WebElement addToCartBtn = selectedItem.findElement(By.xpath(".//a[contains(@class,'add-to-cart')]"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();

        log.info("Clicked Add to cart for visible item at index: {}", randomIndex);
        return this;
    }
}
