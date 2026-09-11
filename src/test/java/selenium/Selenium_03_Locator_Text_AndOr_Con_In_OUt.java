package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Selenium_03_Locator_Text_AndOr_Con_In_OUt {
    WebDriver driver;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void TC_01_Xpath_Text(){
        driver.get("https://automationfc.github.io/basic-form/");

        //Text()
        driver.findElement(By.xpath("//h5[text()='Michael Jackson']"));

        //contains text()
        driver.findElement(By.xpath("//h5[contains(text(),'Hello World!')]"));

        //contains string()
        driver.findElement(By.xpath("//h5[contains(string(),'Viet Nam')]"));
    }
    @Test
    public void TC_2_Xpath_And_Or(){
        driver.get("https://live.techpanda.org/index.php/customer/account/create/");

        //And
        driver.findElement(By.xpath("//span[@class='label' and text()='Account']"));
        //Or
        driver.findElement(By.xpath("//input[@type='password' or name='password']"));
    }
    @Test
    public void TC_3_Xpath_Not(){
        driver.get("https://live.techpanda.org/index.php/mobile.html");

        //Not
        driver.findElement(By.xpath("//li[@class='item last']/a[not(@title='Xperia')]"));
    }
    @Test
    public void TC_4_Inside_Outside(){
        driver.get("https://live.techpanda.org/index.php/mobile.html");

        //Inside
        driver.findElement(By.xpath("//ul[contains(@class,'products-grid')]/li[3]"));

        //Outside
        driver.findElement(By.xpath("(//button[@title='Add to Cart'])[2]"));
    }
    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}