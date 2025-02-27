package main.Base;

import com.aventstack.chaintest.domain.Embed;
import com.aventstack.chaintest.domain.Test;
import com.aventstack.chaintest.service.ChainPluginService;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChainTestListener extends BaseClassUAT2 implements
      IExecutionListener, ISuiteListener, IClassListener, ITestListener, IInvokedMethodListener {

  private static final String TESTNG = "testng";
  private static final Map<String, Test> _suites = new ConcurrentHashMap<>();
  private static final Map<String, Test> _classes = new ConcurrentHashMap<>();
  private static final Map<String, Test> _methods = new ConcurrentHashMap<>();
  private static final ChainPluginService _service = new ChainPluginService(TESTNG);
  private static final ThreadLocal<Test> _currentTest = new ThreadLocal<>();
  private static final ThreadLocal<Queue<String>> _logs = ThreadLocal.withInitial(ConcurrentLinkedQueue::new);
  private static final ThreadLocal<Boolean> _allowLog = ThreadLocal.withInitial(() -> false);
  private static ThreadLocal<String> currentSuiteName = new ThreadLocal<>();


  public static void log(final String log) {
    if (null != _allowLog.get() && _allowLog.get()) {
      _logs.get().add(log);
    }
  }

  public static void embed(final byte[] data, final String mimeType) {
    if (null != _allowLog.get() && _allowLog.get() && _currentTest.get() != null) {
      _service.embed(_currentTest.get(), new Embed(data, mimeType));
    }
  }

  public static void embed(final File file, final String mimeType) {
    if (null != _allowLog.get() && _allowLog.get() && _currentTest.get() != null) {
      _service.embed(_currentTest.get(), new Embed(file, mimeType));
    }
  }

  public static void embed(final String base64, final String mimeType) {
    if (null != _allowLog.get() && _allowLog.get() && _currentTest.get() != null) {
      _service.embed(_currentTest.get(), new Embed(base64, mimeType));
    }
  }

  @Override
  public void onExecutionStart() {
    log("onExecutionStart");
    //_service.start();
  }

  @Override
  public void onExecutionFinish() {
    _service.executionFinished();
  }

  @Override
  public void onStart(final ISuite suite) {
    log("onStart");
// Store the suite name
    currentSuiteName.set(suite.getName());

    // Initialize suite in _suites map
    _suites.put(suite.getName(), new Test(suite.getName()));
    //_suites.put(suite.getName(), new Test(suite.getName()));
    // Get current timestamp in the format YYYYMMDD_HHmmss
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    System.out.println("timestamp: "+timestamp);
    LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM_dd_yyyy hh.mm a");
    String formattedDate = myDateObj.format(myFormatObj);
    System.out.println("After formatting: " + formattedDate);

    // Get the suite name from the thread-local storage
    String suiteName = currentSuiteName.get();  // This should now have the suite name
    System.out.println("check suitename: " + suiteName);

    // If suite name is not set, default to "DefaultSuite"
    if (suiteName == null) {
      suiteName = "DefaultSuite";
    }

    String docTitle=suite.getName();


    // Generate dynamic file paths with the suite name and timestamp
    String simpleReportPath = "C:/Users/rohit.mathur/IdeaProjects/ChainTestProject/chainReport/chaintest/"+ suiteName +"/Index/"+ formattedDate + ".html";
    String emailReportPath = "C:/Users/rohit.mathur/IdeaProjects/ChainTestProject/chainReport/chaintest/" + suiteName + "/Email/" + formattedDate + ".html";

    // Update the property for ChainTest generators
    System.setProperty("chaintest.generator.simple.output-file", simpleReportPath);
    System.setProperty("chaintest.generator.email.output-file", emailReportPath);
    System.setProperty("chaintest.generator.simple.document-title",docTitle);
    System.setProperty("chaintest.generator.email.document-title",docTitle);
    _service.start();
  }

  @Override
  public void onFinish(final ISuite suite) {
    log("on finins");
    final Test suiteTest = _suites.get(suite.getName());
    suiteTest.complete();
    _service.afterTest(suiteTest, Optional.empty());
    _service.flush();
  }

  @Override
  public void onBeforeClass(final ITestClass testClass) {
    log("on before class");
    final Test testClazz = new Test(testClass.getName());
    _classes.put(testClass.getName(), testClazz);
    _suites.get(testClass.getXmlTest().getSuite().getName()).addChild(testClazz);
  }

  @Override
  public void onAfterClass(final ITestClass testClass) {
    log("on after class");
    _classes.get(testClass.getName()).complete();
  }

  @Override
  public void beforeInvocation(final IInvokedMethod method, final ITestResult result) {
    if (method.isConfigurationMethod() && method.getTestMethod().isBeforeMethodConfiguration() || method.isTestMethod()) {
      _allowLog.set(true);
    }
    if (method.isTestMethod()) {
      final Test testMethod = new Test(result.getMethod().getMethodName(),
            Optional.of(result.getTestClass().getName()),
            List.of(result.getMethod().getGroups()));
      testMethod.setDescription(method.getTestMethod().getDescription());
      testMethod.setExternalId(result.getMethod().getQualifiedName() + "_" + result.id());
      while (!_logs.get().isEmpty()) {
        testMethod.addLog(_logs.get().poll());
      }
      _classes.get(result.getTestClass().getName()).addChild(testMethod);
      _methods.put(result.getMethod().getQualifiedName(), testMethod);
      _currentTest.set(testMethod);

      if (null != result.getParameters() && result.getParameters().length > 0) {
        final String params = String.join(", ", Arrays.stream(result.getParameters())
              .filter(p -> null != p && !(p instanceof Method) && !(p instanceof ITestContext) && !(p instanceof ITestResult))
              .map(Object::toString)
              .toArray(String[]::new));
        if (!params.isEmpty()) {
          final String desc = null == testMethod.getDescription() || testMethod.getDescription().isBlank()
                ? "" : testMethod.getDescription() + "<br>";
          testMethod.setDescription(desc + "[" + params + "]");
        }
      }
    }
  }

  @Override
  public void afterInvocation(final IInvokedMethod method, final ITestResult result, ITestContext ctx) {
    if (method.isConfigurationMethod() && method.getTestMethod().isAfterMethodConfiguration()) {
      final Test testMethod = _currentTest.get();
      if (null != testMethod) {
        while (!_logs.get().isEmpty()) {
          testMethod.addLog(_logs.get().poll());
        }
      }
      _allowLog.set(false);
    }
  }

  @Override
  public void onStart(final ITestContext context) {
    log("on start");
  }

  @Override
  public void onFinish(final ITestContext context) {
    log("on Finish");
  }

  @Override
  public void onTestStart(final ITestResult result) {
    log("test is started");
  }

  @Override
  public void onTestSuccess(final ITestResult result) {
    log("on Test success");
    onTestComplete(result);
  }

  private void onTestComplete(final ITestResult result) {
    log("test is completed.");

    if (_methods.containsKey(result.getMethod().getQualifiedName())) {
      final Test completingMethod = _currentTest.get();
      completingMethod.complete(result.getThrowable());
      while (!_logs.get().isEmpty()) {
        completingMethod.addLog(_logs.get().poll());
      }
    }
  }

  @Override
  public void onTestFailure(final ITestResult result) {
    log("failed.......... " + result.getMethod().getMethodName());
    // Take screenshot
    File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
      byte[] bytes = Files.toByteArray(screenshotFile);
      embed(bytes, "image/png");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    onTestComplete(result);
  }

  @Override
  public void onTestSkipped(final ITestResult result) {
    onTestComplete(result);
  }

  @Override
  public void onTestFailedWithTimeout(final ITestResult result) {
    onTestFailure(result);
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
  }
}