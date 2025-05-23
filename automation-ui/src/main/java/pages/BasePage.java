package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.cores.*;
import pages.validators.ValidDeletePage;
import pages.validators.VideoTutorials;


import java.time.Duration;


public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // Common top navigation elements
    @FindBy(linkText = "Home") protected WebElement homeLink;
    @FindBy(linkText = "Products" ) private WebElement productsLink;
    @FindBy(linkText = "Cart") private WebElement cartLink;
    @FindBy(linkText = "Signup / Login") private WebElement signupLink;
    @FindBy(linkText = "Test Cases") private WebElement testCasesLink;
    @FindBy(linkText = "API Testing") private WebElement apiTestingLink;
    @FindBy(linkText = "Video Tutorials") private WebElement videoTutorialLink;
    @FindBy(linkText = "Contact US") private WebElement contactUsLink;

    // Authenticated navigation
    @FindBy(linkText = "Logout") private WebElement logoutLink;
    @FindBy(linkText = "Delete Account") private WebElement deleteAccountLink;

    public static final Logger log = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        PageFactory.initElements(driver, this);
    }

    @Step("Open Website")
    public BasePage open(){
        String baseUrl = "https://automationexercise.com";
        driver.get(baseUrl);
        log.info("Browser: {} baseUrl: {}", driver.getTitle(),baseUrl);
        return this;
    }
    @Step("Go to Home page")
    public HomePage goToHomePage() {
        homeLink.click();
        log.info("Navigated to home page");
        return new HomePage(driver);
    }

    public ProductsPage goToProductsPage() {
        productsLink.click();
        log.info("Navigated to products page");
        return new ProductsPage(driver);
    }

    @Step("Go To Cart Page")
    public CartPage goToCartPage() {
        cartLink.click();
        log.info("Navigated to cart page");
        return new CartPage(driver);
    }

    @Step("Go to Signup/Login Page")
    public LoginPage goToLoginPage() {
        signupLink.click();
        log.info("Navigated to login page");
        return new LoginPage(driver);
    }
    @Step("Go To Contact Us Page")
    public ContactUsPage goToContactUsPage() {
        contactUsLink.click();
        log.info("Navigated to contact us page");
        return new ContactUsPage(driver);
    }
    @Step("Go to Test Cases Page")
    public TestCasePage goToTestCasesPage() {
        testCasesLink.click();
        log.info("Navigated to test cases page");
        return new TestCasePage(driver);
    }
    @Step("Go to API Testing page")
    public APITestingPage goToAPITestingPage() {
        apiTestingLink.click();
        log.info("Navigated to API Testing page");
        return new APITestingPage(driver);
    }
    @Step("Go to Video Tutorial pahe")
    public VideoTutorials goToVideoTutorialPage() {
        videoTutorialLink.click();
        log.info("Navigated to Video Tutorial page");
        return new VideoTutorials(driver);
    }
    @Step("Delete an account")
    public ValidDeletePage goToDeleteAccountPage() {
        deleteAccountLink.click();
        log.info("Navigated to delete account page");
        return new ValidDeletePage(driver);
    }
    @Step("Logout")
    public void logout() {
        logoutLink.click();
        log.info("Logout");
    }


}