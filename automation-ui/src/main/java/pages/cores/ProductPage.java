package pages.cores;

import DTO.ProductInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;


import org.openqa.selenium.interactions.Actions;



public class ProductPage extends BasePage {
    private final Actions actions;

    public ProductPage(WebDriver driver) {
        super(driver);
        actions = new Actions(driver);
    }





}
