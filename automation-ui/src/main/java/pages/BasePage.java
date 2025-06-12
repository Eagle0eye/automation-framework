package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.cores.*;
import pages.interfaces.IBasePage;
import pages.shared.FooterImpl;
import pages.shared.HeaderImpl;
import pages.validators.ValidDeletePage;
import pages.validators.VideoTutorials;


import java.time.Duration;


public class BasePage implements IBasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;


    // Authenticated navigation

    public static final Logger log = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @Step("Open Website")
    @Override
    public void open(){
        String baseUrl = "https://automationexercise.com";
        driver.get(baseUrl);
        log.info("Browser: {} baseUrl: {}", driver.getTitle(),baseUrl);
    }

    @Override
    public HeaderImpl Header() {
        return new HeaderImpl(driver);
    }

    @Override
    public FooterImpl Footer() {
        return new FooterImpl(driver);
    }


}