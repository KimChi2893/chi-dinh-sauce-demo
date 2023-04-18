package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    @Step("Enter to Username textbox with value {0}")
    public void inputToUsernameTextbox(String username) {
        waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, username);
    }

    @Step("Enter to Password textbox with value {0}")
    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Click Login button")
    public ProductsPageObject clickLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickOnElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getProductsPageObject(driver);
    }

    @Step("Verify display Login Button on the Login page")
    public boolean isLoginButtonDisplayed() {
        waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
        return isElementDisplayed(driver, LoginPageUI.LOGIN_BUTTON);
    }
}
