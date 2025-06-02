import Cache.DTO.ProductCache;
import Cache.Cache;

import java.util.Set;

import static util.ProductsGenerator.generateProducts;
import static util.ProductsGenerator.loadProducts;

public class run {
    public static void main(String[] args) {

//        EdgeDriver webdriver = new EdgeDriver();

        try {

//            HomePage homePage = new HomePage(webdriver);
//            homePage.open();

            loadProducts();
            generateProducts(5);
            int sum = 0;
            Set<ProductCache> productCache = (Set<ProductCache>) Cache.get("ordered-products");
            for (ProductCache productCache1 : productCache) {
                System.out.println(productCache1.getProductName() + " total price = " + productCache1.getPrice() * productCache1.getQuantity());
                sum += productCache1.getPrice() * productCache1.getQuantity();
            }
            System.out.println("Total price is: Rs. " + sum);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
