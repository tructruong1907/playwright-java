package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class Playwright_06_Ex_Commands_Locator {
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
    public void TC_01_Register_MailChimp() {
        page.navigate("https://login.mailchimp.com/signup/");

        Locator emailCheckbox =  page.locator("input#email");
        emailCheckbox.fill("cecilia-ttruc@gmail.com");

        //Op 1 : tự động điền username bằng cách sử dụng tab
        emailCheckbox.press("Tab");

        //validate password - Number
        Locator passwordTextbox = page.locator("input#new_password");
        passwordTextbox.fill("123");
        passwordTextbox.press("Tab");
        PlaywrightAssertions.assertThat(page.locator("li.lowercase-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.uppercase-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.number-char.completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.special-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("//li[@class='8-char not-completed']")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("//li[@class='username-check completed']")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("button#create-account-enabled")).isDisabled();
        PlaywrightAssertions.assertThat(page.locator("input[id='marketing_newsletter']")).not().isChecked();

        //validate password - only lowercase
        passwordTextbox.fill("a");
        passwordTextbox.press("Tab");
        PlaywrightAssertions.assertThat(page.locator("li.lowercase-char.completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.uppercase-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.number-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.special-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("//li[@class='8-char not-completed']")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("//li[@class='username-check not-completed']")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("button#create-account-enabled")).isDisabled();
        PlaywrightAssertions.assertThat(page.locator("input[id='marketing_newsletter']")).not().isChecked();

        //validate password - only uppercase
        passwordTextbox.fill("ABC");
        passwordTextbox.press("Tab");
        PlaywrightAssertions.assertThat(page.locator("li.lowercase-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.uppercase-char.completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.number-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.special-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("//li[@class='8-char not-completed']")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("//li[@class='username-check completed']")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("button#create-account-enabled")).isDisabled();
        PlaywrightAssertions.assertThat(page.locator("input[id='marketing_newsletter']")).not().isChecked();

        //validate password - special-char
        passwordTextbox.fill("!@##");
        passwordTextbox.press("Tab");
        PlaywrightAssertions.assertThat(page.locator("li.lowercase-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.uppercase-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.number-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.special-char.completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("//li[@class='8-char not-completed']")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("//li[@class='username-check completed']")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("button#create-account-enabled")).isDisabled();
        PlaywrightAssertions.assertThat(page.locator("input[id='marketing_newsletter']")).not().isChecked();

        //validate password - 8-char
        passwordTextbox.fill("m1234567");
        passwordTextbox.press("Tab");
        PlaywrightAssertions.assertThat(page.locator("li.lowercase-char.completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.uppercase-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.number-char.completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("li.special-char.not-completed")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("//li[@class='8-char completed']")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("//li[@class='username-check completed']")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("button#create-account-enabled")).isDisabled();
        PlaywrightAssertions.assertThat(page.locator("input[id='marketing_newsletter']")).not().isChecked();


        //validate password - FULL
        passwordTextbox.fill("Tructruong@123");
        passwordTextbox.press("Tab");
        PlaywrightAssertions.assertThat(page.locator("li.lowercase-char.completed")).isHidden();
        PlaywrightAssertions.assertThat(page.locator("li.uppercase-char.completed")).isHidden();
        PlaywrightAssertions.assertThat(page.locator("li.number-char.completed")).isHidden();
        PlaywrightAssertions.assertThat(page.locator("li.special-char.completed")).isHidden();
        PlaywrightAssertions.assertThat(page.locator("//li[@class='8-char completed']")).isHidden();
        PlaywrightAssertions.assertThat(page.locator("//li[@class='username-check completed']")).isHidden();
        PlaywrightAssertions.assertThat(page.locator("button#create-account-enabled")).isEnabled();
        PlaywrightAssertions.assertThat(page.locator("input[id='marketing_newsletter']")).not().isChecked();

    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}
