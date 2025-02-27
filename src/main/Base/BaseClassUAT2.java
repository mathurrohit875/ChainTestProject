package main.Base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class BaseClassUAT2 {

  private static final String CONFIG_FILE_PATH = "C:/Users/rohit.mathur/IdeaProjects/Lending/config.properties";
  /*private static String browserNameUAT;
  private static String browserVersionUAT;*/
  //public static WebDriver driverUAT;
  //protected CIPOM cipom;
  //protected CIMandatoryMsgPOM ciMandatoryMsgPOM;
 // protected Properties propertiesUAT;
  //TestUtility testUtility=new TestUtility();
  public static Properties prop;
  public static WebDriver driver;
  WebDriverWait wait;


  public BaseClassUAT2()  {
    try {
      prop = new Properties();
      FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
      try {
        prop.load(fis);
      } catch (IOException e) {

        System.out.println(" "+e.getMessage());
      }
    } catch (FileNotFoundException e) {

      System.out.println(" "+e.getMessage());
    }

  }



  public static void Browserintialize(String browsername, String url) {

    if (browsername.equalsIgnoreCase("chrome")) {

      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.addArguments("--ignore-certificate-errors");
      driver = new ChromeDriver(chromeOptions);

    } else if (browsername.equalsIgnoreCase("firefox")) {

      driver = new FirefoxDriver();
    }

    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get(url);
  }

  public void acceptAlert(){
    wait=new WebDriverWait(driver,Duration.ofSeconds(60));
    wait.until(ExpectedConditions.alertIsPresent());
    driver.switchTo().alert().accept();
  }
 /* public String getProperty(String key) {
    return prop.getProperty(key);
  }


  // Getter for browser name
  public static String getBrowserNameUAT() {
    System.out.println("browser for UAT in base class: " + browserNameUAT);
    return browserNameUAT;
  }

  public static String getBrowserVersionUAT() {
    System.out.println("browser version for UAT in base class: " + browserVersionUAT);
    return browserVersionUAT;
  }


  public WebDriver getDriverUAT() {
    return driverUAT;
  }

*/
}
/* @BeforeSuite
  public void setOutputDirectory() {
    // Generate a timestamp-based directory name
    String timestamp = getHumanReadableTimestamp();
    System.setProperty("outputDirectory", "test-output-" + timestamp);
  }*/
 /* private String getHumanReadableTimestamp() {
    long timestampMillis = System.currentTimeMillis();
    Date date = new Date(timestampMillis);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS");
    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
    return sdf.format(date);
  }*/