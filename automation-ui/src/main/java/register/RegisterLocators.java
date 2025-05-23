package register;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class RegisterLocators {
    public RegisterLocators() {};
    public Map<String, By> formLocators() {
        Map<String, By> locators = new HashMap<>();
        locators.put("title", By.id("id_gender1"));
        locators.put("name", By.id("automation"));

        locators.put("password", By.id("password"));

        locators.put("day", By.id("days"));
        locators.put("month", By.id("months"));
        locators.put("year", By.id("years"));

        locators.put("newsletter", By.id("newsletter"));
        locators.put("specialOffers", By.id("optin"));

        locators.put("firstName", By.id("first_name"));
        locators.put("lastName", By.id("last_name"));

        locators.put("company", By.id("company"));

        locators.put("address1", By.id("address1"));
        locators.put("address2", By.id("address2"));
        locators.put("state", By.id("state"));
        locators.put("city", By.id("city"));
        locators.put("zipcode", By.id("zipcode"));

        locators.put("mobileNumber", By.id("mobile_number"));
        return locators;
    }
}
