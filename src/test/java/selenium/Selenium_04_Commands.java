package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Selenium_04_Commands {
    WebDriver driver;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test //gắn chỉ dẫn
    public void TC_01_WebBrowser(){

    }
    @Test
    public void TC_02_WebElemt(){
        //Element
        WebElement webelement = driver.findElement(By.cssSelector(""));
        //click
        webelement.click();

        //Dung voi loai element dang editable (Textbox/TextArea/Dropdown)
        //Xóa dữ liệu đang có trước khi nhập mới
        webelement.clear();

        //Nhập dữ liệu (Textbox/TextArea/Dropdown)
        webelement.sendKeys("");
        webelement.sendKeys(Keys.ENTER);

        //Dùng để kiểm tra 1 element đang hiển thị
        //Có kích thước cụ thể/ nhìn thấy được
        //Ap dụng cho tất cả các loại element
        //Hiển thị trả vể true/false
        Assert.assertTrue(webelement.isDisplayed());
        Assert.assertFalse(webelement.isDisplayed());

        //Áp dụng cho tát cả các loại element
        //Kiểm tra element có thể thao tác được không (không bị disable)
        //disable = read-only
        Assert.assertTrue(webelement.isEnabled());

        //Aps dụng cho 3 loại element (checkbox/Radio Button/ Dropdown)
        Assert.assertFalse(webelement.isSelected());

        //Trả về text cua element - bao gồm cả sub-element
        webelement.getText();

        webelement.getAttribute("value");
        webelement.getDomAttribute("value");

        webelement.getDomProperty("value");

        //bg color/font/size/...
        webelement.getCssValue("color");

        //Lấy ra vị trí của element so với màn hình
        Point firstNameLocation = webelement.getLocation();
        firstNameLocation.getX();
        firstNameLocation.getY();

        //Lấy ra kích thước element (rộgn x cao)
        Dimension firstnameSize =  webelement.getSize();
        firstnameSize.getWidth();
        firstnameSize.getHeight();

        Rectangle firstnameRect =  webelement.getRect();
        firstnameRect.getDimension();
        firstnameRect.getPoint();

        //Xử lý element trong Shadown DOM
        webelement.getShadowRoot();

        //Dựa vào locator để lấy ra tagname của element đó
        String tagName =  webelement.getTagName();

        webelement.getAccessibleName();
        webelement.getAriaRole();

        //Bắt buộc nằm trong form mới dùng được
        webelement.submit();

    }
    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}