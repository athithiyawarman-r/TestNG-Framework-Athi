package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    static {
        // Initialize Extent Reports once
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("TestReport/extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static void createParentTest(String testName, String description) {
        ExtentTest parent = extent.createTest(testName, description);
        parentTest.set(parent);
    }

    public static void createTestNode(Method method) {
        ExtentTest child = parentTest.get().createNode(method.getName());
        test.set(child);
    }

    public static ExtentTest getTestNode() {
        return test.get();
    }

    public static void logTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            getTestNode().fail(result.getThrowable().getMessage());
        } else {
            getTestNode().pass("Passed");
        }
        extent.flush();
    }
}