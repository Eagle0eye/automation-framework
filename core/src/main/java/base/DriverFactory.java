package base;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DriverFactory {

    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<DRIVERS> currentDriverType = new ThreadLocal<>();

    public static WebDriver setup(DRIVERS driverType, DEVICES device) {
        if (driverThreadLocal.get() == null) {
            initDriver(driverType, device);
        }
        else if (!driverType.equals(currentDriverType.get())) {
            log.warn("WebDriver already initialized with {}. Requested: {}. Reusing existing one.",
                    currentDriverType.get(), driverType);
        }
        return driverThreadLocal.get();
    }

    private static void initDriver(DRIVERS driverType, DEVICES device) {
        currentDriverType.set(driverType);
        WebDriver newDriver = driverType.getDriver();
        newDriver.manage().window().setSize(device.size());
        driverThreadLocal.set(newDriver);
    }

    public static void cleanup() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
            currentDriverType.remove();
        }
    }
}