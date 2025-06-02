package pages.interfaces;

import DTO.Payment;
import pages.cores.PaymentPage;
import pages.validators.ValidOrderPage;

public interface IPaymentPage {
    String verifyTitle();
    PaymentPage fillPaymentForm(Payment form);
    ValidOrderPage payAndConfirmOrder();
}
