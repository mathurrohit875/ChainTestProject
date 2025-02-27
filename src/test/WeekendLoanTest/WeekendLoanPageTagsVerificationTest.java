package test.WeekendLoanTest;

import main.Base.DbMTEST;
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

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


//This test will be performed to check the tags and column heading on the result grid page.

public class WeekendLoanPageTagsVerificationTest extends BaseClassUAT2 {

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
  String loanNumber = "";

  @BeforeClass
  public void setup() throws IOException, SQLException {
    String excelPath = "C:\\Users\\rohit.mathur\\IdeaProjects\\Lending\\src\\LoanLending\\Data\\LongTermData.xlsx";
    Browserintialize("chrome", "https://uatxpresso.roinet.in/Login.aspx");

    excelUtil = new ExcelUtil(excelPath);
    homePage = new HomePage();
    loginPage = new LoginPage();
    weekendLoanResultPage = new WeekendLoanResultPage();
    addWeekendLoanPage = new AddWeekendLoanPage();
    softAssert = new SoftAssert();
    dbMTEST = new DbMTEST();

    weekendGuarantor = new WeekendGuarantor();
    String adminLending = excelUtil.getCellData("WeekendLoan", 17, 1).trim();
    loginPage.login(adminLending, "roinet@1234", "KMJKN");
    loginPage.Login_With_OTP("222111");
    homePage.ClickonWALLET();
    homePage.goToWeekendLoan(adminLending);
    action = new Actions(driver);


  }

  @Test(priority = 1)
  public void verifyTagsOnWeekendPage() {
    weekendLoanResultPage.selectLoanStatus("Pending");
    weekendLoanResultPage.clickViewButton();
    weekendLoanResultPage.btnViewDetails();
    Map<String, String> labelName = addWeekendLoanPage.getLabelNames();


    softAssert.assertEquals(labelName.get("User Type").trim(), "User Type");
    softAssert.assertEquals(labelName.get("User").trim(), "User");
    softAssert.assertEquals(labelName.get("Spouse Name(If Married)").trim(), "Spouse Name(If Married)");
    softAssert.assertEquals(labelName.get("PAN").trim(), "PAN");
    softAssert.assertEquals(labelName.get("AADHAR").trim(), "AADHAR");
    softAssert.assertEquals(labelName.get("GST No").trim(), "GST No");
    softAssert.assertEquals(labelName.get("Wallet Advance Type").trim(), "Wallet Advance Type");
    softAssert.assertEquals(labelName.get("Status").trim(), "Status");
    softAssert.assertEquals(labelName.get("Current Status").trim(), "Current Status");
    softAssert.assertEquals(labelName.get("Rejected Reason").trim(), "Rejected Reason");
    softAssert.assertEquals(labelName.get("GST Statement").trim(), "GST Statement");
    softAssert.assertEquals(labelName.get("Bank Statement").trim(), "Bank Statement");
    softAssert.assertEquals(labelName.get("Other Docs(ITR/26AS etc.)").trim(), "Other Docs(ITR/26AS etc.)");
    softAssert.assertEquals(labelName.get("Cheque Scan Copies").trim(), "Cheque Scan Copies");
    softAssert.assertEquals(labelName.get("Cheque Nos").trim(), "Cheque Nos");
    softAssert.assertEquals(labelName.get("Cheque Bank").trim(), "Cheque Bank");
    softAssert.assertEquals(labelName.get("Cheque Of").trim(), "Cheque Of");
    softAssert.assertEquals(labelName.get("Approved Email").trim(), "Approved Email");
    softAssert.assertEquals(labelName.get("PAN No.").trim(), "PAN No.");
    softAssert.assertEquals(labelName.get("PAN Doc").trim(), "PAN Doc");
    softAssert.assertEquals(labelName.get("Aadhar No.").trim(), "Aadhar No.");
    softAssert.assertEquals(labelName.get("Aadhar Doc").trim(), "Aadhar Doc");
    softAssert.assertEquals(labelName.get("CIBIL").trim(), "CIBIL");
    softAssert.assertEquals(labelName.get("Check For Guarantor").trim(), "Check For Guarantor");
    softAssert.assertEquals(labelName.get("Guarantor Status *").trim(), "Guarantor Status *");
    softAssert.assertEquals(labelName.get("Approved Remarks *").trim(), "Approved Remarks *");
    softAssert.assertEquals(labelName.get("Email Document *").trim(), "Email Document *");
    softAssert.assertEquals(labelName.get("AddendumAmount *").trim(), "AddendumAmount *");
    softAssert.assertEquals(labelName.get("Addendum Number").trim(), "Addendum Number");
    softAssert.assertEquals(labelName.get("Cheque Scan Copies").trim(), "Cheque Scan Copies");
    softAssert.assertEquals(labelName.get("Cheque Number").trim(), "Cheque Number");
    softAssert.assertEquals(labelName.get("Cheque Bank").trim(), "Cheque Bank");
    softAssert.assertEquals(labelName.get("Cheque Of").trim(), "Cheque Of");
    softAssert.assertEquals(labelName.get("Addendum Status *").trim(), "Addendum Status ");
    softAssert.assertAll();
  }

  @AfterClass
  public void quit(){
    driver.quit();
  }
}