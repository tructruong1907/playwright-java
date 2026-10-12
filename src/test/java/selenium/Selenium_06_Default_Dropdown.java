package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Selenium_06_Default_Dropdown {
    WebDriver driver;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test //gắn chỉ dẫn
    public void TC_01_Saucedemo(){
        driver.get("https://www.saucedemo.com/");
        //login
        driver.findElement(By.cssSelector("input#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input#login-button")).click();
        //Handle dropdown
        By sortDropdownBy = By.cssSelector("select.product_sort_container");
        new Select(driver.findElement(sortDropdownBy)).selectByVisibleText("Price (low to high)");
        Assert.assertEquals(new Select(driver.findElement(sortDropdownBy)).getFirstSelectedOption().getText(), "Price (low to high)");
        //Kiem tra dropdown khong phai la multiple - chon duoc nhieu value
        //Assert.assertFalse(sortDropdown.isMultiple());
        //Chon
        //sortDropdown.selectByIndex(3); //chon theo thu tu index - index bat dau = 0
        //sortDropdown.selectByValue("az"); //chon theo value
        //sortDropdown.selectByContainsVisibleText("high to low"); //chon theo text tuong doi
        //sortDropdown.selectByVisibleText("Price (low to high)"); // chon theo text tuyet doi

        //Bo chon
        /*sortDropdown.deselectByIndex(3);
        sortDropdown.deselectByValue("az");
        sortDropdown.deSelectByContainsVisibleText("high to low");
        sortDropdown.deselectByVisibleText("Price (low to high)");*/

        //Kiem tra chon thanh cong
        //Assert.assertEquals(sortDropdown.getFirstSelectedOption().getText(), "Name (A to Z)");

        //Kiem tra so luong item bao nhieu
        //Assert.assertEquals(sortDropdown.getOptions().size(), 4);


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