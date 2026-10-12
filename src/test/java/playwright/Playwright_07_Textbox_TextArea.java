package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.lang.Thread.sleep;

public class Playwright_07_Textbox_TextArea {
    Playwright playwright;
    Browser browser;
    Page page;
    By loadingIcon = By.cssSelector("div.oxd-loading-spinner");
    String firstName;
    String lastName;
    String fullName;
    String emailAddress;
    String password, userName, passportNumber, passportComment;
    String reviewProduct;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome").setHeadless(false).setSlowMo(50));
        page = browser.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        firstName = "Truc";
        lastName = "Truong";
        fullName = firstName + " " + lastName;
        emailAddress = firstName + "." + lastName + new Random().nextInt(9999) + "@gmail.com";
        userName = firstName + "." + lastName + new Random().nextInt(9999);
        password = "123456@Tt";
        reviewProduct = "good product\n good price";
        passportNumber = "CD-123456";
        passportComment = "CD123456\nNguyen Thi Minh Khai\n phuong Xuan Hoa\n Tp.HCM";

    }

    @Test //gắn chỉ dẫn
    public void TC_01_OrangeHRM() throws InterruptedException {

        page.locator("//input[@name='username']").fill("Admin");
        page.locator("//input[@name='password']").fill("admin123");
        page.locator("//button[@type='submit']").click();

        page.locator("//span[text()='PIM']").click();
        page.locator("//button[contains(string(),'Add')]").click();

        page.locator("input[name='firstName']").fill(firstName);
        page.locator("input[name='lastName']").fill(lastName);
        //get EmployeeID
        String employeeID = page.locator("//label[text()='Employee Id']/parent::div/following-sibling::div/input").inputValue();

        page.locator("//button[contains(string(),'Save')]").click();

        //Verify data in "Personal Detail" screen
        assertThat(page.locator("input[name='firstName']")).hasValue(firstName);
        assertThat(page.locator("input[name='lastName']")).hasValue(lastName);
        assertThat(page.locator("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).hasValue(employeeID);

        page.locator("//a[text()='Immigration']").click();

        page.locator("//h6[text()='Assigned Immigration Records']/following-sibling::button[contains(string(),'Add')]").click();

        page.locator("//label[text()='Number']/parent::div/following-sibling::div/input").fill(passportNumber);
        page.locator("//label[text()='Comments']/parent::div/following-sibling::div/textarea").fill(passportComment);
        page.locator("//button[contains(string(),'Save')]").click();

    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}
