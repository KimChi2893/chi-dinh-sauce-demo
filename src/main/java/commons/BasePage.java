package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    public static BasePage getBasePageInstance() {
        return new BasePage();
    }

    private By getByLocator(String locator) {
        By by = null;

        if (locator.startsWith("id=") || locator.startsWith("ID=") || locator.startsWith("Id=")) {
            by = By.id(locator.substring(3));
        } else if(locator.startsWith("class=") || locator.startsWith("Class=") || locator.startsWith("CLASS=")) {
            by = By.className(locator.substring(6));

        }else if(locator.startsWith("name=") || locator.startsWith("Name=") || locator.startsWith("NAME=")) {
            by = By.name(locator.substring(5));

        }else if(locator.startsWith("css=") || locator.startsWith("Css=") || locator.startsWith("CSS=")) {
            by = By.cssSelector(locator.substring(4));

        }else if(locator.startsWith("xpath=") || locator.startsWith("Xpath=") || locator.startsWith("XPATH=") || locator.startsWith("Xpath=")) {
            by = By.xpath(locator.substring(6));

        }else {
            throw new RuntimeException("The locator is not valid");
        }

        return by;
    }

    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));

    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToInput) {
        WebElement element = getWebElement(driver, locator);
        element.clear();
        element.sendKeys(valueToInput);
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void clickOnElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }
    public int getListElementSize(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }
}
