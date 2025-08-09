package tests.login;

import DTO.Login;
import base.BaseUITest;
import org.testng.annotations.Test;
import pages.HomePage;
import components.Header;
import provider.AuthProvider;

import static base.DRIVERS.EDGE;
import static org.testng.Assert.*;

public class TC04_Logout_Test extends BaseUITest {

    public TC04_Logout_Test() {
        super(EDGE);
    }

    @Test(dataProvider = "loginValidCredentials",dataProviderClass = AuthProvider.class)
    public void logoutValidCredentials(Login form) {
        HomePage homePage = new HomePage(driver);
        Header header = homePage.navigateTo().LoginPage().login(form).correctLogin();
        assertNotNull(header.getLoggedInUsername());
        homePage.navigateTo().Logout();
        assertNull(header.getLoggedInUsername());
    }
}
