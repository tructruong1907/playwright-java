package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.SelectOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Playwright_08_Default_Dropdown {
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

    @Test //gắn chỉ dẫn
    public void TC_01_Saucedemo() {
        page.navigate("https://www.saucedemo.com/");
        //login
        page.locator("input#user-name").fill("standard_user");
        page.locator("input#password").fill("secret_sauce");
        page.locator("input#login-button").click();

        //Handle dropdown
        Locator productDropdown = page.locator("select.product_sort_container");

        //Value or Text
        productDropdown.selectOption("za");
        Assert.assertEquals(productDropdown.locator("option:checked").textContent(),"Name (Z to A)");

        //Text or Value
        productDropdown.selectOption("Price (low to high)");
        Assert.assertEquals(productDropdown.locator("option:checked").textContent(),"Price (low to high)");

        //Index
        productDropdown.selectOption(new SelectOption().setIndex(0));
        Assert.assertEquals(productDropdown.locator("option:checked").textContent(),"Name (A to Z)");

        //Value
        productDropdown.selectOption(new SelectOption().setValue("za"));
        Assert.assertEquals(productDropdown.locator("option:checked").textContent(),"Name (Z to A)");

        //Label Text
        productDropdown.selectOption(new SelectOption().setLabel("Price (low to high)"));
        Assert.assertEquals(productDropdown.locator("option:checked").textContent(),"Price (low to high)");

        //Is multiple
        Assert.assertNull(productDropdown.getAttribute("multiple")); //Recommend
        //Assert.assertEquals(productDropdown.getAttribute("multiple"), null);
        PlaywrightAssertions.assertThat(productDropdown).not().hasAttribute("multiple","multiple"); //Not recommend

        //Count = getOptions ben Selenium
        Locator productDropdownOptions = productDropdown.locator("option");
        Assert.assertEquals(productDropdownOptions.count(),4); //cach 1
        PlaywrightAssertions.assertThat(productDropdownOptions).hasCount(4); //cach 2

    }


    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}
