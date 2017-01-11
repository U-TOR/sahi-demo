package testing.ecloud;

import net.sf.sahi.client.Browser;

/**
 * Created by vmaksimenko on 10.01.2017.
 */
public class DriverProvider {

    public static Browser initSahiBrowser() {
        return initSahiBrowser(System.getProperty("browser.name"));
    }

    public static Browser initSahiBrowser(String browserName) {
        Browser browser = new Browser(browserName);
        browser.open();
        return browser;
    }

    public static void tearDownSahiBrowser(Browser browser) {
        browser.setSpeed(100);
        browser.close();
    }

}
