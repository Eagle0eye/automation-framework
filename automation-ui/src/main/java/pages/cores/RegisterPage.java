package pages.cores;

import DTO.Register;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;
import pages.validators.ValidRegisterPage;

public class RegisterPage extends BasePage {

    @FindBy(id = "id_gender1") private WebElement gender1;
    @FindBy(id = "name") private WebElement name;
    @FindBy(id = "password") private WebElement password;
    @FindBy(id = "days") private WebElement days;
    @FindBy(id = "months") private WebElement months;
    @FindBy(id = "years") private WebElement years;
    @FindBy(id = "newsletter") private WebElement newsletter;
    @FindBy(id = "optin") private WebElement specialOffers;
    @FindBy(id = "first_name") private WebElement firstName;
    @FindBy(id = "last_name") private WebElement lastName;
    @FindBy(id = "company") private WebElement company;
    @FindBy(id = "country") private WebElement country;
    @FindBy(id = "address1") private WebElement address1;
    @FindBy(id = "address2") private WebElement address2;
    @FindBy(id = "state") private WebElement state;
    @FindBy(id = "city") private WebElement city;
    @FindBy(id = "zipcode") private WebElement zipcode;
    @FindBy(id = "mobileNumber") private WebElement mobileNumber;
    @FindBy(css = "button.btn:nth-child(22)") private WebElement submit;


    public RegisterPage(WebDriver driver) {
        super(driver);
    }


    public ValidRegisterPage fillRegisterForm(Register form) {
        gender1.click();
        name.clear();
        name.sendKeys(form.getName());
        password.sendKeys(form.getPassword());

        new Select(days).selectByValue(String.valueOf(form.getDay()));
        new Select(months).selectByValue(String.valueOf(form.getMonth()));
        new Select(years).selectByValue(String.valueOf(form.getYear()));

        newsletter.click();
        specialOffers.click();

        firstName.sendKeys(form.getFirstname());
        lastName.sendKeys(form.getLastname());
        company.sendKeys(form.getCompany());

        address1.sendKeys(form.getAddress());
        address2.sendKeys(form.getAddress2());

        new Select(country).selectByValue(form.getCountry());

        state.sendKeys(form.getState());
        city.sendKeys(form.getCity());
        zipcode.sendKeys(form.getZipcode());
        mobileNumber.sendKeys(form.getPhone());

        submit.click();

        return new ValidRegisterPage(driver);
    }

}
