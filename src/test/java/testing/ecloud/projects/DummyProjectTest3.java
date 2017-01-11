package testing.ecloud.projects;

import net.sf.sahi.client.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import testing.ecloud.helpers.DriverProvider;


public class DummyProjectTest3 {
	private Browser b;

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws InterruptedException {
		b = DriverProvider.getBrowser();
	}

	@Features("Projects tests")
	@TestCaseId("1446")
	@Test(groups = "projects")
	public void test() throws InterruptedException {
		b.navigateTo("https://192.168.6.134");
		b.textbox("userName[1]").setValue("admin");
		b.password("password[1]").setValue("changeme");
		b.submit("Login[1]").click();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(){
		DriverProvider.tearDownSahiBrowser();
	}
}
