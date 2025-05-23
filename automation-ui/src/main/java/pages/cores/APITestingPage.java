package testing.pages.cores;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testing.pages.BasePage;

public class APITestingPage extends BasePage {
    @FindBy(tagName = "h2") private WebElement title;
    public APITestingPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        String titleText = title.getText();
        log.info("Verify title API Testing page: {}", titleText);
        return titleText;
    }

}
