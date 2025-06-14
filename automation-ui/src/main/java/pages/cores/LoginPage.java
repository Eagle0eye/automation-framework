package pages.cores;

import Cache.DTO.UserInfo;
import DTO.Login;
import DTO.Register;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.validators.ValidLoginPage;
import Cache.Cache;

public class LoginPage extends BasePage {
    @FindBy(css = ".signup-form > h2:nth-child(1)") private WebElement signupVerify;
    @FindBy(css = ".login-form > form:nth-child(2) > input:nth-child(2)") private WebElement email;
    @FindBy(css = ".login-form > form:nth-child(2) > input:nth-child(3)") private WebElement password;
    @FindBy(css = ".login-form > form:nth-child(2) > button:nth-child(4)") private WebElement loginButton;

    @FindBy(css = ".signup-form > form:nth-child(2) > input:nth-child(2)") private WebElement registeredName;
    @FindBy(css = ".signup-form > form:nth-child(2) > input:nth-child(3)") private WebElement registeredEmail;
    @FindBy(css = "button.btn:nth-child(5)") private WebElement registeredButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ValidLoginPage login(Login form) {
        email.sendKeys(form.getEmail());
        password.sendKeys(form.getPassword());
        loginButton.click();
        log.info("login email: {} password: {}",form.getEmail() ,form.getPassword());
        Cache.put("test-account", UserInfo.builder().email(form.getEmail()).password(form.getPassword()).build());
        return new ValidLoginPage(driver);
    }

    public RegisterPage register(Register form) {
        registeredName.sendKeys(form.getName());
        registeredEmail.sendKeys(form.getEmail());
        log.info("register with name : {} email: {}", form.getName(), form.getEmail());
        registeredButton.click();
        return new RegisterPage(driver);
    }

    public boolean isSignupVisible() {
        boolean visible = signupVerify.isDisplayed();
        log.info("verify signup visibility");
        return visible;
    }




}
