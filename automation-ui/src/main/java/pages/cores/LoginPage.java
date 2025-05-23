package testing.pages.cores;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testing.pages.BasePage;
import testing.pages.validators.ValidLoginPage;
import testing.register.RegisterForm;

import java.util.Map;

import static testing.register.RegisterMapper.ToRegisterForm;
import static util.LocatorLoader.loadLocators;

public class LoginPage extends BasePage {
    @FindBy(css = ".signup-form > h2:nth-child(1)") private WebElement signupVerify;
    @FindBy(css = ".login-form > form:nth-child(2) > input:nth-child(2)") private WebElement email;
    @FindBy(css = ".login-form > form:nth-child(2) > input:nth-child(3)") private WebElement password;
    @FindBy(css = ".login-form > form:nth-child(2) > button:nth-child(4)") private WebElement loginButton;

    @FindBy(css = ".signup-form > form:nth-child(2) > input:nth-child(2)") private WebElement registeredName;
    @FindBy(css = ".signup-form > form:nth-child(2) > input:nth-child(3)") private WebElement registeredEmail;
    @FindBy(css = "button.btn:nth-child(5)") private WebElement registeredButton;
    @FindBy(css = ".signup-form > form:nth-child(2) > p:nth-child(5)") private WebElement registeredPassword;
    private final Map<String, By> locators = loadLocators("/loginPage.json");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ValidLoginPage login(String entered_email, String entered_password) {

        driver.findElement(locators.get("email")).sendKeys(entered_email);
        driver.findElement(locators.get("password")).sendKeys(entered_password);
        driver.findElement(locators.get("loginButton")).click();
        log.info("login email: {} password: {}",entered_email ,entered_password );
        return new ValidLoginPage(driver);
    }

    public RegisterPage register(String path) {

        RegisterForm form = ToRegisterForm(path);

        driver.findElement(locators.get("registeredName")).sendKeys(form.getName());
        driver.findElement(locators.get("registeredEmail")).sendKeys(form.getEmail());
        log.info("register with name : {} email: {}", form.getName(), form.getEmail());
        driver.findElement(locators.get("signupButton")).click();
        return new RegisterPage(driver);
    }

    public boolean isSignupVisible() {
        boolean visible = signupVerify.isDisplayed();
        log.info("verify signup visibility");
        return visible;
    }




}
