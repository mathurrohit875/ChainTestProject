package main.Base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.TestException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.TimeZone;

public class LendingUtility extends BaseClassUAT2 {






  public void navigateToURL(String URL) {
    System.out.println("Navigating to: " + URL);
    System.out.println("Thread id = " + Thread.currentThread().getId());

    try {
      driver.navigate().to(URL);
    } catch (Exception e) {
      System.out.println("URL did not load: " + URL);
      throw new TestException("URL did not load");
    }
  }
  public WebElement getElement(By selector) {
    try {
      return driver.findElement(selector);
    } catch (Exception e) {
      System.out.printf("Element %s does not exist - proceeding%n", selector);
    }
    return null;
  }
  public String randomMobileNumber(){
    int max = 99999999;
    int min = 10000000;
    int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
    return "6" + randomWithMathRandom + "6";
  }

  public void waitForElement(By ele){
    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(120));
    wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
  }

  public static String getElementValue(WebDriver driver, By locator) {
    try {
      return driver.findElement(locator).getAttribute("value").trim();
    } catch (Exception e) {
      Reporter.log("Error retrieving element value: " + e.getMessage());
      return "";
    }
  }

  public static String getElementText(WebDriver driver, By locator) {
    try {
      return driver.findElement(locator).getText().trim();
    } catch (Exception e) {
      Reporter.log("Error retrieving element text: " + e.getMessage());
      return "";
    }
  }
  // Static method to capture a screenshot
  public static String captureScreenshot(String methodName) {
    try {
      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      String humanReadableTimestamp = getHumanReadableTimestamp();
      String fileName = "screenshots/" + methodName + "_" + humanReadableTimestamp + ".png";
      File destinationFile = new File(fileName);
      FileUtils.copyFile(screenshot, destinationFile);
      return destinationFile.getAbsolutePath();
    } catch (IOException e) {
      e.fillInStackTrace();
      return null;
    }
  }
  private static String getHumanReadableTimestamp() {
    long timestampMillis = System.currentTimeMillis();
    Date date = new Date(timestampMillis);
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH.mm a");
    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // Set timezone to UTC for consistency
    return sdf.format(date);
  }
  public void waitForPageLoad(int i){
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(i));
  }

  public static String getAlertBoxText(){
    System.out.println("alert box text:" +driver.switchTo().alert().getText().trim());
    return driver.switchTo().alert().getText().trim();
  }
}
