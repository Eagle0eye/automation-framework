package pages.shared.validations.interfaces;

import pages.HomePage;

public interface IValidationPage {
    String getTitle();
    String getDescription();
    HomePage _continue();
}
