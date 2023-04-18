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

@Feature("Login Test")
public class Login extends BaseTest {
    String browserName;
    WebDriver driver;

    String username = "standard_user", password = "secret_sauce";

    LoginPageObject loginPage;
    ProductsPageObject productsPage;
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        this.browserName =  browserName;
        driver = getBrowserDriver(browserName, url);
        loginPage = PageGeneratorManager.getLoginPageObject(driver);
    }

    @BeforeTest
    public void setupTestData(){
        //ExcelUtil.setExcelFileSheet(GlobalConstants.TEST_DATA_EXCEL_FILE_SHEET);
    }

    @Description("Login")
    @Test
    public void TC_01_Login() {
        loginPage.inputToUsernameTextbox(username);
        loginPage.inputToPasswordTextbox(password);
        productsPage = loginPage.clickLoginButton();
        //When login successfully, verify the PRODUCTS page title
        Assert.assertEquals(productsPage.getPageTitle(), "Swag Labs");

        //Verify that the PRODUCTS page displays "success" with 6 product item
        Assert.assertEquals(productsPage.getInventoryItemCount(), 6);

       // User can login successfully with an account to see the PRODUCTS page then logout successfully to see the Login page
        // - Apply data driven to perform testing with 3 accounts: standard_user, problem_user, performance_glitch_user
    }

    @Description("Logout")
    @Test
    public void TC_02_Logout() {
        // logout successfully to see the Login page
        productsPage.clickOpenMenuButton();
        loginPage = productsPage.clickLogoutLink();
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
