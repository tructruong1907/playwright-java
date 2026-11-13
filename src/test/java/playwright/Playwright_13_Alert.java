package playwright;

import com.microsoft.playwright.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.latest.network.Network;
import org.openqa.selenium.devtools.latest.network.model.Headers;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

import static java.lang.Thread.sleep;

public class Playwright_13_Alert {
    Playwright playwright;
    Browser browser;
    Page page;
    Alert alert;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome").setHeadless(false).setSlowMo(50));
        page = browser.newPage();

    }

    @Test
    public void TC_01_Confirm_Alert(){
        page.navigate("https://live.techpanda.org/index.php/");

        //Alert = Dialog
        //page.onDialog(); //xu ly 1 alert - tu dong close
        //page.onceDialog(); //xu ly 1 hoac nhieu hon 1 alert xuat hien lien tiep - phai su dung ham offDialog de tat

//        page.onDialog( a -> {
//            a.type(); // alert/ confirm/ prompt
//            a.message(); // title/ text cuar alert
//            a.dismiss(); // Cancel alert
//            a.accept(); // accept alert/ confirm (khong dung cho prompt)
//            a.accept(""); // accept prompt
//            a.defaultValue(); //lay ra gia tri mac dinh trong promt alert, neu ko co se tra ra null
//        });



        page.locator("//a[text()='Mobile']").click();

        //tìm element bằng xpath
        page.locator("//a[@title='Xperia']/following-sibling::div//a[@class='link-compare']").click();
        Assert.assertTrue(page.locator("//span[text()='The product Sony Xperia has been added to comparison list.']").isVisible());

        //tìm element bằng css
        page.locator("a[title='IPhone']~div>div.actions a.link-compare").click();
        Assert.assertTrue(page.locator("//span[text()='The product IPhone has been added to comparison list.']").isVisible());


        //nguoc voi selenium - playwright xu ly alert truoc roi moi click cho alert xuat hien sau
        page.onDialog( dialog -> {
            //Verify alert confirm
            Assert.assertEquals(dialog.type(), "confirm");

            //Verrify message
            Assert.assertEquals(dialog.message(), "Are you sure you would like to remove all products from your comparison?");

            //accept alert
            dialog.accept();
        });
        page.locator("//a[text()='Clear All']").click();

        //verify text sau khi clear all
        Assert.assertTrue(page.locator("//span[text()='The comparison list was cleared.']").isVisible());

    }

    @Test
    public void TC_02_Promt_Alert() {
        page.navigate("https://automationfc.github.io/basic-form/index.html");
        String text = "Truc Truong";

        page.onDialog( dialog -> {
            Assert.assertEquals(dialog.message(), "I am a JS prompt");
            dialog.accept(text);
        });
        page.locator("button[onclick='jsPrompt()']").click();

        Assert.assertEquals(page.locator("p#result").textContent(), "You entered: " + text);

    }

    @Test
    public void TC_03_Authentication_Alert1() throws InterruptedException {
        //cach don gian nhat de pass authen la truyen truc tiep u/p trong link -> admin:admin
        page.navigate("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(page.locator("//p[contains(text(),'Congratulations! You must have the proper credentials.')]").isVisible());
    }

    @Test
    public void TC_04_Athentication_Alert2() {
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin","admin"));
        page = browserContext.newPage();

        page.navigate("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(page.locator("//p[contains(text(),'Congratulations! You must have the proper credentials.')]").isVisible());
    }

    @Test
    public void TC_05_Athentication_Alert3() {
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));

        String token = Base64.getEncoder().encodeToString("admin:admin".getBytes(StandardCharsets.UTF_8));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setExtraHTTPHeaders(Map.of("Authorization","Basic " + token)));
        page = context.newPage();

        page.navigate("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(page.locator("//p[contains(text(),'Congratulations! You must have the proper credentials.')]").isVisible());
    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}
