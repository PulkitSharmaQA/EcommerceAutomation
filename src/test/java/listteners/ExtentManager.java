package listteners;

import java.io.File;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Base.DriverManager;
import utils.Screenshots;
public class ExtentManager implements ITestListener {
	
	public ExtentSparkReporter spark ;
	public ExtentReports reports ;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


	    @Override
	    public void onStart(ITestContext context) {

	        String reportPath = System.getProperty("user.dir")
	                + File.separator + "reports"
	                + File.separator + "ExtentReport_" + System.currentTimeMillis() + ".html";

	        spark = new ExtentSparkReporter(reportPath);

	        spark.config().setReportName("Ecommerce Automation Report");
	        spark.config().setDocumentTitle("Automation Testing");
	        spark.config().setTheme(Theme.DARK);

	        reports = new ExtentReports();
	        reports.attachReporter(spark);

	        reports.setSystemInfo("Tester", "Pulkit Sharma");
	        reports.setSystemInfo("Environment", "QA");
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	    	test.set(reports.createTest(result.getName()));
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	    	test.get().log(Status.PASS, "Test Passed: " + result.getName());
	        String path = Screenshots.screenShots(DriverManager.getDriver(),result.getName());
	        test.get().addScreenCaptureFromPath(path);
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        test.get().log(Status.FAIL, "Test Failed: " + result.getName());
	        String path = Screenshots.screenShots(DriverManager.getDriver(),result.getName());
	        test.get().addScreenCaptureFromPath(path);
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        reports.flush();

	    }
	}