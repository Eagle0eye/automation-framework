package pages.interfaces;

import DTO.ReviewProduct;
import pages.cores.CartPage;
import pages.cores.ProductPage;

public interface IProductPage {

    ProductPage setQuantity(int quantity);
    ProductPage writeYourReview(ReviewProduct form);
    ProductPage addToCart();
    ProductPage continueShopping();
    CartPage viewCart();
    String ensureReviewSuccess();
}
