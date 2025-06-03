package tests.train;


import base.BaseUITest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.cores.HomePage;
import provider.ProductProvider;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static util.ProductsGenerator.loadProducts;

public class TestCurrentDev  extends BaseUITest {
    HomePage homePage;
    SoftAssert softAssert;
    @BeforeTest
    public void setUp() {
        softAssert = new SoftAssert();
        loadProducts();
    }

    @Test(dataProvider = "generatedProducts", dataProviderClass = ProductProvider.class)
    public void testCurrentDev(Map<String, Integer> products) {
        homePage = new HomePage(driver);
        homePage.open();
        String condition =  homePage.order().addToCart(products).viewCart().isCartEmpty();
        String expected = "Cart is empty! Click here to buy products.";
        System.out.println("Text: "+condition);
        assertEquals(condition,expected);
    }

}
