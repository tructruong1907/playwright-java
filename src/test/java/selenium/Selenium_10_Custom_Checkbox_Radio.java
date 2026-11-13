package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Selenium_10_Custom_Checkbox_Radio {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        driver = new FirefoxDriver();

        jsExecutor = (JavascriptExecutor) driver; //ép kiểu tường minh

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test //gắn chỉ dẫn
    public void TC_01_Ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");

        By newUserRadio = By.cssSelector("input#id_new_user");

        /*Trường hợp 1: Thẻ input bị che - vẫn dùng để verify được => FAILED*/
        //Verify
/*        Assert.assertFalse(driver.findElement(newUserRadio).isSelected()); //deselected
        if(!driver.findElement(newUserRadio).isSelected()){
            driver.findElement(newUserRadio).click(); //label đang che lên input
        }*/

        /*Trường hợp 2: Thẻ label dùng click - không dùng để verify được => FAILED*/
/*        newUserRadio = By.cssSelector("label.new-user");
        driver.findElement(newUserRadio).click();
        Assert.assertTrue(!driver.findElement(newUserRadio).isSelected()); //FAILED*/

        /*Trường hợp 3: Thẻ label dùng click - dùng thẻ input để verify =>PASSED*/
        //Thực tế với 1 element thường không define thành 2 biến
        // Tăng số lượng dòng code lên + dễ gây hiểu lầm khi đọc code
/*        By newUserRadioClick =  By.cssSelector("label.new-user");
        By newUserInputVerify = By.cssSelector("input#id_new_user");

        if(!driver.findElement(newUserRadioClick).isSelected()){
            driver.findElement(newUserRadioClick).click();
        }
        Assert.assertTrue(driver.findElement(newUserInputVerify).isSelected());*/

        /*Trường hợp 4: Thẻ input dùng click + verify =>PASSED*/
        //Hàm click của Selenium bị lỗi vì nó luôn check clickable của 1 element trước
        //Hàm click của Javascript Executor vì nó không quan tâm element có bị che/ẩn => work around
        By acceptCheckbox = By.cssSelector("input#id_accept_tos");

        if(!driver.findElement(newUserRadio).isSelected()){
            jsExecutor.executeScript("arguments[0].click();", driver.findElement(newUserRadio));
        }
        sleep(3000);
        Assert.assertTrue(driver.findElement(newUserRadio).isSelected());

        if(!driver.findElement(acceptCheckbox).isSelected()){
            jsExecutor.executeScript("arguments[0].click();", driver.findElement(acceptCheckbox));
        }
        sleep(3000);
        Assert.assertTrue(driver.findElement(acceptCheckbox).isSelected());

    }
    @Test
    public void TC_02_Google_Doc() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        //Checkbox
        By checkboxQuang = By.xpath("//div[@aria-label='Quảng Bình']");
        //Verify deselected
        Assert.assertEquals(driver.findElement(checkboxQuang).getDomAttribute("aria-checked"),"false");
        //Click
        driver.findElement(checkboxQuang).click();

        //Verify
        Assert.assertEquals(driver.findElement(checkboxQuang).getDomAttribute("aria-checked"),"true");
    }
    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}