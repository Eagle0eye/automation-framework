package tests.train;


import base.BaseUITest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.cores.HomePage;
import pages.cores.ProductsPage;
import provider.ProductProvider;
import utils.ProductFilter;
import utils.enums.BRAND;
import utils.enums.CATEGORY;

import java.util.Map;



public class TestCurrentDev  extends BaseUITest {
    HomePage homePage;
    SoftAssert softAssert;
    ProductFilter productFilter;


    @BeforeTest
    public void setUp() {
        softAssert = new SoftAssert();
//        loadProducts();
    }

    @Test(dataProvider = "generatedProducts", dataProviderClass = ProductProvider.class)
    public void testCurrentDev(Map<String, Integer> products) {
        homePage = new HomePage(driver);
        homePage.open();
        productFilter = homePage.order().filter();
        productFilter.search(BRAND.H_M);
        productFilter.search(BRAND.BABYHUG);
        productFilter.search(CATEGORY.KIDS_DRESS);

    }

}
