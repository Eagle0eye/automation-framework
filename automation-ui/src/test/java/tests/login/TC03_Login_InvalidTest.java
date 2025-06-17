package tests.login;

import DTO.Login;
import base.BaseUITest;
import base.DRIVERS;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import provider.AuthProvider;

import static org.testng.Assert.assertEquals;

public class TC03_Login_InvalidTest  extends BaseUITest {
    public TC03_Login_InvalidTest(){
        super(DRIVERS.CHROME);

    }

    @Test(dataProvider = "loginInvalidIncorrectPassword",dataProviderClass = AuthProvider.class)
    public void loginInvalidIncorrectPassword(Login form) {
        HomePage homePage = new HomePage(driver);
        String expected_message = "Your email or password is incorrect!";
        String actual_message = homePage.navigateTo().LoginPage().login(form).incorrectLogin();
        assertEquals(actual_message, expected_message);
    }

}
