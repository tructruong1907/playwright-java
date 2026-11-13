package playwright;

import com.microsoft.playwright.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.lang.Thread.sleep;

public class Playwright_12_Custom_Checbox_Radio {
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
    public void TC_01_Ubuntu() throws InterruptedException {
        page.navigate("https://login.ubuntu.com/");

        Locator newUserRadio = page.locator("input#id_new_user");
        Locator acceptCheckbox = page.locator("input#id_accept_tos");

        if(!newUserRadio.isChecked()){
            newUserRadio.dispatchEvent("click");
            //hoac
            //newUserRadio.evaluate("el => el.click()");
        }
        sleep(3000);
        assertThat(newUserRadio).isChecked();

        if(!acceptCheckbox.isChecked()){
            acceptCheckbox.dispatchEvent("click");
        }
        sleep(3000);
        assertThat(acceptCheckbox).isChecked();

    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}
