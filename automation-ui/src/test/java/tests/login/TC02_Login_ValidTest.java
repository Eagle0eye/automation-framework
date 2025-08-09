package tests.login;

import DTO.Login;
import base.BaseUITest;
import base.DRIVERS;
import components.Header;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import provider.AuthProvider;

public class TC02_Login_ValidTest extends BaseUITest {

    public SoftAssert softAssert;

    public TC02_Login_ValidTest() {
        super(DRIVERS.CHROME);
        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "loginValidCredentials",dataProviderClass = AuthProvider.class)
    public void loginValidCredentials(Login form) {
        HomePage homePage = new HomePage(driver);
        Header header=homePage.navigateTo().LoginPage().login(Login.builder().email("automation92@armyspy.com").password("auto12345678").build()).correctLogin();
        softAssert.assertNotNull(header.getLoggedInUsername());
    }

}
