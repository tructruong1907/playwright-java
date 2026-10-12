package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Playwright_05_Commands_Page {
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
    public void TC_01_Command() throws InterruptedException {
        /*-- Navigate --*/
        page.navigate("https://playwright.com");

        page.reload();
        page.goBack();
        page.goForward();

        page.url();
        page.title();
        page.waitForURL("**/auth/login");
        page.waitForLoadState();

        /*-- Locator --*/
        page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("radio"));
        page.getByLabel("No");
        page.getByPlaceholder("FirstName");
        page.getByText("Iphone");
        page.getByAltText("");
        page.getByTitle("");
        page.getByTestId("login");
        page.locator("//div[@id='content']/h1"); //dùng cho css và xpath

        /*-- Action --*/
        page.click("//div[@id='content']/h1"); //xpath hoặc css - đã bị deprecated

        //Thay thế page.click đã bị lỗi thời
        Locator locator = page.locator("//div[@id='content']/h1");
        locator.click();

        page.dblclick("//div[@id='content']/h1"); //double click

        page.hover("//div[@id='content']/h1");

        page.focus("//div[@id='content']/h1"); //selenium ko có hàm này

        page.fill("input[name='firstName']","Truc");

        page.type("input[name='firstName']","Truc");

        //dùng cho checkbox hoặc radio
        page.check("input#pollanswers-1"); //https://demo.nopcommerce.com/
        page.uncheck("input#pollanswers-2"); //https://demo.nopcommerce.com/

        //dropdown
        page.selectOption("div.toolbar-bottom select[title='Sort By']","Name");
        //tương ứng với bên selenium là
        //new Select(driver.findElement(By.cssSelector("div.toolbar-bottom select[title='Sort By']"))).selectByvisibleText("Name");

        page.dragAndDrop("div#draggable","div#dropgable");
        //new Actions(driver).dragAndDrop(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector("")));

        //Lấy url của project
        String projectPath = System.getProperty("user.dir");

        //upload 1 file
        page.setInputFiles("input[type='file']", Paths.get(projectPath + "/input.txt"));

        //upload nhiều files
        page.setInputFiles("input[type='file']", new Path[]{
                Paths.get(projectPath + "/input1.txt"),
                Paths.get(projectPath + "/input2.txt"),
        }
        );

        page.textContent("div.page-title>h1");
        page.innerHTML("div.page-title>h1");
        page.innerHTML("div.page-title>h1");

        //lấy ra thuộc tính của 1 element
        page.getAttribute("input#search","placeholder");

        page.isDisabled("");
        page.isEnabled("");

        page.isVisible("");
        page.isChecked("");
        page.isEditable("");
        page.isHidden("");

        page.waitForSelector("div.page-tittle>h1"); //đợi element

        //set hàm đợi element cho đến khi element được tìm thấy
        page.waitForSelector("div.page-title>h1", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));

        //chỉ sử dụng khi debug, không dùng khi chạy test
        page.waitForTimeout(300);

        //hàm sleep
        Thread.sleep(300);

        //ép kiểu tường minh
        Object jsValue = page.evaluate("document.domain");
        String domainName = (String)jsValue;

        //Chụp ảnh màn hình -> Lưu vào đường dẫn dưới và chụp full màn hình
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(projectPath + "/screenshot.png")).setFullPage(true));

        page.context();

        page.close();
    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}
