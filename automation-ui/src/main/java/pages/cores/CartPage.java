package pages.cores;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class CartPage extends BasePage {

    @FindBy(id = "#empty_cart") private WebElement emptyCart;
    @FindBy(css = "#cart_items > div > div.breadcrumbs > ol > li.active") private WebElement cartPageIsActive;
    @FindBy(linkText = "here") private WebElement goTOAddProducts;
    @FindBy(linkText = "Proceed To Checkout") private WebElement processToCheckout;

    public CartPage(WebDriver driver) {
        super(driver);
    }

}
