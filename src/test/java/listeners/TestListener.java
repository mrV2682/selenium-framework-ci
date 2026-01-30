package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import core.DriverManager;
import org.openqa.selenium.*;
import org.testng.ITestListener;
import org.testng.ITestResult;
import report.ExtentManager;
import report.ExtentTestManager;
import retry.RetryAnalyzer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onStart(org.testng.ITestContext context) {
        System.out.println(">>> TestListener STARTED");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(
                result.getMethod().getMethodName()
        );
        test.info("Thread ID: " + Thread.currentThread().getId());
        ExtentTestManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        // 1️ Kiểm tra retry analyzer
        Object retry = result.getMethod().getRetryAnalyzer(result);

        if (retry instanceof RetryAnalyzer) {
            RetryAnalyzer retryAnalyzer = (RetryAnalyzer) retry;

            // Nếu còn retry → log WARNING, KHÔNG screenshot
            if (retryAnalyzer.isRetryAvailable()) {
                ExtentTestManager.getTest()
                        .warning("Test failed but will be retried");

                return;
            }
        }

        ExtentTest test = ExtentTestManager.getTest();
        test.fail(result.getThrowable());

        try {
            WebDriver driver = DriverManager.getDriver();
            /**
             * If this is API test no need to screenshot
             */
            if (driver == null) {
                test.info("No WebDriver found (API test) - skipping screenshot");
                return;
            }

            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Path dest = Path.of(
                    "reports",
                    result.getName()
                            + "_" + Thread.currentThread().getId()
                            + "_" + System.currentTimeMillis()
                            + ".png"
            );

            Files.createDirectories(dest.getParent());
            Files.copy(src.toPath(), dest);

            test.addScreenCaptureFromPath(dest.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        System.out.println(">>> TestListener FINISHED - flushing report");
        extent.flush();
    }

}
