/**
 * Copyright Tyto Software Pvt. Ltd.
 */


import net.sf.sahi.client.Browser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Test
public class SahiTestCase {
	Browser b;
	String browserName = "Chrome";


//	public abstract void setBrowser();
//
//

	@BeforeTest
	public void setUp(){
		b = new Browser(browserName);
		b.open();
	}

	@AfterTest
	public void tearDown(){
		b.setSpeed(100);
		b.close();
	}

	@Test()
	public void test() {
		b.navigateTo("192.168.6.134");

		System.out.println("Hello");

		assertTrue(true);

	}
}
