package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Playwright_06_Commands_Locator {
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
    public void TC_01_Commands_Locator() {
        page.navigate("https://live.techpanda.org/");

        /*-- Chaining --*/
        //Locator.locator(selector)
        Locator footerLink = page.locator("div.footer");
        Locator myAccLink = footerLink.locator("a[title='My Account']");

        //locator.filter(options) - handle data table
        Locator row = page.locator("div.oxd-table-row").filter(new Locator.FilterOptions().setHasText("Truc Truong"));
        row.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Edit")).click();

        //locator.nth - lấy ra phần tử bất kì trong ds locator
        Locator rows = page.locator("div.oxd-table-row"); //lấy ra tất cả danh sách locator
        rows.nth(0).click(); //lấy dòng đầu tiên
        rows.nth(rows.count() - 1).click();//lấy dòng cuối cùng

        //loctor.first
        rows.first().click();

        //locator.last
        rows.last().click();

        //locator.all() - findElements trong selenium - trả về 1 list<Locator>
        //web demo: jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid
        List<Locator> countryText = page.locator("//tr/td[3]").all(); //lấy hết tất cả tên các nước trong cột country
        List<String> allText = new ArrayList<String>();
        for(Locator locator : countryText){
            allText.add(locator.textContent());
        }

        //locator.and - 2 dk đúng sẽ ra kq đúng, 1 dk sai thì ra kq sai
        Locator saveButton = page.locator("button.oxd-button").and(page.getByText("Save"));
        saveButton.click();

        //Locator.or - 1 trong 2 dk đúng thì kq là đúng
        Locator cancelButton = page.locator("button.oxd-button").or(page.getByText("Cancel"));
        cancelButton.click();

        /*-- Actions --*/
        //fill() - xóa nội dung cũ và nhập nội dung mới

        //clear() - xóa nội dung input

        //pressSequentially(text,option)
        Locator searchBox = page.locator("input[plateholder='Type for hints...']");
        searchBox.pressSequentially("Truc", new Locator.PressSequentiallyOptions().setDelay(100));

        //Press(key) - muốn gửi 1 key bất kì
        searchBox.press("Ctrl+A");

        //setChecked(boolean) - khi thấy element đã được chọn rồi thì nó sẽ không làm gì cả
        //sử dụng cho textbox/radio
        Locator createLoginSwitch = page.locator(".oxd-switch-input[type='checkbox']");
        //Đảm bảo switch luôn ở trạng thái ON, bất kể trước đó đang ON hay OFF
        createLoginSwitch.setChecked(true);
        assertThat(createLoginSwitch).isChecked();

        //selectOption()
        searchBox.selectOption("");
        page.selectOption("","");

        //forcus() - đưa trỏ vào ô KHÔNG gõ gì - dùng để test validate on-focus (highlight border) - ít dùng
        Locator firstNameInput = page.locator("input[plateholder='First Name']");
        firstNameInput.focus();
        assertThat(firstNameInput).isFocused();

        //dragTo(target)
        List<String> items = page.selectOption("","");
        searchBox.dragTo(createLoginSwitch);

        //inputValue() - tuong tu ham getDomProperty() ben selenium
        searchBox.inputValue();

        //So sanh giua Page va Locator
        //Page (not recommend - da loi thoi)
        page.click("button[title='Login']");
        //Locator (recommend)
        Locator loginButton =  page.locator("button[title='Login']");
        loginButton.click();

        //allTextContents() - lấy hết nội dung của 1 hàng or 1 cột
        Locator countryLocator = page.locator("//tr/td[3]");
        List<String> countryNames = countryLocator.allTextContents(); //lay toan bo cot country
        int countryCount = countryLocator.count(); //(count cua playwright)

        Assert.assertFalse(countryNames.isEmpty()); //kiem tra co empty khong
        Assert.assertTrue(countryNames.contains("Viet Nam")); //co chua gia tri nay khong
        //Selenium
        WebDriver driver = new FirefoxDriver();
        List<WebElement> countryNames2 = driver.findElements(By.xpath("//tr/td[3]"));
        int countryNamesCount = countryNames2.size(); //count cua selenium

        List<String> countryText2 = new ArrayList<>();
        for (WebElement element2 : countryNames2){
            countryText2.add(element2.getText());
        }
        Assert.assertTrue(countryText2.contains("Viet Nam"));

        //allInnerTexts() - lấy text hiển thị trên UI
        List<String> names = page.locator("//tr/td[3]").allInnerTexts();
        Assert.assertTrue(names.contains("Viet Nam"));

        //boundingBox() - giống getRect() bên selenium - ít dùng - sử dụng để test design trên UI
        Locator boudingBoxLocator = page.locator("div.oxd-table-row");
        BoundingBox boudingBox = boudingBoxLocator.boundingBox();
        double x = boudingBox.x;
        double y = boudingBox.y;
        double width = boudingBox.width;
        double height = boudingBox.height;

        /*-- Locator Assertion --*/
        String homepageTitle = page.title();
        //PlaywrightAssertions.assertThat(page).hasTitle(homepageTitle); //chua hop ly
        PlaywrightAssertions.assertThat(page).hasTitle("jQuery UI Selectmenu - Default functionality");

        PlaywrightAssertions.assertThat(page).hasURL("https://live.techpanda.org/");
        PlaywrightAssertions.assertThat(page).hasURL("*/");

        //trả về true false (verify) - chịu sự quản lý của locator (testNG), khong wait
        Assert.assertTrue(boudingBoxLocator.isVisible());
        //Chờ lúc assert - nếu như element/ locator truyền vào chưa visible thì sẽ chờ tiếp
        PlaywrightAssertions.setDefaultAssertionTimeout(10000);
        PlaywrightAssertions.assertThat(boudingBoxLocator).isVisible(); //nhân wait của playwrightAssert (ham action)

        PlaywrightAssertions.assertThat(boudingBoxLocator).isChecked();
        PlaywrightAssertions.assertThat(boudingBoxLocator).isHidden();
        PlaywrightAssertions.assertThat(boudingBoxLocator).isEditable();

        Locator searchTextbox = page.locator("input#search");
        Locator searchButton = page.locator("button");

        PlaywrightAssertions.assertThat(searchTextbox).hasClass("input-texxt required-entry");
        PlaywrightAssertions.assertThat(searchTextbox).hasAttribute("placeholder", "Search entrie store here...");
        PlaywrightAssertions.assertThat(searchTextbox).hasCount(1); //dem co bao nhieu element
        PlaywrightAssertions.assertThat(searchButton).hasCount(5);

        //su dung cho textbox
        searchTextbox.fill("LG");
        PlaywrightAssertions.assertThat(searchTextbox).hasValue("LG");

        //not() - Phủ định ngược lại cho các hàm
        PlaywrightAssertions.assertThat(searchTextbox).not().hasValue("Samsung");
        PlaywrightAssertions.assertThat(searchTextbox).not().hasAttribute("placeholder", "Search entrie store here...");
        PlaywrightAssertions.assertThat(searchTextbox).not().hasCount(2);
        PlaywrightAssertions.assertThat(searchTextbox).not().hasClass("input-texxt required-entry");

        /*-- Page Assertion --*/
    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}
