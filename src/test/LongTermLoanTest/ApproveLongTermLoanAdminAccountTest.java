package test.LongTermLoanTest;

import main.Base.BaseClassUAT2;
import main.Base.DbMTEST;
import main.LongTermLoan.AddLongTermLoan;
import main.LongTermLoan.LongTermResultPage;
import main.Pages.HomePage;
import main.Pages.LoginPage;
import main.Utility.ExcelUtil;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ApproveLongTermLoanAdminAccountTest extends BaseClassUAT2 {

  HomePage homePage;
  LoginPage loginPage;
  AddLongTermLoan addLongTermLoan;
  SoftAssert softAssert;
  Actions action;
  ExcelUtil excelUtil;
  DbMTEST dbMTEST;
  LongTermResultPage longTermResultPage;
  Map<String, String> loan = new HashMap<>();
  @BeforeClass
  public void setup() throws IOException {
    String excelPath = "C:\\Users\\rohit.mathur\\IdeaProjects\\Lending\\src\\LoanLending\\Data\\LongTermData.xlsx";
    Browserintialize("chrome", "https://uatxpresso.roinet.in/Login.aspx");
    excelUtil = new ExcelUtil(excelPath);
    longTermResultPage = new LongTermResultPage();
    homePage = new HomePage();
    loginPage = new LoginPage();
    addLongTermLoan = new AddLongTermLoan();
    softAssert = new SoftAssert();
    loginPage.login("MONA.SHARMA", "roinet@1234", "KMJKN");
    loginPage.Login_With_OTP("222111");
    homePage.ClickonWALLET();
    homePage.goToLongTermLoan();
    action = new Actions(driver);
    dbMTEST = new DbMTEST();
  }

  @Test(priority = 1, testName = "open loan")
  public void openLoan() throws IOException {
    String loanno=excelUtil.getCellData("LoanDetail",33,1);
    longTermResultPage.enterLoanNumber(loanno);
    longTermResultPage.selectLoanStatus("Pending");
    longTermResultPage.clickView();
    longTermResultPage.btnViewDetails();
  }

  @Test(priority = 2,testName = "change status to EAgreementSignedDue")
  public void statusToEAgreementDue() throws SQLException, IOException {
    String loanno=excelUtil.getCellData("LoanDetail",33,1);
    addLongTermLoan.ddlLoanStatus("EAgreementSignedDue");
    addLongTermLoan.btnGoAhead();
    String updateLoanStatus="update tm_Channelemi set loanstatus=3, isemandate=1 where loanno='"+loanno+"'";
    dbMTEST.executeQuery(updateLoanStatus);
    String checkLoanStatus="select loanstatus,isemandate from tm_Channelemi where loanno='"+loanno+"'";

    ResultSet rs=dbMTEST.executeQuery(checkLoanStatus);
    while(rs.next()){
      String loanstatus=rs.getString("loanstatus");
      String isemandate=rs.getString("isemandate");
      System.out.println("result is updated."+loanstatus+" "+isemandate);
    }
    longTermResultPage.enterLoanNumber(loanno);
    longTermResultPage.selectLoanStatus("EAgreementSignedByPartner");
    longTermResultPage.clickView();
    longTermResultPage.btnViewDetails();
    addLongTermLoan.ddlLoanStatus("LoanApproved");
    addLongTermLoan.addRemarks("Approved");
    addLongTermLoan.btnGoAhead();


  }
}
