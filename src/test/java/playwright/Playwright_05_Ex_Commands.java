package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Playwright_05_Ex_Commands {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                .setChannel("firefox").setHeadless(false).setSlowMo(50));
        page = browser.newPage();

    }

    @Test //gắn chỉ dẫn
    public void TC_01_URL(){
        page.navigate("https://live.techpanda.org/index.php");
        page.locator("div.footer a[title='My Account']").click();

        Assert.assertEquals(page.url(), "https://live.techpanda.org/index.php/customer/account/login/");

        page.locator("a[title='Create an Account']").click();
        Assert.assertEquals(page.url(), "https://live.techpanda.org/index.php/customer/account/create/");

    }
    @Test
    public void TC_02_Title(){
        page.navigate("https://live.techpanda.org/index.php");
        page.locator("div.footer a[title='My Account']").click();

        Assert.assertEquals(page.title(), "Customer Login");
        page.locator("a[title='Create an Account']").click();
        Assert.assertEquals(page.title(), "Create New Customer Account");

    }
    @Test
    public void TC_03_Navigate(){
        page.navigate("https://live.techpanda.org/index.php");
        page.locator("div.footer a[title='My Account']").click();

        Assert.assertEquals(page.url(), "https://live.techpanda.org/index.php/customer/account/login/");

        page.goBack();
        Assert.assertEquals(page.url(), "https://live.techpanda.org/index.php");

        page.goForward();
        Assert.assertEquals(page.url(), "https://live.techpanda.org/index.php/customer/account/login/");

    }
    @Test
    public void TC_04_PageSource(){
        page.navigate("https://live.techpanda.org/index.php");
        page.locator("div.footer a[title='My Account']").click();

        Assert.assertTrue(page.content().contains("Login or Create an Account"));
        page.locator("a[title='Create an Account']").click();

        Assert.assertTrue(page.content().contains("Create an Account"));
    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}
