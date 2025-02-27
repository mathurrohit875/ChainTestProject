package test.WeekendLoanTest;

import main.Base.DbMTEST;
import main.Base.LendingUtility;
import main.WeekendLoan.AddWeekendLoanPage;
import main.WeekendLoan.WeekendGuarantor;
import main.WeekendLoan.WeekendLoanResultPage;
import main.Base.BaseClassUAT2;
import main.Utility.ExcelUtil;
import main.Pages.HomePage;
import main.Pages.LoginPage;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ApproveWeekendLoanAdminTest extends BaseClassUAT2 {

  HomePage homePage;
  LoginPage loginPage;

  SoftAssert softAssert;
  Actions action;
  ExcelUtil excelUtil;
  DbMTEST dbMTEST;
  WeekendLoanResultPage weekendLoanResultPage;
  AddWeekendLoanPage addWeekendLoanPage;
  String loanNumber="";

  Map<String, String> loan = new HashMap<>();

  @BeforeClass
  public void setup() throws IOException {
    String excelPath = "C:\\Users\\rohit.mathur\\IdeaProjects\\ChainTestProject\\src\\main\\data\\LendingData.xlsx";
    Browserintialize("chrome", "https://uatxpresso.roinet.in/Login.aspx");
    excelUtil = new ExcelUtil(excelPath);
    weekendLoanResultPage = new WeekendLoanResultPage();
    addWeekendLoanPage = new AddWeekendLoanPage();
    homePage = new HomePage();
    loginPage = new LoginPage();

    softAssert = new SoftAssert();
    loginPage.login("MONA.SHARMA", "roinet@1234", "KMJKN");
    loginPage.Login_With_OTP("222111");
    homePage.ClickonWALLET();
    homePage.goToWeekendLoan("MONA.SHARMA");
    action = new Actions(driver);
    dbMTEST = new DbMTEST();
  }

  @Test(priority = 1, testName = "open loan detail on grid page")
  public void openLoan() throws SQLException {
    File file = new File("loanNumber.txt");


    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      // Read the loan number from the file
      loanNumber = reader.readLine(); // Assuming the loan number is on the first line
      System.out.println("Loan number read from file: " + loanNumber);
    } catch (IOException e) {
      e.printStackTrace();
    }
    String updateLoanStatus="update tm_channelemi set loanstatus=3, isemandate=1 where loanno='"+loanNumber+"'";
    String updateGuarantorStatus="update tm_channelemi set guarantoragreementstatus=3, isguarantoremandate=1 where loanno='"+loanNumber+"'";
    dbMTEST.executeQuery(updateLoanStatus);
    dbMTEST.executeQuery(updateGuarantorStatus);
    weekendLoanResultPage.enterLoanNumber(loanNumber);
    weekendLoanResultPage.selectLoanStatus("--All--");
    weekendLoanResultPage.clickViewButton();
    weekendLoanResultPage.btnViewDetails();

    addWeekendLoanPage.changeLoanStatus("LoanApproved");
    addWeekendLoanPage.addApprovalRemark("loan approved");
    addWeekendLoanPage.clickSaveButton();
    String alertText = LendingUtility.getAlertBoxText();
    softAssert.assertEquals(alertText.equalsIgnoreCase("Your Wallet Advance request updated successfully."),true,"Wallet Advance not approved because: "+alertText);
    softAssert.assertAll();
    acceptAlert();
  }

 /* @AfterClass
  public void quit(){
    driver.quit();
  }*/
}
