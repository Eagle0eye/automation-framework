package base;

import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class BaseUITest {
    private static final String URL = "https://automationexercise.com/";
    private static final Logger log = LoggerFactory.getLogger(BaseUITest.class);
    protected final WebDriver driver;

    public BaseUITest(DRIVERS driverType) {
        this(driverType, DEVICES.MAXSIZE); // Default to MAXSIZE
    }

    public BaseUITest(DRIVERS driverType, DEVICES device) {
        this.driver = DriverFactory.setup(driverType, device);
    }

    @BeforeSuite
    public void setup() {
        log.info("Initializing BaseUITest");
        driver.get(URL);
    }

    @AfterSuite
    public void teardown() {
        DriverFactory.cleanup();
    }
}
