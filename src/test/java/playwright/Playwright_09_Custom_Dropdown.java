package playwright;

import com.microsoft.playwright.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.lang.Thread.sleep;

public class Playwright_09_Custom_Dropdown {
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
    public void TC_01_Jquery() {
        page.navigate("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectDropdownItem1("span#speed-button","ul#speed-menu div.ui-menu-item-wrapper","Fast");
        Assert.assertEquals(page.locator("span#speed-button>span.ui-selectmenu-text").textContent(), "Fast");
        assertThat(page.locator("span#speed-button>span.ui-selectmenu-text")).hasText("Fast");

        selectDropdownItem("span#files-button","ul#files-menu li div.ui-menu-item-wrapper","Some unknown file");
        Assert.assertEquals(page.locator("span#files-button>span.ui-selectmenu-text").textContent(), "Some unknown file");


        selectDropdownItem("span#number-button","ul#number-menu div.ui-menu-item-wrapper","3");
        Assert.assertEquals(page.locator("span#number-button>span.ui-selectmenu-text").textContent(), "3");

    }

    @Test
    public void TC_02_Jquery() throws InterruptedException{
        page.navigate("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectDropdownItem("div.dropdown","div.item>span.text","Christian");
        Assert.assertEquals(page.locator("div.divider.text").textContent(),"Christian");
        Thread.sleep(3000);

        selectDropdownItem1("div.dropdown","div.item>span.text","Justen Kitsune");
        Assert.assertEquals(page.locator("div.divider.text").textContent(),"Justen Kitsune");
        Thread.sleep(3000);
    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        page.navigate("https://mikerodham.github.io/vue-dropdowns/");

        selectDropdownItem1("li.dropdown-toggle","ul.dropdown-menu a","Third Option");
        assertThat(page.locator("li.dropdown-toggle")).hasText("Third Option");
        sleep(3000);

        selectDropdownItem1("li.dropdown-toggle","ul.dropdown-menu a","First Option");
        assertThat(page.locator("li.dropdown-toggle")).hasText("First Option");

    }

    @Test
    public void TC_04_Editable() throws InterruptedException {
        page.navigate("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectDropdownItem1("div.dropdown","div.item>span.text","Albania");
        assertThat(page.locator("div.divider")).hasText("Albania");
        sleep(3000);

        editDropdownItem("input.search","div.item>span.text","Bahamas");
        assertThat(page.locator("div.divider")).hasText("Bahamas");
        sleep(3000);
    }

    @Test
    public void TC_05_Honda() throws InterruptedException {
        page.navigate("https://www.honda.com.vn/o-to/du-toan-chi-phi");
        Locator province = page.locator("select.province");

        //Custom combobox
        selectDropdownItem1("div.dropdown","div.dropdown-menu>a","CITY L (Trắng ngọc)");
        sleep(3000);
        assertThat(page.locator("button#selectize-input")).hasText("CITY L (Trắng ngọc)");

        //Default Combobox
        province.selectOption("Đồng Nai");
        Assert.assertEquals(province.locator("option:checked").textContent(),"Đồng Nai");
    }

    //Cach 1 - dung vong lap for
    private void selectDropdownItem(String parentLocator, String childLocator, String textItem) {

        page.locator(parentLocator).click();

        //Tim locaotr khop va tra ve tat ca cac item
        Locator allItems = page.locator(childLocator);

        //Dung vong lap duyet qua tung item
        for (int i = 0; i < allItems.count(); i++){
            // Dung nth de lay ra tung element va gan tam vao bien item
            Locator item = allItems.nth(i);

            //Kiem tra dieu kien neu khop vs text truyen vao thi click vao element va thoat vong lap
            if(item.innerText().equals(textItem)){
                item.click();
                break;
            }
        }
    }
    //Cach 2 - dung ham cua playwright
    private void selectDropdownItem1(String parentLocator, String childLocator, String textItem) {

        page.locator(parentLocator).click();

        page.locator(childLocator).getByText(textItem, new Locator.GetByTextOptions().setExact(true)).click();
    }

    private void editDropdownItem(String parentLocator, String childLocator, String textItem) {
        //1. Click vao dropdown
        page.locator(parentLocator).fill(textItem);

        page.locator(childLocator).getByText(textItem, new Locator.GetByTextOptions().setExact(true)).click();
    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}
