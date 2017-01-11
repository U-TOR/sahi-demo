package testing.ecloud.projects;

import net.sf.sahi.client.Browser;
import org.testng.annotations.*;
import testing.ecloud.DriverProvider;


public class DummyProjectTest2 {
	Browser b;

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws InterruptedException {
		b = DriverProvider.initSahiBrowser();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(){
		DriverProvider.tearDownSahiBrowser(b);
	}

	@Test(groups = "projects")
	public void test() throws InterruptedException {
		b.navigateTo("https://192.168.6.134");
		b.textbox("userName[1]").setValue("admin");
		b.password("password[1]").setValue("changeme");
		b.submit("Login[1]").click();
	}
}
