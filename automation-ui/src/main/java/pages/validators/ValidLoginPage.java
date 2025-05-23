package testing.pages.validators;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testing.pages.BasePage;


public class ValidLoginPage extends BasePage {

    @FindBy(css = ".navbar-nav > li:nth-child(10) > a:nth-child(1)")  private WebElement correctLogin;

    @FindBy(css = ".login-form > form:nth-child(2) > p:nth-child(4)") private WebElement errorMessage;

    public ValidLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean correctLogin() {
        String text = correctLogin.getText();
        if (text.startsWith("Logged in as")) {
            log.info("Verify correct login successful as {}.", correctLogin.getText().substring(12, text.length()));
            return true;
        }
        return false;
    }

    public boolean incorrectLogin(){
        log.info("verify incorrect login");
        return errorMessage.getText().contentEquals("Your email or password is incorrect!");
    }

}
