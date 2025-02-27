package main.WeekendLoan;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class CustomSoftAssert extends SoftAssert {

  private Map<String, WebElement> labelElements;

  public CustomSoftAssert(Map<String, WebElement> labelElements) {
    this.labelElements = labelElements;
  }

  @Override
  public void assertEquals(String actual, String expected, String message) {
    super.assertEquals(actual, expected, message);
    if (!actual.equals(expected)) {
      Reporter.getCurrentTestResult().setAttribute("failedLabel", actual);
    }
  }
}

