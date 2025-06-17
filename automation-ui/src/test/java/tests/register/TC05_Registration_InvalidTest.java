package tests.register;

import DTO.Register;
import base.BaseUITest;
import base.DRIVERS;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.authentication.LoginPage;
import provider.RegisterProvider;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC05_Registration_InvalidTest extends BaseUITest {
    public TC05_Registration_InvalidTest() {
        super(DRIVERS.EDGE);
    }
    @Test(dataProvider = "RegisterInvalidExistEmail",dataProviderClass = RegisterProvider.class)
    public void registerUI_invalidTest(Register form) {
        HomePage homePage = new HomePage(driver);

        LoginPage loginPage = homePage.navigateTo().LoginPage();
        assertTrue(loginPage.isSignupVisible());

        String actual = loginPage.register(form).clickOnSignup().verifyRegisterErrorMessage();
        String expected = "Email Address already exist!";
        assertEquals(actual,expected);
    }
}
