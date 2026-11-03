package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Selenium_07_Custom_Dropdown {
    WebDriver driver;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test //gắn chỉ dẫn
    public void TC_01_Jquery() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectDropdownItem("span#speed-button","ul#speed-menu div.ui-menu-item-wrapper","Fast");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");

        selectDropdownItem("span#files-button","ul#files-menu li div.ui-menu-item-wrapper","Some unknown file");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "Some unknown file");

        selectDropdownItem("span#number-button","ul#number-menu div.ui-menu-item-wrapper","3");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "3");

    }

    @Test
    public void TC_02_Jquery(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectDropdownItem("div.dropdown","div.item>span.text","Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Christian");


        selectDropdownItem("div.dropdown","div.item>span.text","Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Justen Kitsune");
    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectDropdownItem("li.dropdown-toggle","ul.dropdown-menu a","Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");
        sleep(3000);

        selectDropdownItem("li.dropdown-toggle","ul.dropdown-menu a","First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");
        sleep(3000);

    }

    @Test
    public void TC_04_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectDropdownItem("div.dropdown","div.item>span.text","Albania");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Albania");
        sleep(3000);

        editDropdownItem("input.search","div.item>span.text","Bahamas");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Bahamas");
        sleep(3000);
    }

    @Test
    public void TC_05_Honda() throws InterruptedException {
        driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
        sleep(5000);
        Select select = new Select(driver.findElement(By.cssSelector("select.province")));


        //Custom combobox
        selectDropdownItem("div.dropdown","div.dropdown-menu>a","CITY L (Trắng ngọc)");
        sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "CITY L (Trắng ngọc)");

        //Default Combobox
        selectDropdownItem("select.province","select.province>option","Đồng Nai");
        sleep(3000);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Đồng Nai");
    }

    private void selectDropdownItem(String parentLocator, String childLocator, String textItem) {
        //1. Click vao dropdown
        driver.findElement(By.cssSelector(parentLocator)).click();

        //2. Chờ cho tất cả các item được load ra
        //3. Duyệt qua hết tất cả các item
        List<WebElement> listItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        //4. Kiểm tra label text: nếu đúng thì click chọn, nếu không đúng thì duyệt tiếp
        for(WebElement item : listItems){
           if(item.getText().equals(textItem)){
               item.click();
               break;
           }
       }
    }

    private void editDropdownItem(String parentLocator, String childLocator, String textItem) {
        //1. Click vao dropdown
        driver.findElement(By.cssSelector(parentLocator)).clear();
        driver.findElement(By.cssSelector(parentLocator)).sendKeys(textItem);

        List<WebElement> listItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        //4. Kiểm tra label text: nếu đúng thì click chọn, nếu không đúng thì duyệt tiếp
        for(WebElement item : listItems){
            if(item.getText().equals(textItem)){
                item.click();
                break;
            }
        }
    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}