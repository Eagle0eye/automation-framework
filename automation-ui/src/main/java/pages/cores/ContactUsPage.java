package pages.cores;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ContactUsPage extends BasePage {

    @FindBy(css = "") private WebElement title;
    @FindBy(css= "") private WebElement name;
    @FindBy(css = "") private WebElement email;
    @FindBy(css ="") private WebElement subject;
    @FindBy(css = "") private WebElement message;
    @FindBy(css = "") private WebElement uploadFile;
    @FindBy(css = "") private WebElement submit;

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }
}
