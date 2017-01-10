import net.sf.sahi.client.Browser;

/**
 * Created by vmaksimenko on 10.01.2017.
 */
public class DriverProvider {

    private static Browser browser;

    public static Browser initSahiBrowser() {
        return initSahiBrowser(System.getProperty("browser.name"));
    }

    public static Browser initSahiBrowser(String browserName) {
        browser = new Browser(browserName);
        browser.open();
        return browser;
    }

    public static void tearDownSahiBrowser() {
        browser.setSpeed(100);
        browser.close();
    }

}
