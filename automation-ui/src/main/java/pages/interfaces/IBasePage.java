package pages.interfaces;

import pages.cores.*;
import pages.validators.ValidDeletePage;
import pages.validators.VideoTutorials;

public interface IBasePage {
    void open();
    HomePage gotoHomePage();
    ProductsPage  gotoProductsPage();
    CartPage gotoCartPage();
    LoginPage gotoLoginPage();
    ContactUsPage gotoContactUsPage();
    TestCasePage gotoTestCasesPage();
    APITestingPage gotoAPITestingPage();
    VideoTutorials gotoVideoTutorialPage();
    ValidDeletePage gotoDeleteAccountPage();
    void logout();
}
