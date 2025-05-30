package pages.interfaces;

import pages.cores.CartPage;

import java.util.List;

public interface iProductsService<T> {
    T searchProduct(String productName);
    T searchProduct(String productName, String category);
    T selectProduct(String productName);
    CartPage selectProducts(List<String> productNames);

}
