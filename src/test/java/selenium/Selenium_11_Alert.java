package selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.latest.network.Network;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.devtools.latest.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import static java.lang.Thread.sleep;

public class Selenium_11_Alert {
    WebDriver driver;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test //gắn chỉ dẫn
    public void TC_01_Confirm_Alert(){
        driver.get("https://live.techpanda.org/index.php/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        //tìm element bằng xpath
        driver.findElement(By.xpath("//a[@title='Xperia']/following-sibling::div//a[@class='link-compare']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());

        //tìm element bằng css
        driver.findElement(By.cssSelector("a[title='IPhone']~div>div.actions a.link-compare")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']")).isDisplayed());

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();

        //chi can wait alert xuat hien trong DOM, khong can hien thi len UI
        //Ham nay co the vua cho vua switch to alert
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());

        //get text ra để verify title của Alert
        Assert.assertEquals(alert.getText(),"Are you sure you would like to remove all products from your comparison?");

        //Accept
        alert.accept();

        //verify text sau khi clear all
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());

    }
    @Test
    public void TC_02_Promt_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.cssSelector("button[onclick='jsPrompt()']")).click();

        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        sleep(3000);

        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        String text = "Truc Truong";
        alert.sendKeys(text);
        sleep(3000);

        alert.accept();
        sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + text);

    }
    @Test
    public void TC_03_Authentication_Alert1() throws InterruptedException {
        //cach don gian nhat de pass authen la truyen truc tiep u/p trong link -> admin:admin
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }
    @Test
    public void TC_04_Athentication_Alert2() {
        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("http://the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p[contains(text()," +
                "'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }
    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
       // driver.quit();
    }

}