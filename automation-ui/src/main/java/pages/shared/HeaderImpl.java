package pages.shared;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.cores.*;
import pages.validators.ValidDeletePage;
import pages.validators.VideoTutorials;

import java.time.Duration;

public class HeaderImpl implements IHeader{

    protected WebDriver driver;
    protected WebDriverWait wait;


    @FindBy(linkText = "Home") protected WebElement homeLink;
    @FindBy(linkText = "Products" ) private WebElement productsLink;
    @FindBy(linkText = "Cart") private WebElement cartLink;
    @FindBy(linkText = "Test Cases") private WebElement testCasesLink;
    @FindBy(linkText = "API Testing") private WebElement apiTestingLink;
    @FindBy(linkText = "Video Tutorials") private WebElement videoTutorialLink;
    @FindBy(linkText = "Contact US") private WebElement contactUsLink;

    public static final Logger log = LoggerFactory.getLogger(HeaderImpl.class);

    public HeaderImpl(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @Step("Go to Home page")
    @Override
    public HomePage gotoHomePage() {
        homeLink.click();
        log.info("Navigated to home page");
        return new HomePage(driver);
    }

    @Override
    @Step("Go To Products page")
    public ProductsPage gotoProductsPage() {
        productsLink.click();
        log.info("Navigated to products page");
        return new ProductsPage(driver);
    }

    @Step("Go To Cart Page")
    @Override
    public CartPage gotoCartPage() {
        cartLink.click();
        log.info("Navigated to cart page");
        return new CartPage(driver);
    }

    @Step("Go to Signup/Login Page")
    @Override
    public LoginPage gotoLoginPage() {
        WebElement signup_login = driver.findElement(By.linkText("Signup / Login"));
        signup_login.click();
        log.info("Navigated to login page");
        return new LoginPage(driver);
    }

    @Step("Go To Contact Us Page")
    @Override
    public ContactUsPage gotoContactUsPage() {
        contactUsLink.click();
        log.info("Navigated to contact us page");
        return new ContactUsPage(driver);
    }

    @Step("Go to Test Cases Page")
    @Override
    public TestCasePage gotoTestCasesPage() {
        testCasesLink.click();
        log.info("Navigated to test cases page");
        return new TestCasePage(driver);
    }

    @Step("Go to API Testing page")
    @Override
    public APITestingPage gotoAPITestingPage() {
        apiTestingLink.click();
        log.info("Navigated to API Testing page");
        return new APITestingPage(driver);
    }

    @Step("Go to Video Tutorial page")
    @Override
    public VideoTutorials gotoVideoTutorialPage() {
        videoTutorialLink.click();
        log.info("Navigated to Video Tutorial page");
        return new VideoTutorials(driver);
    }

    @Step("Delete an account")
    @Override
    public ValidDeletePage gotoDeleteAccountPage() {
        WebElement deleteAccountLink = driver.findElement(By.linkText("Delete Account"));
        deleteAccountLink.click();
        log.info("Navigated to delete account page");
        return new ValidDeletePage(driver);
    }
    @Step("Get Logged username")
    @Override
    public String getLoggedInUsername(){
        String xpath = "/li/a/i[contains(@class,'fa-user')]/following-sibling::b";
        WebElement loggedInUserText = driver.findElement(By.xpath(xpath));
        WebElement username =  wait.until(ExpectedConditions.visibilityOf(loggedInUserText));
        String actualText = username.getText().trim();
        log.info("Verified logged in user: {}", actualText);
        return actualText;
    }
    @Step("Logout")
    @Override
    public void logout() {
        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        logoutLink.click();
        log.info("Logout");
    }
}
