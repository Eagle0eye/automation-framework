package services;

import pages.cores.CartPage;
import pages.cores.ProductPage;
import utils.ProductFilter;

import java.util.Map;

public interface IProductService {
    ProductFilter filter();

    ProductsService addToCart(String productName, int quantity);

    ProductsService addToCart(Map<String, Integer> products);

    boolean verifyAddMessage();

    ProductsService continueShopping();

    CartPage viewCart();

    ProductPage viewProduct();
    ProductPage viewProduct(String productName);

}
