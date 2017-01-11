package testing.ecloud.helpers;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

public class TestListener implements ITestListener {

    Allure lifecycle = Allure.LIFECYCLE;

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        if(System.getProperty("trail.enabled") != null && System.getProperty("trail.enabled").equals("true")) {
            Method testMethod = iTestResult.getMethod().getConstructorOrMethod().getMethod();
            if (testMethod.isAnnotationPresent(TestCaseId.class)) {
                int caseId = Integer.parseInt(testMethod.getAnnotation(TestCaseId.class).value());
                try {
                    TestRailRunHelper.getInstance().setTestResult(caseId, TestRailStatus.PASSED, "", "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    @Step("Test failed, see screenshot")
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        DriverProvider.attachScreenshot("failure_" + iTestResult.getName());
        if(System.getProperty("trail.enabled") != null && System.getProperty("trail.enabled").equals("true")) {
//        CommonFunctions.log("trace", trace);

            Method testMethod = iTestResult.getMethod().getConstructorOrMethod().getMethod();
            String errorMessage = iTestResult.getThrowable().getMessage();
            String ticketId = "";

            if (testMethod.isAnnotationPresent(TestCaseId.class)) {
                int caseId = Integer.parseInt(testMethod.getAnnotation(TestCaseId.class).value());
                try {
                    TestRailRunHelper.getInstance().setTestResult(caseId, TestRailStatus.FAILED, errorMessage, ticketId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        if(System.getProperty("trail.enabled") != null && System.getProperty("trail.enabled").equals("true")) {
            try {
                TestRailRunHelper.getInstance().startRun(
                        System.getProperty("trail.run") + System.getProperty("browser.name") + " - " + new Date().toString()
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
         DriverProvider.tearDownSahiBrowser();
    }

    protected Allure getLifecycle() {
        return lifecycle;
    }

}
