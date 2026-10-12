package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.Geolocation;
import com.microsoft.playwright.options.Proxy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Playwright_04_Commands_Brownser {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    BrowserType browserType;
    Page page;

    @BeforeClass //gắn chỉ dẫn
    //mở web browser
    public void InitialBrowser(){
        playwright = Playwright.create(); //không có tham số/param

        //Trình duyệt ẩn danh - không lưu trữ cookie hoặc thao tác lích sử duyệt web
        //mặc định Headless = true, mình phải chỉnh lại false để cho screen xuất hiện
        //setSlowMo: các step cách nhau 50 milliseconds
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome").setHeadless(false).setSlowMo(50));

        //it sử dụng
        //browserContext = playwright.firefox().launchPersistentContext(Path.of(""));

        page = browser.newPage();

        //các hàm trong playwright
        browserType = playwright.chromium(); //gọi trình duyệt Chrome
        browserType = playwright.firefox(); //gọi trình duyệt firefox
        browserType =  playwright.webkit(); //gọi các trình duyệt khác

        //Web API testing
        APIRequest apiRequest = playwright.request();
        Selectors  selectors = playwright.selectors();

        //Browser Type
        browserType.launch(new BrowserType.LaunchOptions().setHeadless(false)); //hiển thị màn hình khi chạy (UI)
        browserType.launch(new BrowserType.LaunchOptions().setHeadless(true)); //ko hiển thị màn hình khi chạy (non-UI)
        browserType.launch(new BrowserType.LaunchOptions().setSlowMo(500));//các steps chạy cách nhau 1 thời gian được config
        browserType.launch(new BrowserType.LaunchOptions().setChannel("msedge")); //trình duyệt được mở là edge

        //Mặc định đường dẫn download file là thư mục "Download"
        //câu lệnh này giúp chuyển đổi thư mục file được tải về
        String projectPath = System.getProperty("user.dir");
        browserType.launch(new BrowserType.LaunchOptions().setDownloadsPath(Path.of(projectPath + "\\downloadFile")));

        //Đường dẫn đến Excutable Path của trình duyệt - gõ "chrome://version" trên trình duyệt để thấy đường dẫn của Executable path
        //Trỏ vào nơi chứa thư muc đã cài đặt của trình duyệt
        browserType.launch(new BrowserType.LaunchOptions().setExecutablePath(Path.of("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome")));

        //set Proxy
        browserType.launch(new BrowserType.LaunchOptions().setProxy(new Proxy("")));

        //Áp dụng cho Chrome/Edge - gõ "chrome://version" trên trình duyệt để thấy đường dẫn của profile path
        //Chạy với profile nào
        browserType.launchPersistentContext(Path.of("/Users/duke050518/Library/Application Support/Google/Chrome/Profile 1"));

        //Lấy ra đường dẫn cài đặt của trình duyệt
        browserType.executablePath();

        //Cloud Testing: Browser/Sauce Lab/Device Farm/ Lambda Test/...
        //End Point: GRID URL/ HUB URL
        browserType.connect("");

        //Chrome DEV Tool (Testing ít sử dụng)
        browserType.connectOverCDP("");

        //Browser
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setSlowMo(50));
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false).setSlowMo(50));

        // Kiểm tra browser có đang chạy/hoạt động không
        browser.isConnected();

        //Danh sách browser tab/window đang mở
        browser.contexts();

        //Trả về type của browser (Chromium/Firefox/Webkit)
        browser.browserType();
        if(browser.browserType().name().equals("Firefox")){
            System.out.println("");
        }

        //Close browser
        browser.close();

        //Trả về version của browser
        browser.version();

        //Trả về Page để thao tác với element
        browser.newPage();

        //Trả về 1 context thao tác vs window/tab
        browser.newContext();

        //Browser Context
        browserContext = browser.newContext();

        //Browser Context - Get cookie
        List<Cookie> cookies = browserContext.cookies();

        //Browser Context - Gán lại cookie
        browserContext.addCookies(cookies);

        //Browser Context - Lấy tọa độ của 1 point
        browserContext.setGeolocation(new Geolocation(10.803382456462119, 106.76367745511331));

        //Browser Context - giả lập internet offline
        browserContext.setOffline(true);

        //Browser Context - set permission cho trình duyệt
        browserContext.grantPermissions(new ArrayList<>());

        //Browser Context - xóa permission của trình duyệt đang có
        browserContext.clearPermissions();
    }

    @Test
    public void TC_01_() {

    }

    @Test
    public void TC_02_() {

    }

    @Test
    public void TC_03_() {

    }

    @AfterClass //gắn chỉ dẫn
    //Đóng trình duyệt
    public void closeBrowser(){
        browser.close();
        playwright.close(); //close tất cả các browser đã được tạo
    }
}
