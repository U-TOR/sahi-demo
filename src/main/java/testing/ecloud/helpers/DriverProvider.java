package testing.ecloud.helpers;

import net.sf.sahi.client.Browser;
import org.apache.commons.lang.RandomStringUtils;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DriverProvider {

    public static Browser getBrowser() {
        try {
            if (null == webDriver.get() || webDriver.get().toString().contains("(null)"))
                setDriver(initSahiBrowser());
            return webDriver.get();
        } catch (Exception exception) {
            throw new AssertionError("Cannot get driver: " + exception.getMessage());
        }
    }

    public static void setDriver(Browser driver) {
        webDriver.set(driver);
    }

    public static boolean hasDriver() {
        return null != getBrowser() && !getBrowser().toString().contains("(null)");
    }

    public static void tearDownSahiBrowser() {
        Browser browser = getBrowser();
        browser.setSpeed(100);
        browser.close();
    }

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] attachScreenshot(String name) {
        byte [] image = null;
        try {
            Path temp = Files.createTempFile("attachment_" + RandomStringUtils.randomAlphanumeric(5), ".jpg");

            Browser browser = getBrowser();
            browser.focusWindow();
            browser.takePageScreenShot(temp.toString());

            image = Files.readAllBytes(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    private static ThreadLocal<Browser> webDriver = new ThreadLocal<>();

    private static Browser initSahiBrowser() {
        Browser browser = new Browser(System.getProperty("driver.name"));
        browser.open();
        browser.resizeWindow(1366, 768);    // AFAIK it's a min required resolution
        return browser;
    }
}
