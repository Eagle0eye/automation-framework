package tests.subscription;

import base.BaseUITest;
import components.Footer;
import expectaions.ui.UIExpectations;
import org.testng.annotations.Test;
import pages.HomePage;

import static base.DRIVERS.EDGE;
import static org.testng.Assert.assertEquals;

public class TC10_Subscription_Ensure_In_Home_Page extends BaseUITest {
    private HomePage homePage;
    public TC10_Subscription_Ensure_In_Home_Page() {
        super(EDGE);
    }

    @Test(priority = 1)
    public void ensureHomePageIsVisible(){
        String actual = homePage.verify();
        assertEquals(actual, UIExpectations.HomePage.WELCOME_MESSAGE);
    }


    @Test(priority = 2)
    public void scrollDownInHomePage(){
        homePage.scroll().downWithArrow();
    }


    @Test(priority = 3)
    public void ensureSubscribeInHomepage(){
        String email = "user@example.com";
        homePage.scroll().upWithMouse();
        Footer footer = homePage.Footer();
        assertEquals(footer.verifyTextSUBSCRIPTION(), UIExpectations.Footer.TITLE);
        assertEquals(footer.enterEmailToSubscribe(email).verifyEmailSubscribed(),UIExpectations.Footer.SUCCESS_MESSAGE);
    }
}
