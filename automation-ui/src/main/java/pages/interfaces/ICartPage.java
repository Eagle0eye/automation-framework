package pages.interfaces;

import pages.cores.CartPage;
import pages.cores.CheckoutPage;
import pages.cores.ProductPage;

import java.util.Map;

public interface ICartPage {
    CheckoutPage proceedToCheckout();
    String isCartEmpty();
    String verifyDisplayedCart();
    boolean verifyItems(Map<String, Integer> items);
    CartPage cancelProduct(String productName);
    ProductPage gotoProductPageWithHere();
}
