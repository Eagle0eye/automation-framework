package pages.interfaces;

import pages.shared.FooterImpl;
import pages.shared.HeaderImpl;

public interface IBasePage {
    void open();
    HeaderImpl Header();
    FooterImpl Footer();
}
