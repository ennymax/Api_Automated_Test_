package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

public class ExtentReport implements ITestListener {

    public static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports init() {

        Path path = Paths.get("./Report/");
        // if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                // fail to create directory
                e.printStackTrace();
            }
        }
        ExtentSparkReporter spark = new ExtentSparkReporter("./Report/" + "Report" + System.currentTimeMillis() + ".html");

        extent = new ExtentReports();
        extent.setReportUsesManualConfiguration(true);
        extent.attachReporter(spark);

        return extent;
    }

    public synchronized void onStart(ITestContext context) {
       // System.out.println("Test Suite started!");
    }

    public synchronized void onFinish(ITestContext context) {
        extent.flush();
        test.remove();
    }

    public synchronized void onTestStart(ITestResult result) {
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());

        extentTest.assignCategory(result.getTestContext().getSuite().getName());

        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));

        System.out.println("**************************************** " + (result.getMethod().getMethodName() + " Started " + " ************************************** "));
    }

    public synchronized void onTestSuccess(ITestResult result) {
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
        test.get().assignCategory(result.getMethod().getMethodName().toUpperCase());
        test.get().pass(MarkupHelper.createLabel(result.getName() + " The Test Case Passed", ExtentColor.GREEN));
        System.out.println("***********Passed********************* " + (result.getMethod().getMethodName() + " " + getTime(result.getEndMillis()) + " *********Passed*********** \n\n"));
    }

    public synchronized void onTestFailure(ITestResult result) {
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
        test.get().fail(result.getThrowable());
        test.get().assignCategory(result.getMethod().getMethodName().toUpperCase());
        test.get().fail(MarkupHelper.createLabel(result.getName() + " The Test Case Failed", ExtentColor.RED));
        System.out.println("**********Failed********************* " + (result.getMethod().getMethodName() + "  " + getTime(result.getEndMillis()) + " **********Failed************* \n \n"));
    }

    public synchronized void onTestSkipped(ITestResult result) {
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
        test.get().skip(result.getThrowable());
        test.get().assignCategory(result.getMethod().getMethodName().toUpperCase());
        test.get().skip(MarkupHelper.createLabel(result.getName() + " The Test Case Skipped", ExtentColor.YELLOW));
        System.out.println("***************************Skipped********************* " + (result.getMethod().getMethodName() + "  " + getTime(result.getEndMillis()) + " ********Skipped************ \n\n"));
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
