package tests.contactus;

import DTO.ContactUs;
import base.BaseUITest;
import expectaions.ui.UIExpectations;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.connections.ContactUsPage;
import provider.ContactUsProvider;
import static base.DRIVERS.CHROME;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC06_ContactUs_Test extends BaseUITest {

    public TC06_ContactUs_Test() {
        super(CHROME);
    }

    @Test(dataProvider = "SendValidInfo",dataProviderClass = ContactUsProvider.class)
    public void sendContactUsFormTest(ContactUs form){
        HomePage homePage = new HomePage(driver);

        String verify_homePage = homePage.verify();
        assertEquals(verify_homePage, UIExpectations.HomePage.WELCOME_MESSAGE);

        ContactUsPage contactUsPage = homePage.navigateTo().ContactUsPage();
        assertTrue(contactUsPage.verifyPageTitle());

        String actual = contactUsPage.fillContactUsForm(form).submit().verifySuccessMessage();
        assertEquals(actual,UIExpectations.ContactUs.MESSAGE);

        contactUsPage._gotoHome();
        assertEquals(homePage.verify(),UIExpectations.HomePage.WELCOME_MESSAGE);
    }

}
