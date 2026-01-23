package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {

            String projectPath = System.getProperty("user.dir");
            String reportPath = projectPath
                    + File.separator
                    + "reports"
                    + File.separator
                    + "extent-report.html";

            System.out.println(">>> Extent report path: " + reportPath);

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(reportPath);

            reporter.config().setReportName("Selenium Automation Report");
            reporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
