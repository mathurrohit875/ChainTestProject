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

import java.util.ArrayList;
import java.util.Map;


public class ThreeMonthWeeklyLoanTest extends BaseClassUAT2 {


  HomePage homePage;
  LoginPage loginPage;
  AddLongTermLoan addLongTermLoan;
  SoftAssert softAssert;
  Actions action;

  @BeforeClass
  public void setup() {
    Browserintialize("chrome", "https://uatxpresso.roinet.in/Login.aspx");
    homePage = new HomePage();
    loginPage = new LoginPage();
    addLongTermLoan = new AddLongTermLoan();
    softAssert = new SoftAssert();
    loginPage.login("CMF000041", "roinet@1234", "KMJKN");
    loginPage.Login_With_OTP("222111");
    homePage.ClickonWALLET();
    homePage.goToLongTermLoan();
    action = new Actions(driver);

  }

  @Test(priority = 1, testName = "adding loan request")
  public void addlongtermloan() {
    addLongTermLoan.clickAddLoanButton();
    addLongTermLoan.enterLoanAmount("20000");
    Map<String, String> loanDetails = addLongTermLoan.verifyEMIDetails();
    softAssert.assertEquals("020000", loanDetails.get("baseLoanAmt"), "base amount is not equal");
    softAssert.assertEquals("100.00", loanDetails.get("bounceCharge"), "emi bounce charge not match");
    softAssert.assertEquals("0.00", loanDetails.get("insuranceCharge"), "insurance charge not match");
    softAssert.assertEquals("300.00", loanDetails.get("agreementCharge"), "agreement charge not match");
    softAssert.assertEquals("0.00", loanDetails.get("fileProcessingCharge"), "file processing not match");
    softAssert.assertEquals("24", loanDetails.get("interestRate"), "interest rate does not match");
    softAssert.assertAll();
    addLongTermLoan.DurationInMonth("3 Months");
    addLongTermLoan.SelectFrequency("Weekly");
    addLongTermLoan.clickCheckbox();
  }

  @Test(priority = 2, testName = "verifying loan details")
  public void verifyLoanDetails() {
    System.out.println("second test");
    Map<String, Double> verifyDetail = addLongTermLoan.verifyLoanDetails();
    System.out.println("total loan amount: " + verifyDetail.get("loanDetailTotalAmount"));
    System.out.println("Final Loan Amount Base: " + verifyDetail.get("loanDetailFinalAmountBase"));
    System.out.println("loan detail interest: " + verifyDetail.get("loanDetailInterest"));
    System.out.println("loan detail emi loan amount: " + verifyDetail.get("loanDetailLoanAmountEMI"));
    System.out.println("loan detail emi: " + verifyDetail.get("loanDetailEMI"));
    System.out.println("loan detail emi amount: " + verifyDetail.get("loanDetailAmountEmi"));
    double R = 0.24;
    double N = 16.00;
    double weeklyRate = R / 12 / 4;

    // EMI calculation
    double emi = Math.round(verifyDetail.get("loanDetailTotalAmount") * weeklyRate * Math.pow(1 + weeklyRate, N)
          / (Math.pow(1 + weeklyRate, N) - 1));
    System.out.println("check emi: " + emi);
    softAssert.assertEquals(verifyDetail.get("loanDetailAmountEmi"), emi, "emi amount does not match.");
    softAssert.assertAll();
    collectLoanDetailFromTable();
  }

  public void collectLoanDetailFromTable() {

    Map<String, Double> verifyDetail = addLongTermLoan.verifyLoanDetails();
    ArrayList<Integer> arrayList = new ArrayList<>();
    int installmentcalculate = 0;

    int prinAmtCal;
    double lblInterestAmt = 0.00;
    //double finalInterestAmt = 0.00;
    int tablentCal = 0;
    double R = 0.24;
    double weeklyRate = R / 12 / 4;

    addLongTermLoan.moveToEmiTable();
    int emiTableSize = addLongTermLoan.EmiDetail();
    System.out.println("table size: " + emiTableSize);

    for (int i = 0; i < emiTableSize; i++) {
      int getPrevBal = addLongTermLoan.getPrevBalance((i));  // (i+2) adjusted
      System.out.println("prev balance: " + getPrevBal);
      if (getPrevBal == 0) {
        break;
      } else {
        System.out.println("checking at position: " + (i + 1));
        int installmentAmt = addLongTermLoan.getInstallmentAmount((i + 1));
        System.out.println("installment amount: " + installmentAmt);
        installmentcalculate += installmentAmt;

        int interestAmtInt = addLongTermLoan.getinterestAmountele((i + 1));
        System.out.println("interest amount: " + interestAmtInt);
        tablentCal += interestAmtInt;

        int prinAmt = addLongTermLoan.getprincipalAmount((i + 1));
        System.out.println("principal amount: " + prinAmt);
        prinAmtCal = installmentAmt - interestAmtInt;
        System.out.println("calculated principal: " + prinAmtCal);

        softAssert.assertEquals(prinAmtCal, prinAmt, 1.00, "Principal amount mismatch.");
        lblInterestAmt = Math.round((getPrevBal * (weeklyRate)));
        System.out.println("calculated interest: " + lblInterestAmt);

        softAssert.assertEquals(interestAmtInt, lblInterestAmt, 1.00, "Interest mismatch.");
        //finalInterestAmt += lblInterestAmt;
        arrayList.add(installmentAmt);
      }
    }

    double finalLoanAmtStr = verifyDetail.get("loanDetailLoanAmountEMI");
    System.out.println("Total installment amount: " + finalLoanAmtStr);
    softAssert.assertEquals(finalLoanAmtStr, installmentcalculate, 1.00, "EMI calculation mismatch.");

    double interestAmountStr = verifyDetail.get("loanDetailInterest");
    System.out.println("Total interest amount: " + interestAmountStr);
    softAssert.assertEquals(tablentCal, interestAmountStr, 1.00, "Interest calculation mismatch.");

    softAssert.assertAll();
  }

}
