package test.WeekendLoanTest;


import main.Base.DbMTEST;
import main.WeekendLoan.AddWeekendLoanPage;
import main.WeekendLoan.WeekendGuarantor;
import main.WeekendLoan.WeekendLoanResultPage;
import main.Base.BaseClassUAT2;
import main.Utility.ExcelUtil;
import main.Pages.HomePage;
import main.Pages.LoginPage;
//import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import main.Base.ChainTestListener;

public class ApplyWeekendLoanWithGuarantorTest extends BaseClassUAT2 {

  HomePage homePage;
  LoginPage loginPage;

  SoftAssert softAssert;
  Actions action;
  ExcelUtil excelUtil;
  DbMTEST dbMTEST;
  WeekendLoanResultPage weekendLoanResultPage;
  AddWeekendLoanPage addWeekendLoanPage;
  WeekendGuarantor weekendGuarantor;
  Map<String, String> loan = new HashMap<>();

  @BeforeClass
  public void setup() throws IOException, SQLException {
    String excelPath = "C:\\Users\\rohit.mathur\\IdeaProjects\\ChainTestProject\\src\\main\\data\\LendingData.xlsx";
    Browserintialize("chrome", "https://uatxpresso.roinet.in/Login.aspx");
    ChainTestListener.log("log chrom");
    excelUtil = new ExcelUtil(excelPath);
    homePage = new HomePage();
    loginPage = new LoginPage();
    weekendLoanResultPage = new WeekendLoanResultPage();
    addWeekendLoanPage = new AddWeekendLoanPage();
    weekendGuarantor = new WeekendGuarantor();
    softAssert = new SoftAssert();
    dbMTEST = new DbMTEST();
    String cspUser = excelUtil.getCellData("WeekendLoan", 16, 1).trim();
    String getChannelId = "select userid from tm_user where usercode='" + cspUser + "'";

    ResultSet rs = dbMTEST.executeQuery(getChannelId);
    String channelId = "";
    while (rs.next()) {
      channelId = rs.getString("userid");
    }
    String updateWalletBalance = "update tm_channel set availablelimit=100.00 where channelid=" + channelId;
    dbMTEST.executeQuery(updateWalletBalance);
    loginPage.login(cspUser, "roinet@1234", "KMJKN");
    loginPage.Login_With_OTP("222111");
    homePage.ClickonWALLET();
    homePage.goToWeekendLoan(cspUser);
    action = new Actions(driver);

  }

  @Test(priority = 1, testName = "click add loan to navigate to loan page",groups = {"regression"})
  public void goToLoanPage() throws IOException {
    ChainTestListener.log("log chrom");
    weekendLoanResultPage.clickAddButton();
  }

  @Test(priority = 2, testName = "apply for loan with guarantor.",groups = {"integration"})
  public void applyLoan() throws Exception {
    ChainTestListener.log("log chrom");
    String spouse = excelUtil.getCellData("WeekendLoan", 1, 1);
    String loanAmount = excelUtil.getCellData("WeekendLoan", 2, 1);
    String gstStmt = excelUtil.getCellData("WeekendLoan", 3, 1);
    String bankStmt = excelUtil.getCellData("WeekendLoan", 4, 1);
    String otherDoc = excelUtil.getCellData("WeekendLoan", 5, 1);
    String chequeScan = excelUtil.getCellData("WeekendLoan", 6, 1);
    String chequeNumber = excelUtil.getCellData("WeekendLoan", 7, 1);
    String chequeBank = excelUtil.getCellData("WeekendLoan", 8, 1);
    String chequeOf = excelUtil.getCellData("WeekendLoan", 9, 1);
    String apprEmail = excelUtil.getCellData("WeekendLoan", 10, 1);
    String panNumber = excelUtil.getCellData("WeekendLoan", 11, 1);
    String panDoc = excelUtil.getCellData("WeekendLoan", 12, 1);
    String aadharNumber = excelUtil.getCellData("WeekendLoan", 13, 1);
    String aadharDoc = excelUtil.getCellData("WeekendLoan", 14, 1);
    String cibilDoc = excelUtil.getCellData("WeekendLoan", 15, 1);
    String walletExposureAmt=excelUtil.getCellData("WeekendLoan",23,1);
    String resStability=excelUtil.getCellData("WeekendLoan",24,1);
    addWeekendLoanPage.fillLoanDetails(spouse, loanAmount, gstStmt, bankStmt, otherDoc,
          chequeScan, chequeNumber, chequeBank,walletExposureAmt,resStability, chequeOf, apprEmail, panNumber, panDoc, aadharNumber,
          aadharDoc, cibilDoc);
    addWeekendLoanPage.clickSaveButton();
    String loanNumber = addWeekendLoanPage.getLoanNumber();
    File file = new File("loanNumber.txt");
    try {
      // Check if the file exists
      if (!file.exists()) {
        // If the file doesn't exist, create it
        boolean fileCreated = file.createNewFile();
        if (fileCreated) {
          ChainTestListener.log("log chrom");
          System.out.println("File created successfully.");
        } else {
          ChainTestListener.log("log chrom");
          System.out.println("File already exists or cannot be created.");
        }
      }
      try (FileWriter writer = new FileWriter(file)) {
        ChainTestListener.log("log chrom");
        // Write the loan number to the file
        writer.write(loanNumber);
        System.out.println("Loan number saved to file.");
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
      }

    System.out.println("loanNumber: " + loanNumber);
    weekendLoanResultPage.enterLoanNumber(loanNumber);
    weekendLoanResultPage.selectLoanStatus("--All--");
    weekendLoanResultPage.clickViewButton();
    weekendLoanResultPage.clickGuarantorDetailButton();
    String weekendResult = driver.getWindowHandle();
    Set<String> windowSet = driver.getWindowHandles();
    Iterator<String> i = windowSet.iterator();
    while (i.hasNext()) {
      String guarantorWindow = i.next();
      if (!weekendResult.equals(guarantorWindow)) {
        driver.switchTo().window(guarantorWindow);

        weekendGuarantor.enterGuarantorDetail("DELHI & NCR", "GURGAON", "Rohit Mathur", "8290336521", "rohit.mathur@roinet.in",
              "Salaried", "friend", "3", "ABCDE TOWER 10, FLAT 903, NEAR HUDA MARKET, TWIN TOWER", "123456"
              , "536350660843", "BXRPM9931K", panDoc, aadharDoc, bankStmt, "no","22/07/1993","Male");
       weekendGuarantor.clickSaveButton();
      }

    }
    driver.switchTo().window(weekendResult);
  }
  /*@AfterClass
  public void quit(){
    driver.quit();
  }*/
}
