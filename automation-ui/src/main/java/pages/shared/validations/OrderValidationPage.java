package pages.shared.validations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.shared.validations.interfaces.IOrderValidationPage;

public class OrderValidationPage extends BaseValidationPage implements IOrderValidationPage {

    public OrderValidationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void downloadInvoice() {
        String download_xpath = "//a[contains(@href, '/download_invoice/')]";
        WebElement download = this.driver.findElement(By.xpath(download_xpath));
        download.click();
    }
}
