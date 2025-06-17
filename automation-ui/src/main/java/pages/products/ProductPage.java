package pages.products;

import DTO.ReviewProduct;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.shared.components.BasePage;
import pages.cart.CartPage;


public class ProductPage extends BasePage  {

    public ProductPage(WebDriver driver) {
        super(driver);
    }


    public ProductPage setQuantity(int quantity) {

        WebElement quantityElement = driver.findElement(By.xpath("//div[@class='product-information']//input[@id='quantity']"));
        quantityElement.clear();
        quantityElement.sendKeys(String.valueOf(quantity));
        log.info("Selected Quantity: {}", quantity);
        return this;
    }

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


    public ProductPage addToCart() {
        WebElement addToCartButton = driver.findElement(By.xpath("//button[contains(@class, 'tests.cart')]"));
        addToCartButton.click();
        return this;
    }


    public ProductPage continueShopping() {
        WebElement continueButton = wait
                .until(ExpectedConditions
                .visibilityOfElementLocated(
                        By.xpath("//button[@class='btn btn-success close-modal btn-block']")
                )
                );
        continueButton.click();
        return this;
    }

    @SneakyThrows
    public CartPage viewCart() {
        WebElement viewCartButton = wait
                .until(ExpectedConditions
                .visibilityOfElementLocated(
                        By.xpath("//a[@href='/view_cart']\n")
                )
                );
        viewCartButton.click();
        return new CartPage(driver);
    }

    public String ensureReviewSuccess() {
        WebElement message = driver.findElement(By.xpath("//div[@id='review-section']//div[@class='alert-success alert']//span"));
        return message.getText();
    }
}
