package pages.authentication;

import models.UserInfo;
import DTO.Login;
import DTO.Register;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.shared.components.BasePage;
import pages.shared.validations.ValidLoginPage;
import repository.CacheRepository;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ValidLoginPage login(Login form) {
        String EMAIl_XPATH = "//input[@data-qa='login-email']";
        String PASSWORD_XPATH = "//input[@data-qa='login-password']";
        String LOGIN_BUTTON_XPATH = "//button[@data-qa='login-button']";

        WebElement email = driver.findElement(By.xpath(EMAIl_XPATH));
        WebElement password = driver.findElement(By.xpath(PASSWORD_XPATH));
        WebElement loginButton = driver.findElement(By.xpath(LOGIN_BUTTON_XPATH));

        email.sendKeys(form.getEmail());
        password.sendKeys(form.getPassword());
        loginButton.click();

        log.info("login via credentials => email: {} password: {}",form.getEmail() ,form.getPassword());
        CacheRepository.put("test-account", UserInfo.builder().email(form.getEmail()).password(form.getPassword()).build());
        return new ValidLoginPage(driver);
    }

    public LoginPage register(Register form) {
        String NAME_XPATH = "//input[@data-qa='signup-name']";
        String EMAIL_XPATH = "//input[@data-qa='signup-email']";


        WebElement nameField = driver.findElement(By.xpath(NAME_XPATH));
        WebElement emailField = driver.findElement(By.xpath(EMAIL_XPATH));

        nameField.sendKeys(form.getName());
        emailField.sendKeys(form.getEmail());

        log.info("register with name : {} email: {}", form.getName(), form.getEmail());
        return this;
    }
    public RegisterPage clickOnSignup() {
        String REGISTER_BUTTON_XPATH = "//button[@data-qa='signup-button']";
        WebElement registeredButton = driver.findElement(By.xpath(REGISTER_BUTTON_XPATH));
        registeredButton.click();
        return new RegisterPage(driver);
    }

    public boolean isLoginVisible(){
        String LOGIN_FORM_XPATH = "//div[contains(@class,'login-form')]/h2";
        WebElement loginForm = driver.findElement(By.xpath(LOGIN_FORM_XPATH));
        return loginForm.isDisplayed();
    }

    public boolean isSignupVisible() {
        String SIGNUP_XPATH = "//div[contains(@class,'signup-form')]/h2";
        WebElement signupVerify = driver.findElement(By.xpath(SIGNUP_XPATH));
        boolean visible = signupVerify.isDisplayed();
        log.info("verify signup visibility");
        return visible;
    }




}
