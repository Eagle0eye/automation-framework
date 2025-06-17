package base;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public enum DRIVERS {
    CHROME, FIREFOX, EDGE,  SAFARI;
    public  WebDriver getDriver() {
        return switch (this) {
            case CHROME -> new ChromeDriver();
            case FIREFOX -> new FirefoxDriver();
            case EDGE -> new EdgeDriver();
            case SAFARI -> new SafariDriver();
            default -> throw new NotFoundException("Driver Not Found");
        };

    }
}
