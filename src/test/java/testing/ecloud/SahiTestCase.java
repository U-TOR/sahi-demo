package testing.ecloud;

import net.sf.sahi.client.Browser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testing.ecloud.DriverProvider;

public class SahiTestCase {
	Browser b;

	@BeforeTest
	public void setUp() {
		b = DriverProvider.initSahiBrowser();
	}

	@AfterTest
	public void tearDown(){
		DriverProvider.tearDownSahiBrowser(b);
	}

	@Test()
	public void test() {
		b.navigateTo("https://192.168.6.134");
		b.textbox("userName[1]").setValue("admin");
		b.password("password[1]").setValue("changeme");
		b.submit("Login[1]").click();
	}
}
