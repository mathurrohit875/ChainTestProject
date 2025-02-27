package test.WeekendLoanTest;


//This test is to verify the loan detail which partner has entered on the admin end.
// This will check the pending stage loan number and then verify CSP Code, Spouse Name, Loan Amount
// Loan Tenure Days, Interest Rate, Processing Fee,  Insurance Fee, Penalty, Agreement Fee, Cheque Number
// Cheque Bank, Cheque of, Pan Number, Aadhar number, these all details will be verified from the DB.


import main.Base.DbMTEST;
import main.WeekendLoan.AddWeekendLoanPage;
import main.WeekendLoan.WeekendGuarantor;
import main.WeekendLoan.WeekendLoanResultPage;
import main.Base.BaseClassUAT2;
import main.Utility.ExcelUtil;
import main.Pages.HomePage;
import main.Pages.LoginPage;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class VerifyLoanDetailAdminAccountTest extends BaseClassUAT2 {

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
    String excelPath = "C:\\Users\\rohit.mathur\\IdeaProjects\\Lending\\src\\LoanLending\\Data\\LongTermData.xlsx";
    Browserintialize("chrome", "https://uatxpresso.roinet.in/Login.aspx");
    excelUtil = new ExcelUtil(excelPath);
    weekendLoanResultPage=new WeekendLoanResultPage();
    addWeekendLoanPage=new AddWeekendLoanPage();
    homePage = new HomePage();
    loginPage = new LoginPage();
    File file = new File("loanNumber.txt");


    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      // Read the loan number from the file
      loanNumber = reader.readLine(); // Assuming the loan number is on the first line
      System.out.println("Loan number read from file: " + loanNumber);
    } catch (IOException e) {
      e.printStackTrace();
    }
    softAssert = new SoftAssert();
    loginPage.login("MONA.SHARMA", "roinet@1234", "KMJKN");
    loginPage.Login_With_OTP("222111");
    homePage.ClickonWALLET();
    homePage.goToWeekendLoan("MONA.SHARMA");
    action = new Actions(driver);
    dbMTEST = new DbMTEST();
  }
  @Test(priority = 1,testName = "open loan detail on grid page")
  public void openLoan(){
    weekendLoanResultPage.enterLoanNumber(loanNumber);
    weekendLoanResultPage.selectLoanStatus("--All--");
    weekendLoanResultPage.clickViewButton();
    weekendLoanResultPage.btnViewDetails();
  }
  @Test(priority = 2,testName = "open loan detail on grid page")
  public void verifyDetails() throws SQLException {

    String loanDetails="Select * from tm_Channelemi where loanno='"+loanNumber+"'";
    ResultSet resultSet= dbMTEST.executeQuery(loanDetails);

    ResultSetMetaData resultSetMetaData= resultSet.getMetaData();
    int columnCount = resultSetMetaData.getColumnCount();
    String spouseName="";
    String loanamt="";
    String loaninterestrate="";
    String fileprocesschrg="";
    String insurancefee="";
    String chequenos="";
    String chequebank="";
    String chequeof="";
    String panno="";
    String aadharno="";
    while (resultSet.next()) {
      // Iterate through all columns dynamically
      for (int i = 1; i <= columnCount; i++) {
        // Get the column name
        String columnName = resultSetMetaData.getColumnName(i);
        // Get the value of the column as a String (you can get it in other types depending on the column)
        String columnValue = resultSet.getString(i);

        spouseName=resultSet.getString("spousename");
        loanamt=resultSet.getString("loanamt");
        loaninterestrate=resultSet.getString("loaninterestrate");
        fileprocesschrg=resultSet.getString("fileprocesschrg");
        insurancefee=resultSet.getString("insurancefee");
        chequenos=resultSet.getString("chequenos");
        chequebank=resultSet.getString("chequebank");
        chequeof=resultSet.getString("chequeof");
        panno=resultSet.getString("panno");
        aadharno=resultSet.getString("aadharno");
        System.out.println(columnName+":"+columnValue);

      }
    }
    Map<String, String> getValue=addWeekendLoanPage.getAllValues();
    System.out.println("spouseName: "+getValue.get("spouseName"));
    System.out.println("loanAmount: "+getValue.get("loanAmount"));
    System.out.println("loanTenure: "+getValue.get("loanTenure"));
    System.out.println("interestRate: "+getValue.get("interestRate"));
    System.out.println("processingFee: "+getValue.get("processingFee"));
    System.out.println("insuranceFee: "+getValue.get("insuranceFee"));
    System.out.println("penaltyAmount: "+getValue.get("penaltyAmount"));
    System.out.println("agreementFee: "+getValue.get("agreementFee"));
    System.out.println("chequeNos: "+getValue.get("chequeNos"));
    System.out.println("chequeBank: "+getValue.get("chequeBank"));
    System.out.println("ddlChequeOf: "+getValue.get("ddlChequeOf"));
    System.out.println("txtPANNo: "+getValue.get("txtPANNo"));
    System.out.println("txtAadharNo: "+getValue.get("txtAadharNo"));
    softAssert.assertEquals(spouseName,getValue.get("spouseName"),"Spouse name mismatch.");
    softAssert.assertEquals(loanamt,getValue.get("loanAmount"),"loan amt mismatch.");
    softAssert.assertEquals(loaninterestrate,getValue.get("interestRate"),"loan intrest mismatch");
    softAssert.assertEquals(fileprocesschrg,getValue.get("processingFee"),"processing charge mismatch.");
    softAssert.assertEquals(insurancefee,getValue.get("insuranceFee"),"insurance fee mismatch.");
    softAssert.assertEquals(chequenos,getValue.get("chequeNos"),"cheque number mismatch.");
    softAssert.assertEquals(chequebank,getValue.get("chequeBank"),"cheque bank mismatch.");
    softAssert.assertEquals(chequeof+"elf",getValue.get("ddlChequeOf"),"cheque of mismatch.");
    softAssert.assertEquals(panno,getValue.get("txtPANNo"),"pan number mismatch.");
    softAssert.assertEquals(aadharno,getValue.get("txtAadharNo"),"aadhar number mismatch.");
    softAssert.assertAll();


  }
}
