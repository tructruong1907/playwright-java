package playwright;

import com.microsoft.playwright.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.lang.Thread.sleep;

public class Playwright_10_Button {
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
    public void TC_01_Fahasa() throws InterruptedException {
        page.navigate("https://www.fahasa.com/customer/account/create");

        page.locator("li.popup-login-tab-login").click();

        Locator loginButton = page.locator("button.fhs-btn-login");

        //Verify text button
        assertThat(loginButton).hasText("Đăng nhập");

        //Có trạng thái là disable
        assertThat(loginButton).isDisabled();
        //màu nền là xám
        assertThat(loginButton).hasCSS("background-color","rgba(0, 0, 0, 0)");
        Assert.assertEquals(rgbToHex((String) loginButton.evaluate("el => getComputedStyle(el).backgroundColor")),"#000000");

        //Nhap du lieu
        page.locator("input#login_username").pressSequentially("truc.truong@gmail.com");
        page.locator("input#login_password").pressSequentially("123456");
        sleep(3000);

        //verify trạng thái enabled
        assertThat(loginButton).isEnabled();
        //màu nền là đỏ
        assertThat(loginButton).hasCSS("background-color","rgb(201, 33, 39)");
        Assert.assertEquals(rgbToHex((String) loginButton.evaluate("el => getComputedStyle(el).backgroundColor")),"#C92127");


    }

    public String rgbToHex(String rgb) {
        String[] p = rgb.replaceAll("[^0-9,]", "").split(",");
        return String.format("#%02X%02X%02X", Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]));
    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}
