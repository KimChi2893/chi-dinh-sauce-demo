package tests;

import commons.BaseTest;
import commons.GlobalConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.ProductsPageObject;
import utils.ExcelUtils.SpreadsheetUtil;

import java.io.File;
import java.io.IOException;

@Feature("Login Test")
public class Login_Logout_Apply_Data_Driven extends BaseTest {
    String browserName;
    WebDriver driver;

    LoginPageObject loginPage;
    ProductsPageObject productsPage;
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        this.browserName =  browserName;
        driver = getBrowserDriver(browserName, url);
        loginPage = PageGeneratorManager.getLoginPageObject(driver);
    }

    @Description("Login")
    @Test
    public void Login_Logout() throws IOException {
        String fileSeparator = GlobalConstants.FILE_SEPARATOR;
        String filePath = GlobalConstants.PROJECT_PATH +  fileSeparator + "src" + fileSeparator + "test" + fileSeparator + "java"  + fileSeparator + "resources" + fileSeparator + GlobalConstants.TEST_DATA_EXCEL_FILENAME;

        int row = 1;
        var spreadsheet = new SpreadsheetUtil(new File(filePath));
        spreadsheet.switchToSheet(GlobalConstants.TEST_DATA_EXCEL_FILE_SHEET);

        // User can login successfully with an account to see the PRODUCTS page then logout successfully to see the Login page
        // Apply data driven to perform testing with 3 accounts: standard_user, problem_user, performance_glitch_user
        for (;row <=spreadsheet.getRowCountInSheet(); row++){
            String username = spreadsheet.getCellData("Username", row);
            String password = spreadsheet.getCellData("Password", row);
            loginPage.inputToUsernameTextbox(username);
            loginPage.inputToPasswordTextbox(password);
            productsPage = loginPage.clickLoginButton();

            //When login successfully, verify the PRODUCTS page title
            Assert.assertEquals(productsPage.getPageTitle(), "Swag Labs");

            //Verify that the PRODUCTS page displays "success" with 6 product item
            Assert.assertEquals(productsPage.getInventoryItemCount(), 6);

            // logout successfully to see the Login page
            productsPage.clickOpenMenuButton();
            loginPage = productsPage.clickLogoutLink();
            Assert.assertTrue(loginPage.isLoginButtonDisplayed());
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
