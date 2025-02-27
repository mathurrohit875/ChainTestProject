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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ApplyWeekendLoanTest extends BaseClassUAT2 {
  HomePage homePage;
  LoginPage loginPage;

  SoftAssert softAssert;
  Actions action;
  ExcelUtil excelUtil;
  DbMTEST dbMTEST;
  WeekendLoanResultPage weekendLoanResultPage;
  AddWeekendLoanPage addWeekendLoanPage;
  Map<String, String> loan = new HashMap<>();
  @BeforeClass
  public void setup() throws IOException, SQLException {
    String excelPath = "C:\\Users\\rohit.mathur\\IdeaProjects\\Lending\\src\\LoanLending\\Data\\LongTermData.xlsx";
    Browserintialize("chrome", "https://uatxpresso.roinet.in/Login.aspx");

    excelUtil = new ExcelUtil(excelPath);
    homePage = new HomePage();
    loginPage = new LoginPage();
    weekendLoanResultPage=new WeekendLoanResultPage();
    addWeekendLoanPage=new AddWeekendLoanPage();
    softAssert = new SoftAssert();
    dbMTEST = new DbMTEST();
    String cspUser=excelUtil.getCellData("WeekendLoan",18,1).trim();
    String getChannelId="select userid from tm_user where usercode='"+cspUser+"'";

    ResultSet rs=dbMTEST.executeQuery(getChannelId);
    String channelId="";
    while(rs.next()){
      channelId=rs.getString("userid");
    }
    String updateWalletBalance="update tm_channel set availablelimit=100.00 where channelid="+channelId;
    dbMTEST.executeQuery(updateWalletBalance);
    loginPage.login(cspUser, "roinet@1234", "KMJKN");
    loginPage.Login_With_OTP("222111");
    homePage.ClickonWALLET();
    homePage.goToWeekendLoan(cspUser);
    action = new Actions(driver);

  }

  @Test(priority = 1,testName = "apply for loan")
  public void applyLoan() throws Exception {
    weekendLoanResultPage.clickAddButton();
    String spouse=excelUtil.getCellData("WeekendLoan",1,1);
    String loanAmount=excelUtil.getCellData("WeekendLoan",19,1);
    String gstStmt=excelUtil.getCellData("WeekendLoan",3,1);
    String bankStmt=excelUtil.getCellData("WeekendLoan",4,1);
    String otherDoc=excelUtil.getCellData("WeekendLoan",5,1);
    String chequeScan=excelUtil.getCellData("WeekendLoan",6,1);
    String chequeNumber=excelUtil.getCellData("WeekendLoan",7,1);
    String chequeBank= excelUtil.getCellData("WeekendLoan",8,1);
    String chequeOf=excelUtil.getCellData("WeekendLoan",9,1);
    String apprEmail=excelUtil.getCellData("WeekendLoan",10,1);
    String panNumber=excelUtil.getCellData("WeekendLoan",11,1);
    String panDoc=excelUtil.getCellData("WeekendLoan",12,1);
    String aadharNumber=excelUtil.getCellData("WeekendLoan",13,1);
    String aadharDoc=excelUtil.getCellData("WeekendLoan",14,1);
    String cibilDoc=excelUtil.getCellData("WeekendLoan",15,1);
    String walletExposureAmt=excelUtil.getCellData("WeekendLoan",23,1);
    String resStability=excelUtil.getCellData("WeekendLoan",24,1);
    addWeekendLoanPage.fillLoanDetails(spouse,loanAmount,gstStmt,bankStmt,otherDoc,
          chequeScan, chequeNumber, chequeBank,walletExposureAmt, resStability,chequeOf,apprEmail,panNumber,panDoc,aadharNumber,
          aadharDoc, cibilDoc);
    addWeekendLoanPage.clickSaveButton();
    String loanNumber=addWeekendLoanPage.getLoanNumber();
    System.out.println("loanNumber: "+loanNumber);
    File file = new File("loanNumberWithoutGuarantor.txt");
    try {
      // Check if the file exists
      if (!file.exists()) {
        // If the file doesn't exist, create it
        boolean fileCreated = file.createNewFile();
        if (fileCreated) {
          System.out.println("File created successfully.");
        } else {
          System.out.println("File already exists or cannot be created.");
        }
      }
      try (FileWriter writer = new FileWriter(file)) {
        // Write the loan number to the file
        writer.write(loanNumber);
        System.out.println("Loan number saved to file.");
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

  }

 /* @AfterClass
  public void quit(){
    driver.quit();

  }*/
}
