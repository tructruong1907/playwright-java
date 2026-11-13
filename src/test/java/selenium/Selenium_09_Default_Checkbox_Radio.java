package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Selenium_09_Default_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test //gắn chỉ dẫn
    public void TC_01_KendoUI(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By realSizeCheckboxBy = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");
        By leatherTrimCheckboxBy = By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input");
        By dualZoneCheckboxBy = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        //Kiêm tra checkbox đã được select hay chưa
        if(!driver.findElement(realSizeCheckboxBy).isSelected()){
            driver.findElement(realSizeCheckboxBy).click();
        }
        //Chọn/chưa chọn
        Assert.assertTrue(driver.findElement(realSizeCheckboxBy).isSelected());
        Assert.assertTrue(driver.findElement(leatherTrimCheckboxBy).isSelected());
        Assert.assertFalse(driver.findElement(dualZoneCheckboxBy).isSelected());

        //Disabled/Enabled
        Assert.assertTrue(driver.findElement(realSizeCheckboxBy).isEnabled());
        Assert.assertFalse(driver.findElement(leatherTrimCheckboxBy).isEnabled());
        Assert.assertTrue(driver.findElement(dualZoneCheckboxBy).isEnabled());

        //Click chọn dual zone
        if(!driver.findElement(dualZoneCheckboxBy).isSelected()){
            driver.findElement(dualZoneCheckboxBy).click();
        }
        Assert.assertTrue(driver.findElement(dualZoneCheckboxBy).isSelected());

    }
    @Test
    public void TC_02_Jotform_Checkbox(){
        driver.get("https://automationfc.github.io/multiple-fields/");


        List<WebElement> checkboxes = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[type='checkbox']")));

        //Kiểm tra tất cả checkboxes chưa chọn (deselected)
        for(WebElement checkbox : checkboxes){
            Assert.assertFalse(checkbox.isSelected());
        }

        //Chọn hết tất cả checkboxes
        for(WebElement checkbox : checkboxes){
            if(!checkbox.isSelected()){
                checkbox.click();
            }
        }

        //Kiểm tra toàn bộ đã được chọn
        for(WebElement checkbox : checkboxes){
            Assert.assertTrue(checkbox.isSelected());
        }

        //Bỏ chọn hết toàn bộ
        for(WebElement checkbox : checkboxes){
            if(checkbox.isSelected()){
                checkbox.click();
            }
        }

        //Chọn 1 checkbox bất kỳ
        for(WebElement checkbox : checkboxes){
            if(!checkbox.isSelected() && checkbox.getDomProperty("value").equals("Emotional Disorder")){
                checkbox.click();
            }
        }

        //

    }

    @Test
    public void TC_03_Radio() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        //By petroRadio14 = By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::span/input");
        By petroRadio16 = By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::span/input");
        By petroRadio18 = By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::span/input");

        //Chọn
        if(!driver.findElement(petroRadio16).isSelected()){
            driver.findElement(petroRadio16).click();
        }
        sleep(3000);

        //Verify
        Assert.assertTrue(driver.findElement(petroRadio16 ).isSelected());

        //Bỏ chọn (chọn qua item khác để bỏ chọn item cũ)
        if(!driver.findElement(petroRadio18).isSelected()){
            driver.findElement(petroRadio18).click();
        }
        sleep(3000);
        Assert.assertTrue(driver.findElement(petroRadio18).isSelected());
        Assert.assertFalse(driver.findElement(petroRadio16 ).isSelected());
    }


    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}