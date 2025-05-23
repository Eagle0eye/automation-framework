package testing.pages.cores;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testing.pages.BasePage;

public class ProductsPage extends BasePage {

    @FindBy(tagName = "h2") private WebElement headerTitle;
    @FindBy(id = "search_product") private WebElement searchBox;
    @FindBy(id = "submit_search") private WebElement searchButton;
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        String title = headerTitle.getText();
        log.info("verify Title of Products Page");
        return title;
    }
    public void clickOnSearchButton(String searchText) {
       searchBox.sendKeys(searchText);
       searchButton.click();
       log.info("search about : '{}' and button clicked",searchText);
    }
}
