package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Selenium_04_Ex_Commands_02 {
    WebDriver driver;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    @Test //gắn chỉ dẫn
    public void TC_01_Mailchimp() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        WebElement emailCheckbox =  driver.findElement(By.cssSelector("input#email"));
        emailCheckbox.sendKeys("cecilia-ttruc@gmail.com");

        //Op 1 : tự động điền username bằng cách sử dụng tab
        emailCheckbox.sendKeys(Keys.TAB);

        //Op 2: tự động điền username bằng cách click vào user name textbox
        //WebElement usernameCheckbox = driver.findElement(By.cssSelector("input#new_username"));
        //usernameCheckbox.click();
        sleep(3000);

        //validate password - Number
        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#new_password"));
        passwordTextbox.sendKeys("123");
        passwordTextbox.sendKeys(Keys.ENTER);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("button#create-account-enabled")).isEnabled());
        Assert.assertFalse(driver.findElement(By.cssSelector("input[id='marketing_newsletter']")).isSelected());
        sleep(3000);

        //clear info
        passwordTextbox.clear();

        //validate password - only lowercase
        passwordTextbox.sendKeys("a");
        passwordTextbox.sendKeys(Keys.ENTER);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("button#create-account-enabled")).isEnabled());
        Assert.assertFalse(driver.findElement(By.cssSelector("input[id='marketing_newsletter']")).isSelected());
        sleep(3000);

    }
    @Test
    public void TC_02_(){

    }
    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}