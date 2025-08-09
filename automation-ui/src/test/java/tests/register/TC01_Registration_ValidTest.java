package tests.register;

import DTO.Register;
import base.BaseUITest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.authentication.LoginPage;

import provider.RegisterProvider;
import validators.ValidationPage;

import static base.DRIVERS.CHROME;

public class TC01_Registration_ValidTest extends BaseUITest {

    private HomePage homePage;
    private SoftAssert softAssert;


    public TC01_Registration_ValidTest() {
        super(CHROME);
    }


    @BeforeTest
    public void beforeTest() {
        softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        homePage.open();
    }

    @Test(dataProvider = "RegisterValidMinInfo", dataProviderClass = RegisterProvider.class)
    public void registerUI_validTest(Register form)
    {     String verify_message = homePage.verify();
        String expected_message = "Full-Fledged practice website for Automation Engineers";
        softAssert.assertEquals(verify_message, expected_message);

        LoginPage loginPage = homePage.navigateTo().LoginPage();
        softAssert.assertTrue(loginPage.isSignupVisible());

        ValidationPage validationPage = loginPage.register(form).clickOnSignup().fillMandatoryRegisterForm(form).submit();
        softAssert.assertEquals(validationPage.getTitle(), "ACCOUNT CREATED!");

        homePage = validationPage._continue();
        validationPage = homePage.navigateTo().DeleteAccountPage();
        softAssert.assertEquals(validationPage.getTitle(), "ACCOUNT DELETED!");


    }



}
