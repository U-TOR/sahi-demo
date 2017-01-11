package testing.ecloud.projects;

import net.sf.sahi.client.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import testing.ecloud.helpers.DriverProvider;

import static org.testng.Assert.assertTrue;


public class DummyProjectTest4 {

	private Browser b;

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws InterruptedException {
		b = DriverProvider.getBrowser();
	}

	@Features("Projects tests")
	@TestCaseId("1446")
	@Test(groups = "projects")
	public void test() throws InterruptedException {
		b.navigateTo(System.getProperty("server.address"));
		b.textbox("userName[1]").setValue(System.getProperty("user.login"));
		b.password("password[1]").setValue(System.getProperty("user.password"));
		b.submit("Login[1]").click();

		assertTrue(false, "Failed test");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(){
		DriverProvider.tearDownSahiBrowser();
	}
}
