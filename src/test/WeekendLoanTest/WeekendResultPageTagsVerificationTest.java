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

public class WeekendResultPageTagsVerificationTest extends BaseClassUAT2 {

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
  public void verifyTagsOnWeekendResultPage() {
    String title = weekendLoanResultPage.getTitle();
    //Page Heading
    softAssert.assertEquals(title.trim(), "Weekend Wallet Advance");

    weekendLoanResultPage.selectLoanStatus("--All--");
    weekendLoanResultPage.clickViewButton();
    Map<String, String> loanStatus = weekendLoanResultPage.Loanstatus();
    Map<String, String> addendumStatus = weekendLoanResultPage.Addendumstatus();
    Map<String, String> labelName = weekendLoanResultPage.getLabelNames();
    Map<String, String> columnName = weekendLoanResultPage.getTableColumn();


    //Loan Status
    softAssert.assertEquals(loanStatus.get("--All--").trim(), "--All--");
    softAssert.assertEquals(loanStatus.get("Pending").trim(), "Pending");
    softAssert.assertEquals(loanStatus.get("EAgreementSignedDue").trim(), "EAgreementSignedDue");
    softAssert.assertEquals(loanStatus.get("EAgreementSignedByPartner").trim(), "EAgreementSignedByPartner");
    softAssert.assertEquals(loanStatus.get("LoanApproved").trim(), "LoanApproved");
    softAssert.assertEquals(loanStatus.get("LoanRunning").trim(), "LoanRunning");
    softAssert.assertEquals(loanStatus.get("EMIPaymentCompleted").trim(), "EMIPaymentCompleted");
    softAssert.assertEquals(loanStatus.get("LoanRejected").trim(), "LoanRejected");
    softAssert.assertEquals(loanStatus.get("ForeClosure").trim(), "ForeClosure");
    softAssert.assertEquals(loanStatus.get("DefaultClosure").trim(), "DefaultClosure");
    softAssert.assertEquals(loanStatus.get("EMIPaymentCompletedWithNegativeBal").trim(), "EMIPaymentCompletedWithNegativeBal");
    softAssert.assertEquals(loanStatus.get("ForceCloseWithNegativeBal").trim(), "ForceCloseWithNegativeBal");

    //Addendum Status
    softAssert.assertEquals(addendumStatus.get("--All--").trim(),"--All--");
    softAssert.assertEquals(addendumStatus.get("Pending").trim(),"Pending");
    softAssert.assertEquals(addendumStatus.get("EAgreementSignedDue").trim(),"EAgreementSignedDue");
    softAssert.assertEquals(addendumStatus.get("EAgreementSignedByPartner").trim(),"EAgreementSignedByPartner");
    softAssert.assertEquals(addendumStatus.get("Wallet AdvanceApproved").trim(),"Wallet AdvanceApproved");



    //Tag Name
    softAssert.assertEquals(labelName.get("User Code").trim(), "User Code");
    softAssert.assertEquals(labelName.get("Wallet Advance Number").trim(), "Wallet Advance Number");
    softAssert.assertEquals(labelName.get("From Date").trim(), "From Date");
    softAssert.assertEquals(labelName.get("To Date").trim(), "To Date");
    softAssert.assertEquals(labelName.get("Status").trim(), "Status");
    softAssert.assertEquals(labelName.get("Addendum Status").trim(), "Addendum Status");

    //Table heading name
    softAssert.assertEquals(columnName.get("User Code").trim(), "User Code", "usercode does not match");
    softAssert.assertEquals(columnName.get("User Name").trim(), "User Name", "username does not match");
    softAssert.assertEquals(columnName.get("Wallet Advance No.").trim(), "Wallet Advance No.", "wallet advance no. does not match");
    softAssert.assertEquals(columnName.get("Wallet Advance Amount").trim(), "Wallet Advance Amount");
    softAssert.assertEquals(columnName.get("Wallet Advance Status").trim(), "Wallet Advance Status");
    softAssert.assertEquals(columnName.get("Addendum Status").trim(), "Addendum Status");
    softAssert.assertEquals(columnName.get("Approved By").trim(), "Approved By");
    softAssert.assertEquals(columnName.get("Approved Remarks").trim(), "Approved Remarks");
    softAssert.assertEquals(columnName.get("Approved Date").trim(), "Approved Date");
    softAssert.assertEquals(columnName.get("PAN").trim(), "PAN");
    softAssert.assertEquals(columnName.get("AADHAR").trim(), "AADHAR");
    softAssert.assertEquals(columnName.get("CIBIL").trim(), "CIBIL");
    softAssert.assertEquals(columnName.get("Email Docs").trim(), "Email Docs");
    softAssert.assertEquals(columnName.get("Cheque Scan Copies").trim(), "Cheque Scan Copies");
    softAssert.assertEquals(columnName.get("Addendum Cheque Scan Copies").trim(), "Addendum Cheque Scan Copies");
    softAssert.assertEquals(columnName.get("E-Mandate").trim(), "E-Mandate");
    softAssert.assertEquals(columnName.get("E-Sign").trim(), "E-Sign");
    softAssert.assertEquals(columnName.get("View Details").trim(), "View Details");
    softAssert.assertEquals(columnName.get("Take Limit").trim(), "Take Limit");
    softAssert.assertEquals(columnName.get("Review Report").trim(), "Review Report");
    softAssert.assertEquals(columnName.get("Email Document PDF").trim(), "Email Document PDF");
    softAssert.assertEquals(columnName.get("New Agreement").trim(), "New Agreement");
    softAssert.assertEquals(columnName.get("Guarantor E-Sign").trim(), "Guarantor E-Sign");
    softAssert.assertEquals(columnName.get("Guarantor E-Mandate").trim(), "Guarantor E-Mandate");
    softAssert.assertEquals(columnName.get("Guarantor Detail").trim(), "Guarantor Detail");
    softAssert.assertEquals(columnName.get("E-NACH").trim(), "E-NAC");

    softAssert.assertAll();
  }

  @AfterClass
  public void quit(){
    driver.quit();
  }
}
