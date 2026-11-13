package playwright;

import com.microsoft.playwright.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.lang.Thread.sleep;

public class Playwright_11_Default_Checkbox_Radio {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome").setHeadless(false).setSlowMo(50));
        page = browser.newPage();

    }

    @Test
    public void TC_01_KendoUI(){
        page.navigate("https://demos.telerik.com/kendo-ui/checkbox/index");

        Locator realSizeCheckboxBy = page.locator("//label[text()='Rear side airbags']/preceding-sibling::span/input");
        Locator dualZoneCheckboxBy = page.locator("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        realSizeCheckboxBy.uncheck();
        dualZoneCheckboxBy.check();
        //dualZoneCheckboxBy.setChecked(true); //check
        //dualZoneCheckboxBy.setChecked(false); //uncheck

        //Verify
        assertThat(realSizeCheckboxBy).not().isChecked();
        assertThat(dualZoneCheckboxBy).isChecked();


    }

    @Test
    public void TC_02_Jotform_Checkbox(){
        page.navigate("https://automationfc.github.io/multiple-fields/");


        List<Locator> checkboxes = page.locator("input[type='checkbox']").all();

        //Kiểm tra tất cả checkboxes chưa chọn (deselected)
        for(Locator checkbox : checkboxes){
            assertThat(checkbox).not().isChecked();
        }

        //Chọn hết tất cả checkboxes
        for(Locator checkbox : checkboxes){
            checkbox.check();
        }

        //Kiểm tra toàn bộ đã được chọn
        for(Locator checkbox : checkboxes){
            Assert.assertTrue(checkbox.isChecked());
        }

        //Bỏ chọn hết toàn bộ
        for(Locator checkbox : checkboxes){
            checkbox.uncheck();
        }

        //Chọn 1 checkbox bất kỳ
        for(Locator checkbox : checkboxes){
            if(!checkbox.isChecked() && checkbox.getAttribute("value").startsWith("Emotional")){
                checkbox.check();
            }
        }

    }

    @Test
    public void TC_03_Radio() throws InterruptedException {
        page.navigate("https://demos.telerik.com/kendo-ui/radiobutton/index");

        Locator petroRadio14 = page.locator("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::span/input");
        Locator petroRadio16 = page.locator("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::span/input");
        Locator petroRadio18 = page.locator("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::span/input");

        //Chọn
        petroRadio16.check();
        sleep(3000);

        //Verify
        assertThat(petroRadio16).isChecked();
        assertThat(petroRadio14).not().isChecked();

        //Bỏ chọn (chọn qua item khác để bỏ chọn item cũ)
        petroRadio18.check();
        sleep(3000);

        assertThat(petroRadio18).isChecked();
        assertThat(petroRadio16).not().isChecked();
    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}
