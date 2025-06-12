package pages.shared;

public interface IFooter{
    String verifyTextSUBSCRIPTION();
    FooterImpl enterEmailToSubscribe(String email);
    String verifyEmailSubscribed();
}
