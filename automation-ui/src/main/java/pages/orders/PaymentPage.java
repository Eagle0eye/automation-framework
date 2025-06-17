package pages.orders;

import DTO.Payment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.shared.components.BasePage;

import pages.shared.validations.ValidOrderPage;

public class PaymentPage extends BasePage {
    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public String verifyTitle() {
        String title_xpath = "//h2[@class='heading' and contains(text(),'Payment')]";
        WebElement title_element = driver.findElement(By.xpath(title_xpath));
        return title_element.getText().strip();
    }


    public PaymentPage fillPaymentForm(Payment form) {
        String cardName_xpath = "//input[@name='name_on_card']";
        String cardNumber_xpath = "//input[@name='card_number']";
        String cvc_xpath = "//input[@name='cvc']";
        String expiredMonth_xpath = "//input[@name='expiry_month']";
        String expiredYear_xpath = "//input[@name='expiry_year']";

        WebElement cardName = driver.findElement(By.xpath(cardName_xpath));
        WebElement cardNumber = driver.findElement(By.xpath(cardNumber_xpath));
        WebElement cvc = driver.findElement(By.xpath(cvc_xpath));
        WebElement expiredMonth = driver.findElement(By.xpath(expiredMonth_xpath));
        WebElement expiredYear = driver.findElement(By.xpath(expiredYear_xpath));

        cardName.clear();
        cardNumber.clear();
        cvc.clear();
        expiredMonth.clear();
        expiredYear.clear();

        cardName.sendKeys(form.getCardName());
        cardNumber.sendKeys(form.getCardNumber());
        cvc.sendKeys(form.getCvc());
        expiredMonth.sendKeys(form.getMonth());
        expiredYear.sendKeys(form.getYear());

        return this;
    }

    public ValidOrderPage payAndConfirmOrder() {
        String submitButton_xpath = "//button[@id='submit']";
        WebElement submitButton = driver.findElement(By.xpath(submitButton_xpath));
        submitButton.click();
        return new ValidOrderPage(driver);
    }

}
