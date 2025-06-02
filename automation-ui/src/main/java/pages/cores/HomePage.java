package pages.cores;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import services.ProductsService;


public class HomePage extends BasePage {


    private ProductsService productsService;
    @FindBy(css = ".single-widget > h2:nth-child(1)") private WebElement subscription;

    public HomePage(WebDriver driver) {
        super(driver);
        productsService = new ProductsService(driver);
    }

    public ProductsService Filter() {
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
}
