package testing.ecloud;

import net.sf.sahi.client.Browser;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import testing.ecloud.helpers.DriverProvider;

public class SahiTestExample {
	private Browser b;

	@Features("FEATURE NAME")
	@TestCaseId("CASE ID")
	@Test(groups = "GROUP")
	public void test() throws InterruptedException {
		b.navigateTo(System.getProperty("server.address"));
		b.textbox("userName[1]").setValue(System.getProperty("user.login"));
		b.password("password[1]").setValue(System.getProperty("user.password"));
		b.submit("Login[1]").click();


	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws InterruptedException {
		b = DriverProvider.getBrowser();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(){
		DriverProvider.tearDownSahiBrowser();
	}
}
