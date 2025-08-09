package tests.subscription;

import base.BaseUITest;
import components.Footer;
import expectaions.ui.UIExpectations;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.cart.CartPage;
import provider.ProductProvider;
import provider.RegisterProvider;

import java.util.Map;

import static base.DRIVERS.CHROME;
import static org.testng.Assert.assertEquals;

public class TC11_Subscription_Ensure_In_Cart_Page extends BaseUITest {
    private final HomePage homePage;
    private CartPage cartPage;

    public TC11_Subscription_Ensure_In_Cart_Page(){
        super(CHROME);
        homePage = new HomePage(driver);

    }

    @Test(priority = 1)
    public void ensureHPageIsVisible(){
        String actual = homePage.verify();
        assertEquals(actual, UIExpectations.HomePage.WELCOME_MESSAGE);
        ;
    }


    @Test(priority = 2, dataProvider = "generatedProducts", dataProviderClass = ProductProvider.class)
    public void scrollDownInHomePage(Map<String, Integer> products){
        homePage.scroll().downWithArrow();
        homePage.order().addToCart(products);
        CartPage cartPage = homePage.navigateTo().CartPage();
        cartPage.scroll().downWithArrow();

    }


    @Test(priority = 3)
    public void ensureSubscribeInHomepage(){
        String email = "user@example.com";
        Footer footer = cartPage.Footer();
        assertEquals(footer.verifyTextSUBSCRIPTION(), UIExpectations.Footer.TITLE);
        assertEquals(footer.enterEmailToSubscribe(email).verifyEmailSubscribed(),UIExpectations.Footer.SUCCESS_MESSAGE);
    }
}
