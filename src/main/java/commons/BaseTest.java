package commons;

import browserFactory.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;
    protected final Log log;

    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    public WebDriver getDriverInstance() {
        return this.driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser) {
            case CHROME:
                driver = new ChromeDriverManager().getBrowserDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriverManager().getBrowserDriver();
                break;
            case EDGE:
                driver = new EdgeDriverManager().getBrowserDriver();
                break;
            case SAFARI:
                driver = new SafariDriverManager().getBrowserDriver();
                break;
            default:
                throw new BrowserNotSupportedException(browserName);
        }

        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        return driver;
    }
}
