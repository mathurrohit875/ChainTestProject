package test.Check;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

@Listeners(ChainTestListener.class)
public class TestClass {


  WebDriver driver;

  @Test
  public void sum() {

    Assert.assertTrue(true);
  }

  @Test
  public void testMethod(final Method method) throws IOException {

    driver = new ChromeDriver();
    driver.get("https://www.google.com");
    //log
    ChainTestListener.log("log chrom");
    // embed

    String a="ab";
    String b="bc";
    if(a.equalsIgnoreCase(b)){
      Assert.assertTrue(true);

    }
    else{
      ChainTestListener.log("test fail");

      // Take screenshot
      File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      byte[] bytes = Files.toByteArray(screenshotFile);
      ChainTestListener.embed(bytes, "image/png");
      Assert.fail();
    }

  }

  /*@BeforeMethod
  public void beforeMethod(final ITestResult result) throws IOException {
    driver = new ChromeDriver();
    //log
    ChainTestListener.log("log example");

    // embed
    // Take screenshot
    File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    byte[] bytes = Files.toByteArray(screenshotFile);
    ChainTestListener.embed(bytes, "image/png");
  }

  @AfterMethod
  public void afterMethod(final ITestResult result) throws IOException {
    //log
    ChainTestListener.log("log example");

    // embed
    // Take screenshot
    File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    byte[] bytes = Files.toByteArray(screenshotFile);
    ChainTestListener.embed(bytes, "image/png");
  }*/

  @AfterTest
  public void quit()
  {
    driver.quit();
  }
}
