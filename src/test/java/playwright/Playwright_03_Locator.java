package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Playwright_03_Locator {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome").setHeadless(false).setSlowMo(50));
        page = browser.newPage();

    }

    @Test
    public void TC_01_Role() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");

        page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Yes"));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue"));
        page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Account"));
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Address Book"));
        page.getByRole(AriaRole.SEARCH, new Page.GetByRoleOptions().setName("search"));
    }

    @Test
    public void TC_02_Label() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");

        page.getByLabel("First Name");
        page.getByLabel("Last Name");
        page.getByLabel("Login");
        page.getByLabel("Yes");
        page.getByLabel("Desktops");

    }

    @Test
    public void TC_03_Placeholder() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");

        page.getByPlaceholder("Search").fill("iMAC");
        page.getByPlaceholder("First Name").fill("Truc");
        //page.getByPlaceholder("Password").fill("1234Test");
        page.getByPlaceholder("Password Confirm").fill("1234Test");

    }

    @Test
    public void TC_04_Text() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");

        page.getByText("If you already have an account with us, please login at the ");
        page.getByText("login page");
        page.getByText("I have read and agree to the ");

    }

    @Test
    public void TC_05_AltText() {//dùng cho hình ảnh
        page.navigate("https://opencart.abstracta.us/index.php?route=product/category&path=24");

        page.getByAltText("HTC Touch HD");
        page.getByAltText("iPhone");
        page.getByAltText("Palm Treo Pro");

    }

    @Test
    public void TC_06_ByTitle() {
        page.navigate("https://opencart.abstracta.us/index.php?route=product/category&path=24");

        page.getByTitle("Palm Treo Pro");
        page.getByTitle("HTC Touch HD");
    }

    @Test
    public void TC_07_ByTextid() {
        page.navigate("https://demo-bank.vercel.app/");

        page.getByTestId("login-input");
        page.getByTestId("password-input");

    }

    @Test
    public void TC_08_CSS() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");

        page.locator("input.btn-primary");
        page.locator("input#input-firstname");
        page.locator("a[href*='information/contact']");

    }

    @Test
    public void TC_09_Xpath() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");

        page.locator("//label[contains(string(),'Yes')]/input");
        page.locator("//a[text()='Brands']");
        page.locator("//div[@id='content']/h1");
    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}
