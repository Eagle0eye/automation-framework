package pages.shared.validations;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.shared.validations.interfaces.IValidationPage;

@RequiredArgsConstructor
public abstract class BaseValidationPage implements IValidationPage {

    protected final WebDriver driver;

    @Override
    public String getTitle() {
        String title_xpath = "//h2[@class='title text-center' and contains(@style, 'color: green')]";
        WebElement titleElement = driver.findElement(By.xpath(title_xpath));
        return titleElement.getText().strip();
    }

    @Override
    public String getDescription() {
        String description_xpath = "//p[contains(@style, 'font-size: 20px')]";
        WebElement descriptionElement = driver.findElement(By.xpath(description_xpath));
        return descriptionElement.getText().strip();
    }

    @Override
    public HomePage _continue() {
        String continue_xpath = "//a[@data-qa='continue-button']";
        WebElement continueElement = driver.findElement(By.xpath(continue_xpath));
        continueElement.click();
        return new HomePage(driver);
    }
}
