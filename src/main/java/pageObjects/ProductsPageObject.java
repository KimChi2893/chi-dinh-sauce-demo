package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.ProductsPageUI;

public class ProductsPageObject extends BasePage {
    WebDriver driver;

    public ProductsPageObject(WebDriver driver){
        this.driver = driver;
    }

    @Step("Verify the PRODUCTS page title")
    public String getPageTitle() {
        return getPageTitle(driver);
    }

    @Step("Verify that the PRODUCTS page displays with product items")
    public int getInventoryItemCount() {
        return getListElementSize(driver, ProductsPageUI.INVENTORY_ITEM);
    }

    @Step("Click Open Menu Button")
    public void clickOpenMenuButton() {
        waitForElementClickable(driver, ProductsPageUI.OPEN_MENU_BUTTON);
        clickOnElement(driver, ProductsPageUI.OPEN_MENU_BUTTON);
    }

    @Step("Click Logout link")
    public LoginPageObject clickLogoutLink() {
        waitForElementClickable(driver, ProductsPageUI.LOGOUT_LINK);
        clickOnElement(driver, ProductsPageUI.LOGOUT_LINK);
        return PageGeneratorManager.getLoginPageObject(driver);
    }
}
