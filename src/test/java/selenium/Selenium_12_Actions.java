package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Selenium_12_Actions {
    WebDriver driver;
    Actions actions; //Khai báo Actions
    String osName = System.getProperty("os.name");
    JavascriptExecutor js;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        System.out.println("Driver ID: " + driver.toString());

        actions = new Actions(driver); //Khởi tạo actions
        js = (JavascriptExecutor) driver;

    }

    @Test //gắn chỉ dẫn
    public void TC_01_jQuery_Hover(){
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Myntra_Hover(){
        driver.get("https://www.myntra.com/");

        actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navContent']//a[text()='Kids']"))).pause(Duration.ofSeconds(2)).perform();

        actions.click(driver.findElement(By.xpath("//div[@class='desktop-navContent']//a[text()='Home & Bath']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(),"Kids Home Bath");

    }

    @Test
    public void TC_03_Click_And_Hold(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-state-default"));

        actions.clickAndHold(numbers.get(8)).pause(Duration.ofSeconds(2)).moveToElement(numbers.get(19)).release().perform();

        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(),12);
    }

    @Test
    public void TC_04_ClickAndHold_Random(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-state-default"));

        Keys keys;
        if(osName.contains("Mac OS X")){
            keys = Keys.COMMAND;
        } else {
            keys = Keys.CONTROL;
            }
        actions.keyDown(keys).perform();

        actions.click(numbers.get(8)).pause(Duration.ofSeconds(2)).click(numbers.get(19)).pause(Duration.ofSeconds(2)).click(numbers.get(20)).pause(Duration.ofSeconds(2)).click(numbers.get(29)).pause(Duration.ofSeconds(2)).perform();

        actions.keyUp(keys).perform();

        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(),4);

    }

    @Test
    public void TC_05_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if(driver.toString().contains("Firefox")){
            System.out.println("Scroll with Firefox browser");
            //chi firefox moi bi loi scroll nay, chrome/edge khong can
            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
        }

        actions.pause(Duration.ofSeconds(2));

        actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("P#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_06_Right_Click() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        //Right click
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        //Not hover - visible
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-copy")).isDisplayed());

        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-copy"))).perform();

        //Hover - visible
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-copy.context-menu-hover.context-menu-visible")).isDisplayed());

        //CLick
        driver.findElement(By.cssSelector("li.context-menu-icon-copy")).click();
        sleep(3000);

        //accept alert
        driver.switchTo().alert().accept();
        sleep(3000);

        //Not visible
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-copy")).isDisplayed());
    }

    @Test
    public void TC_07_Drag_Drop_HTML4() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement sourceCircle =  driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle =  driver.findElement(By.cssSelector("div#droptarget"));

        actions.dragAndDrop(sourceCircle, targetCircle).pause(Duration.ofSeconds(2)).perform();

        Assert.assertEquals(targetCircle.getText(), "You did great!");
        Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color")).asHex().toUpperCase() , "#03A9F4");
    }

    @Test
    public void TC_08_Drag_Drop_HTML5() throws InterruptedException, IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        String jsContent = getContentFile(System.getProperty("user.dir") + "/dragDrop/dragDrop.js");
        System.out.println(System.getProperty("user.dir") + "/dragDrop/dragDrop.js");

        js.executeScript(jsContent);
        sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

        js.executeScript(jsContent);
        sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");


    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

}