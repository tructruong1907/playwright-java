package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Selenium_03_Locator {
    WebDriver driver;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://opencart.abstracta.us/index.php?route=account/register");
    }

    //execute : viết test case
    //cú pháp HTML: Tagname (thẻ) - Attribute (thuộc tính) - Value (giá trị thuộc tính)
    //cú pháp XPath: //tagname[@attribute = 'value']
    //cú pháp CSS:  tagname[attribute = 'value']

    @Test //gắn chỉ dẫn
    public void TC_01_ID(){
        driver.findElement(By.id("input-firstname")).sendKeys("Truc");
        driver.findElement(By.id("input-lastname")).sendKeys("Truong");
    }

    @Test
    public void TC_02_Class(){
        driver.findElement(By.className("btn-primary")).click();
    }

    @Test
    public void TC_03_Name(){
        driver.findElement(By.name("password")).sendKeys("Truc@@@111");
    }

    @Test
    public void TC_04_LinkText(){
        driver.findElement(By.linkText("Recurring payments"));
        driver.findElement(By.linkText("Wish List"));
    }

    @Test
    public void TC_05_PartialLink(){
        driver.findElement(By.partialLinkText("Recurring")).click();
        driver.findElement(By.partialLinkText("Wish")).click();
        driver.findElement(By.partialLinkText("Recurring")).click();
    }

    @Test
    public void TC_06_Tagname(){
        int textboxSize = driver.findElements(By.tagName("input")).size();
        System.out.println(textboxSize);

    }

    @Test
    public void TC_07_CSS(){
        driver.get("https://opencart.abstracta.us/index.php?route=account/register");

        driver.findElement(By.cssSelector("input#input-email")).sendKeys("truc@gmail.com"); //css kết hợp ID

        driver.findElement(By.cssSelector("a.agree")); //css kết hợp class

        driver.findElement(By.cssSelector("input[name='email']")); //css kết hợp name

        driver.findElement(By.cssSelector("a[href='https://opencart.abstracta.us:443/index.php?route=account/transaction']"));

        driver.findElement(By.cssSelector("a[href*='account/transaction']"));

        int textboxSize = driver.findElements(By.cssSelector("input")).size();
        System.out.println(textboxSize);

    }
    @Test
    public void TC_08_Xpath(){
        driver.get("https://opencart.abstracta.us/index.php?route=account/register");

        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("truc@gmail.com"); //css kết hợp ID

        driver.findElement(By.xpath("//a[@class='agree']")); //css kết hợp class

        driver.findElement(By.xpath("//input[@name='email']")); //css kết hợp name

        driver.findElement(By.xpath("//a[@href='https://opencart.abstracta.us:443/index.php?route=account/transaction']"));
        driver.findElement(By.xpath("//a[text()='Transactions']"));

        driver.findElement(By.xpath("//a[contains(@href,'account/return')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Address')]"));

        int textboxSize = driver.findElements(By.xpath("//input")).size();
        System.out.println(textboxSize);
    }
    @Test
    public void TC_09_Xpath_Text(){
        driver.get("https://automationfc.github.io/basic-form/");

        //Text()
        driver.findElement(By.xpath("//label[text()='First Name']"));
        driver.findElement(By.xpath("//label[text()='Last Name']"));

        //contains text()
        driver.findElement(By.xpath("//h5[contains(text(),'Hello World!')]"));

        //contains string()
        driver.findElement(By.xpath("//h5[contains(string(),'Viet Nam')]"));
    }
    @Test
    public void TC_10_Xpath_AndOr(){
        driver.get("https://live.techpanda.org/index.php/customer/account/create/");

        //And
        driver.findElement(By.xpath("//span[@class='label' and text()='Account']"));
        //Or
        driver.findElement(By.xpath("//input[@type='password' or name='password']"));

    }
    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}