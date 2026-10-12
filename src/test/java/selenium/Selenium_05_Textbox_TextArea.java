package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Selenium_05_Textbox_TextArea {
    WebDriver driver;
    By loadingIcon = By.cssSelector("div.oxd-loading-spinner");
    String firstName;
    String lastName;
    String fullName;
    String emailAddress;
    String password, userName, passportNumber, passportComment;
    String reviewProduct;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        driver = new FirefoxDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        firstName = "Truc";
        lastName = "Truong";
        fullName = firstName + " " + lastName;
        emailAddress = firstName + "." + lastName + new Random().nextInt(9999) + "@gmail.com";
        userName = firstName + "." + lastName + new Random().nextInt(9999);
        password = "123456@Tt";
        reviewProduct = "good product\n good price";
        passportNumber = "CD-123456";
        passportComment = "CD123456\nNguyen Thi Minh Khai\n phuong Xuan Hoa\n Tp.HCM";
    }

    @Test //gắn chỉ dẫn
    public void TC_01_OrangeHRM() throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertTrue(isLoadingIconHidden());

        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();

        //driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        //or click vào Add button
        driver.findElement(By.xpath("//button[contains(string(),'Add')]")).click();

        //Chờ cho tất cả các icon loading biến mất sau 30s
        Assert.assertTrue(isLoadingIconHidden());


        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);
        //get EmployeeID
        String employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value");

        //bật 'Create Login Details'
        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div/label")).click();


        //điền thông tin Username, Password, Confirm password
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();


        sleep(4000);
        //Verify Text Save successfully
        Assert.assertEquals(driver.findElement(By.cssSelector("p.oxd-text--toast-message")).getText(),"Successfully Saved");

        //Loading Icon 1 - Add new
        Assert.assertTrue(isLoadingIconHidden());
        //Loading Icon 2 - Personal Details
        Assert.assertTrue(isLoadingIconHidden());

        //Verify data in "Personal Detail" screen
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getDomProperty("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getDomProperty("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"), employeeID);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Assert.assertTrue(isLoadingIconHidden());

        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button[contains(string(),'Add')]")).click();

        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(passportComment);
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        sleep(3000);
        //Verify Text Save successfully
        Assert.assertEquals(driver.findElement(By.cssSelector("p.oxd-text--toast-message")).getText(),"Successfully Saved");
        //Loading Icon
        Assert.assertTrue(isLoadingIconHidden());
        /*---------------------------------------------------------------------*/

        //Verify passport
        driver.findElement(By.xpath("//div[text()='" + passportNumber + "']/parent::div/following-sibling::div//i[contains(@class,'bi-pencil-fill')]")).click();

        sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getDomProperty("value"), passportNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getDomProperty("value"), passportComment);

        //Logout
        driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        //Login bẳng user ở step 5
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //click My Info
        driver.findElement(By.xpath("//span[text()='My Info']")).click();
        sleep(3000);

        //Verify thông tin đúng
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getDomProperty("value"),firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getDomProperty("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"), employeeID);


    }

    private boolean isLoadingIconHidden() {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon)));
    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        driver.quit();
    }

}