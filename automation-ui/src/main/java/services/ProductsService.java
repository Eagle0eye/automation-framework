package services;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utils.ProductFilter;
import utils.ProductRepository;
import variables.ProductVariables;


public class ProductsService {
    private final WebDriver driver;
    private ProductFilter productFilter;

    ProductsService(WebDriver driver) {
       this.driver = driver;
       this.productFilter = new ProductFilter(driver);
    }

    @Step("load Products and caching it in memory ")
    public void loadProducts() {
        ProductVariables.ALL_PRODUCTS = new ProductRepository(driver).loadProducts();
    }

    public ProductFilter filter(){
        return productFilter;
    }


}
