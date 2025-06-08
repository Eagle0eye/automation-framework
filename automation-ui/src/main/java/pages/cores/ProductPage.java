package pages.cores;

import DTO.ReviewProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import pages.interfaces.IProductPage;


public class ProductPage extends BasePage implements IProductPage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public ProductPage setQuantity(int quantity) {

        WebElement quantityElement = driver.findElement(By.xpath("//div[@class='product-information']//input[@id='quantity']"));
        quantityElement.clear();
        quantityElement.sendKeys(String.valueOf(quantity));
        log.info("Selected Quantity: {}", quantity);
        return this;
    }

    @Override
    public ProductPage writeYourReview(ReviewProduct form) {


        WebElement nameElement = driver.findElement(By.xpath("//form[@id='review-form']//input[@id='name']"));
        WebElement emailElement = driver.findElement(By.xpath("//form[@id='review-form']//input[@id='email']"));
        WebElement messageElement = driver.findElement(By.xpath("//form[@id='review-form']//textarea[@id='review']"));
        WebElement submitButton = driver.findElement(By.xpath("//form[@id='review-form']//button[@id='button-review']"));
        // Ensure Clear all Fields
        nameElement.clear();
        emailElement.clear();
        messageElement.clear();

        // send Values
        nameElement.sendKeys(form.getName());
        emailElement.sendKeys(form.getEmail());
        messageElement.sendKeys(form.getMessage());

        submitButton.click();
        return this;
    }

    @Override
    public ProductPage addToCart() {
        WebElement addToCartButton = driver.findElement(By.xpath("//button[contains(@class, 'cart')]"));
        addToCartButton.click();
        return this;
    }


    @Override
    public ProductPage continueShopping() {
        WebElement continueButton = wait
                .until(ExpectedConditions
                .visibilityOfElementLocated(
                        By.xpath("//button[contains(text(), 'Continue Shopping')]")
                )
                );
        continueButton.click();
        return this;
    }

    @Override
    public CartPage viewCart() {
        WebElement viewCartButton = wait
                .until(ExpectedConditions
                .visibilityOfElementLocated(
                        By.xpath("//a[@href='/view_cart']")
                )
                );
        viewCartButton.click();
        return new CartPage(driver);
    }

    @Override
    public String ensureReviewSuccess() {
        WebElement message = driver.findElement(By.xpath("//div[@id='review-section']//div[@class='alert-success alert']//span"));
        return message.getText();
    }
}
