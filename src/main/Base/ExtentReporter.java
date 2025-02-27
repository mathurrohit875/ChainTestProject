package main.Base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReporter extends BaseClassUAT2 implements ITestListener {

  private static final ThreadLocal<ExtentTest> extenttestMTEST = new ThreadLocal<>();
  private static ExtentReports extentReportsMTEST;
  private static ExtentTest extentTestMTEST;
  private static String suiteNameMTEST;
  private static String timestampMTEST;
  private static String reportFileNameMTEST;
  private static ExtentSparkReporter htmlReporterMTEST;
  private static String browserMTEST;
  Test testAnnotationMTEST;
  String testNameMTEST;
  ExtentTest testMTEST;
  String authorMTEST, categoryMTEST, deviceMTEST;
  long timestampMillis = System.currentTimeMillis();
  Date date = new Date(timestampMillis);
  SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH.mm a");
  String humanReadableTimestampMTEST, filenameMTEST;
  File destinationFileMTEST, screenshotMTEST;

  @Override
  public void onTestStart(ITestResult result) {

    // Fetch the testName from the @Test annotation
    testAnnotationMTEST = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
    testNameMTEST = testAnnotationMTEST.testName();


    // Use the testName if specified, otherwise fallback to the method name
    testMTEST = extentReportsMTEST.createTest(testNameMTEST.isEmpty() ? result.getMethod().getMethodName() : testNameMTEST);
    extenttestMTEST.set(testMTEST);
    // Dynamically assign details for each testMTEST
    //authorMTEST = getAuthorFromAnnotationsMTEST(resultMTEST);  // Get author dynamically
    categoryMTEST = "Category Name: " + result.getTestClass().getName();  // Category from class name
    deviceMTEST = getDeviceInfoMTEST(result);
    // Assign the dynamic values
    //testMTEST.assignAuthor(authorMTEST);
    testMTEST.assignCategory(categoryMTEST);
    testMTEST.assignDevice(deviceMTEST);
    logAndClearReporterMessagesMTEST();

  }

  @Override
  public void onTestSuccess(ITestResult result) {
    logAndClearReporterMessagesMTEST();

  }

  @Override
  public void onTestFailure(ITestResult result) {
    // Mark the test as failed in the Extent Report
    extenttestMTEST.get().fail(result.getThrowable());

    // WebDriver instance retrieved successfully, take a screenshot
    if (driver != null) {
      try {
        // Automatically find the last active element
       // WebElement failedElement = getLastActiveElement();
        // Get the failed element (assuming it's stored in the result object)
      // WebElement failedElement = (WebElement) result.getAttribute("failedElement");

       // if (failedElement != null) {
         // System.out.println("Failed Element: " + failedElement);
          //Highlight the failed element
        //  highlightElement(failedElement);

          // Capture the screenshot
          String screenshotPath = LendingUtility.captureScreenshot(result.getMethod().getMethodName());
          if (screenshotPath != null) {
            System.out.println("Screenshot captured at: " + screenshotPath);

            // Attach screenshot to Extent Report
            extenttestMTEST.get().fail("Test Failed: Screenshot attached",
                  MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
          } else {
            System.out.println("Failed to capture screenshot.");
          }

           //Revert the highlight (optional)
          //unhighlightElement(failedElement);
       // }
      } catch (Exception e) {
        System.out.println("Exception while capturing screenshot: " + e.getMessage());
      }
    } else {
      System.out.println("Driver is null. Cannot take screenshot.");
    }
  }

  private WebElement getLastActiveElement() {
    try {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return (WebElement) js.executeScript("return document.activeElement;");
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  // Method to highlight an element
  private void highlightElement(WebElement element) {

    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true);arguments[0].setAttribute('style', 'border: 2px solid red; background: yellow');", element);
  }

  // Method to revert the highlight (optional)
  private void unhighlightElement(WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true);arguments[0].setAttribute('style', 'border: '');", element);
  }





  @Override
  public void onTestSkipped(ITestResult result) {
    ITestListener.super.onTestSkipped(result);
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
  }

  @Override
  public void onTestFailedWithTimeout(ITestResult result) {
    ITestListener.super.onTestFailedWithTimeout(result);
  }

  @Override
  public void onStart(ITestContext context) {
    //System.out.println("extent report checking " + extentReportsMTEST);
    if (extentReportsMTEST == null) {

      suiteNameMTEST = context.getSuite().getName(); // Get the test suite name
      timestampMTEST = new SimpleDateFormat("MMM-yyyy/dd-MM-yyyy HH.mm a").format(new Date()); // Format timestampMTEST
      reportFileNameMTEST = "test-output/extent-reports/" + suiteNameMTEST + "_" + timestampMTEST + ".html";

      htmlReporterMTEST = new ExtentSparkReporter(reportFileNameMTEST);
      htmlReporterMTEST.config().setTheme(Theme.STANDARD); // Use Theme.DARK for a dark theme
      htmlReporterMTEST.config().setDocumentTitle("Automation Test Report");
      htmlReporterMTEST.config().setReportName("Functional Test Report of: " + suiteNameMTEST);

      extentReportsMTEST = new ExtentReports();
      System.out.println("extent report checking " + extentReportsMTEST);
      extentReportsMTEST.attachReporter(htmlReporterMTEST);

      // Set system info
      extentReportsMTEST.setSystemInfo("OS", System.getProperty("os.name") + " " + System.getProperty("os.version"));
      extentReportsMTEST.setSystemInfo("Java Version", System.getProperty("java.version"));
      extentReportsMTEST.setSystemInfo("User Name", System.getProperty("user.name"));
      extentReportsMTEST.setSystemInfo("User Home", System.getProperty("user.home"));
      extentReportsMTEST.setSystemInfo("Java Vendor", System.getProperty("java.vendor"));
      extentReportsMTEST.setSystemInfo("Architecture", System.getProperty("os.arch"));
      extentReportsMTEST.setSystemInfo("Available Processors", String.valueOf(Runtime.getRuntime().availableProcessors()));
      extentReportsMTEST.setSystemInfo("Total Memory (MB)", String.valueOf(Runtime.getRuntime().totalMemory() / (1024 * 1024)));
      extentReportsMTEST.setSystemInfo("Free Memory (MB)", String.valueOf(Runtime.getRuntime().freeMemory() / (1024 * 1024)));
    }
  }

  // Sample method to get device info
  private String getDeviceInfoMTEST(ITestResult result) {

    if (driver instanceof RemoteWebDriver) {
      Capabilities browserCap = ((RemoteWebDriver) driver).getCapabilities();
      String browserName = browserCap.getBrowserName();
      String browserVersion = browserCap.getBrowserVersion();

      browserMTEST = "Browser Name: " + browserName + ", \n Browser Version: " + browserVersion;
      System.out.println(browserMTEST);
      return browserMTEST;
    } else {

      browserMTEST = "Unable to retrieve device info, driver is not RemoteWebDriver.";
      return browserMTEST;
    }


  }

  @Override
  public void onFinish(ITestContext context) {
    extentReportsMTEST.flush();

  }

  private void logAndClearReporterMessagesMTEST() {
    List<String> reporterLogsMTEST = Reporter.getOutput();
    for (String logMTEST : reporterLogsMTEST) {
      extenttestMTEST.get().info(logMTEST);
    }
    reporterLogsMTEST.clear(); // Clear the reporter logs after adding them to the Extent Report
  }
}
