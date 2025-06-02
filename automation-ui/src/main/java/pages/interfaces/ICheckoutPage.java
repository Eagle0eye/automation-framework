package pages.interfaces;

import pages.cores.CheckoutPage;
import pages.cores.PaymentPage;

public interface ICheckoutPage {
    String verifyAddressDetails();
    String reviewOrderDetails();
    CheckoutPage addComment(String comment);
    PaymentPage submitOrder();

}
