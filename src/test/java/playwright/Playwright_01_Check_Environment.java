package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class Playwright_01_Check_Environment {
    Playwright playwright;
    Browser browser;
    Page page;

    @Test
    public void TC_01_Run_On_Firefox() {
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(50));
        page = browser.newPage();
        page.navigate("https://www.facebook.com/");
        browser.close();
        playwright.close();
    }

    @Test
    public void TC_02_Run_On_Chrome() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome").setHeadless(false).setSlowMo(50));
        page = browser.newPage();
        page.navigate("https://www.facebook.com/");
        browser.close();
        playwright.close();
    }

    @Test
    public void TC_03_Run_On_Edge() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("msedge").setHeadless(false).setSlowMo(50));
        page = browser.newPage();
        page.navigate("https://www.facebook.com/");
        browser.close();
        playwright.close();
    }
}
