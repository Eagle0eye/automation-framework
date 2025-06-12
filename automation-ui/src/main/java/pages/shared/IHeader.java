package pages.shared;


import pages.cores.*;
import pages.validators.ValidDeletePage;
import pages.validators.VideoTutorials;

public interface IHeader {
    HomePage gotoHomePage();
    CartPage gotoCartPage();
    LoginPage gotoLoginPage();
    ProductsPage gotoProductsPage();
    ContactUsPage gotoContactUsPage();
    TestCasePage gotoTestCasesPage();
    APITestingPage gotoAPITestingPage();
    VideoTutorials gotoVideoTutorialPage();
    ValidDeletePage gotoDeleteAccountPage();
    String getLoggedInUsername();
    void logout();
}
