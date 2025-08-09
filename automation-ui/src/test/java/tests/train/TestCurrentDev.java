package tests.train;


import base.BaseUITest;
import base.DEVICES;
import base.DRIVERS;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import provider.ProductProvider;
import components.Filter;

import java.util.Map;

import static util.ProductsGenerator.loadProducts;

@Epic("Current Test")
@Feature("Test Multiple Browsers")
public class TestCurrentDev extends BaseUITest {
    HomePage homePage;
    SoftAssert softAssert;
    Filter filter;

    public TestCurrentDev(DRIVERS driverType, DEVICES devicesType) {
        super(driverType,devicesType);
    }



    @BeforeTest
    public void setUp() {
        softAssert = new SoftAssert();
        loadProducts();
    }
    @Description("Navigate to APITesting Page")
    @Test(dataProvider = "generatedProducts", dataProviderClass = ProductProvider.class)
    public void testCurrentDev(Map<String, Integer> products) {
        homePage = new HomePage(driver);
        homePage.open();
        homePage.navigateTo().APITestingPage();
        //        ReviewProduct review = ReviewProduct.builder().email("A@aa").name("Yousef").message("22222").build();
//        String actual =  homePage.order().viewProduct().setQuantity(3).writeYourReview(review).ensureReviewSuccess();
//        String expected = "Thank you for your review.";
//        softAssert.assertTrue(actual.equals(expected));
    }

}
