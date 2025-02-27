package main.LongTermLoan;


import main.Base.BaseClassUAT2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddLongTermLoan extends BaseClassUAT2 {

  @FindBy(name = "ctl00$ContentPlaceHolder1$btnAddLaonRequest")
  WebElement addLoanButton;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtLoanAmountReq")
  WebElement loanAmount;

  //EMI DETAILS//
  @FindBy(name = "ctl00$ContentPlaceHolder1$txtLoanAmount")
  WebElement baseLoanAmt;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtEmiBounceCharges")
  WebElement bounceCharge;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtInsuranceCharges")
  WebElement insuranceCharge;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtAgreementCharges")
  WebElement agreementCharge;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtFileProcessingCharges")
  WebElement fileProcessingCharge;

  @FindBy(name = "ctl00$ContentPlaceHolder1$ddlInterestRate")
  WebElement interestRate;

  @FindBy(id = "ContentPlaceHolder1_chkTNC")
  WebElement termsCheckbox;

  //LOAN DETAILS//
  @FindBy(xpath = "//div[@id='ContentPlaceHolder1_pnlEmiCalc']//div[@class='borderbox']//div[@class='col-md-3'][1]//span")
  WebElement totalAmountBase;

  @FindBy(xpath = "//div[@id='ContentPlaceHolder1_pnlEmiCalc']//div[@class='borderbox']//div[@class='col-md-3'][2]//span")
  WebElement finalLoanAmountBase;
  @FindBy(xpath = "//div[@id='ContentPlaceHolder1_pnlEmiCalc']//div[@class='borderbox']//div[@class='col-md-3'][3]//span")
  WebElement interestAmount;
  @FindBy(xpath = "//div[@id='ContentPlaceHolder1_pnlEmiCalc']//div[@class='borderbox']//div[@class='col-md-3'][4]//span")
  WebElement finalLoanAmountEMI;
  @FindBy(xpath = "//div[@id='ContentPlaceHolder1_pnlEmiCalc']//div[@class='borderbox']//div[@class='col-md-3'][5]//span")
  WebElement totalEMI;
  @FindBy(xpath = "//div[@id='ContentPlaceHolder1_pnlEmiCalc']//div[@class='borderbox']//div[@class='col-md-3'][6]//span")
  WebElement emiAmount;

  @FindBy(id = "ContentPlaceHolder1_grdEMI")
  WebElement emiTable;
  //Duration in month
  @FindBy(name = "ctl00$ContentPlaceHolder1$ddlDurationInMonths")
  WebElement durationInMonth;
  @FindBy(name = "ctl00$ContentPlaceHolder1$ddlFrequency")
  WebElement frequency;

  WebDriverWait wait;
  Actions action;
  Select select;
  Map<String, Double> loanDetail = new HashMap<>();
  @FindBy(name = "ctl00$ContentPlaceHolder1$txtPANNo")
  WebElement panNumber;
  @FindBy(id = "ContentPlaceHolder1_updPAN")
  WebElement panDoc;
  @FindBy(name = "ctl00$ContentPlaceHolder1$txtAadharNo")
  WebElement aadharNumber;
  @FindBy(name = "ctl00$ContentPlaceHolder1$updAadhar")
  WebElement aadharDoc;
  @FindBy(name = "ctl00$ContentPlaceHolder1$updChequeScanCopies")
  WebElement chequeScanCopy;
  @FindBy(name = "ctl00$ContentPlaceHolder1$txtChequeNos")
  WebElement chequeNumber;
  @FindBy(name = "ctl00$ContentPlaceHolder1$txtChequeBank")
  WebElement chequeBank;
  @FindBy(name = "ctl00$ContentPlaceHolder1$ddlChequeOf")
  WebElement chequeOf;
  @FindBy(name = "ctl00$ContentPlaceHolder1$updGstStatement")
  WebElement gstStatement;
  @FindBy(name = "ctl00$ContentPlaceHolder1$updBankStatement")
  WebElement bankStatement;
  @FindBy(name = "ctl00$ContentPlaceHolder1$updOtherDocs")
  WebElement otherDoc;
  @FindBy(name = "ctl00$ContentPlaceHolder1$updApprEmail")
  WebElement apprEmail;
  @FindBy(name = "ctl00$ContentPlaceHolder1$updLedgerIncomeFromComp")
  WebElement ledgerIncome;
  @FindBy(name = "ctl00$ContentPlaceHolder1$updShopOwnerProof")
  WebElement shopOwnerProof;
  @FindBy(name = "ctl00$ContentPlaceHolder1$txtOtherBusinessDetail")
  WebElement otherBusinessDetail;
  @FindBy(name = "ctl00$ContentPlaceHolder1$txtOperatorWorkWith")
  WebElement operatorWorkWith;
  @FindBy(name = "ctl00$ContentPlaceHolder1$txtOtherLoanFromComp")
  WebElement otherLoan;
  @FindBy(name = "ctl00$ContentPlaceHolder1$updCIBIL")
  WebElement cibilDoc;

  //Submit Button to submit loan request
  @FindBy(name = "ctl00$ContentPlaceHolder1$btnAssign")
  WebElement submit;
  // Locate all rows in the EMI table
  @FindBy(xpath = "//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center']")
  private List<WebElement> emiRows;

  @FindBy(name="ctl00$ContentPlaceHolder1$ddlLoanStatus")
  WebElement ddlLoanStatus;

  @FindBy(name = "ctl00$ContentPlaceHolder1$btnGoAhead")
  WebElement btnGoAhead;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtApprovedRemarks")
  WebElement txtApprovedRemarks;

  public AddLongTermLoan() {
    PageFactory.initElements(driver, this);
    wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    action = new Actions(driver);

  }

  public void EnterLoanDetails(String pan, String panCardPath, String aadharCardNumber,
                               String aadharCardPath, String scanChequePath, String chequeNumb, String bankCheque,
                               String chequeOfOption, String gstDocPath, String bankDocPath, String otherDocPath, String emailApprovPath,
                               String incomeLedgerPath, String shopOwnerPath, String otherBusiness, String otherOperator,
                               String otherLoanDetail, String cibilDocPathPath) {

    action.moveToElement(panNumber).perform();
    select = new Select(chequeOf);
    select.selectByVisibleText(chequeOfOption);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loader-wrapper']")));

    panNumber.clear();
    panNumber.sendKeys(pan);
    panDoc.sendKeys(panCardPath);


    aadharNumber.clear();
    aadharNumber.sendKeys(aadharCardNumber);
    aadharDoc.sendKeys(aadharCardPath);

    chequeScanCopy.sendKeys(scanChequePath);
    chequeNumber.sendKeys(chequeNumb);
    chequeBank.sendKeys(bankCheque);
    gstStatement.sendKeys(gstDocPath);
    bankStatement.sendKeys(bankDocPath);
    otherDoc.sendKeys(otherDocPath);
    apprEmail.sendKeys(emailApprovPath);
    action.moveToElement(ledgerIncome).perform();
    ledgerIncome.sendKeys(incomeLedgerPath);
    shopOwnerProof.sendKeys(shopOwnerPath);
    otherBusinessDetail.sendKeys(otherBusiness);
    operatorWorkWith.sendKeys(otherOperator);
    otherLoan.sendKeys(otherLoanDetail);
    cibilDoc.sendKeys(cibilDocPathPath);

  }

  public void submitLoanRequest() {
    wait.until(ExpectedConditions.visibilityOf(submit));
    submit.click();
  }

  public void confirmLoanRequest(){
    wait.until(ExpectedConditions.alertIsPresent());
    driver.switchTo().alert().accept();
  }
  public String getLoanNumber() throws Exception {
    wait.until(ExpectedConditions.alertIsPresent());
    String alertBoxText = driver.switchTo().alert().getText();
    System.out.println("this is alert box text: " + alertBoxText);
    // Define the regex pattern for extracting the loan number after "loan no: " and before " has been processed"
    String pattern = "Your request with loan no: (\\S+) has been processed successfully.";

    // Create a Pattern object
    Pattern r = Pattern.compile(pattern);

    // Create a matcher object
    Matcher m = r.matcher(alertBoxText);
    String loanNumber = "";
    // Check if the pattern matches and extract the loan number
    if (m.find()) {
      // Group 1 will contain the loan number
      loanNumber = m.group(1);
    } else {
      throw new Exception("catching exception for loan number.");
    }
    return loanNumber;
  }

  public void acceptLoanRequest(){
    wait.until(ExpectedConditions.alertIsPresent());
    driver.switchTo().alert().accept();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='title' and contains(.,'Apply Adhoc Limit Loan Long Term')]")));
  }

  // Method to retrieve the installment amount from a specific row (index i)
  public int getInstallmentAmount(int index) {
    String instAmt = emiRows.get(index).findElement(By.xpath(".//td[2]")).getText();
    return instAmt.isEmpty() ? 0 : Integer.parseInt(instAmt);  // Handle empty values
  }

  public void SelectFrequency(String freq) {
    wait.until(ExpectedConditions.visibilityOf(frequency));
    select = new Select(frequency);
    select.selectByVisibleText(freq);

  }

  public void DurationInMonth(String duration) {
    select = new Select(durationInMonth);
    select.selectByVisibleText(duration);

  }

  // Method to retrieve the interest amount from a specific row (index i)
  public int getinterestAmountele(int index) {
    WebElement interestAmountele = emiRows.get(index).findElement(By.xpath(".//td[3]"));

    // Focus on the current row (interestAmountele)
    action.moveToElement(interestAmountele).perform();
    String interestAmt = emiRows.get(index).findElement(By.xpath(".//td[3]")).getText();
    return interestAmt.isEmpty() ? 0 : Integer.parseInt(interestAmt);
  }

  // Method to retrieve the principal amount from a specific row (index i)
  public int getprincipalAmount(int index) {
    WebElement principalAmountele = emiRows.get(index).findElement(By.xpath(".//td[4]"));

    // Focus on the current row (principalAmountele)
    action.moveToElement(principalAmountele).perform();
    String prinAmt = emiRows.get(index).findElement(By.xpath(".//td[4]")).getText();
    return prinAmt.isEmpty() ? 0 : Integer.parseInt(prinAmt);
  }

  // Method to retrieve the previous balance from a specific row (index i)
  public int getPrevBalance(int index) {
    WebElement prevBalanceAmountele = emiRows.get(index).findElement(By.xpath(".//td[5]"));

    // Focus on the current row (prevBalanceAmountele)
    action.moveToElement(prevBalanceAmountele).perform();
    String prevBal = emiRows.get(index).findElement(By.xpath(".//td[5]")).getText();
    return prevBal.isEmpty() ? 0 : Integer.parseInt(prevBal);  // Handle empty string
  }

  // Method to get the total number of rows in the EMI table
  public int EmiDetail() {
    return emiRows.size();  // Return the number of rows in the EMI table
  }

  public void clickAddLoanButton() {
    wait.until(ExpectedConditions.visibilityOf(addLoanButton));
    addLoanButton.click();
  }

  public void enterLoanAmount(String loan) {
    wait.until(ExpectedConditions.visibilityOf(loanAmount));
    /*loanAmount.clear();
    try{
      driver.switchTo().alert().accept();
    }
    catch(Exception e){
      System.out.println("no exception "+e.getMessage());
    }*/
    loanAmount.sendKeys(loan);
    action.sendKeys(Keys.TAB).perform();
  }

  public Map<String, String> verifyEMIDetails() {
    Map<String, String> loanDetails = new HashMap<>();
    wait.until(ExpectedConditions.visibilityOf(baseLoanAmt));
    action.moveToElement(agreementCharge).perform();
    baseLoanAmt.getAttribute("value");
    loanDetails.put("baseLoanAmt", baseLoanAmt.getAttribute("value"));
    loanDetails.put("bounceCharge", bounceCharge.getAttribute("value"));
    loanDetails.put("insuranceCharge", insuranceCharge.getAttribute("value"));
    loanDetails.put("agreementCharge", agreementCharge.getAttribute("value"));
    loanDetails.put("fileProcessingCharge", fileProcessingCharge.getAttribute("value"));
    loanDetails.put("interestRate", interestRate.getAttribute("value"));
    return loanDetails;
  }

  public void clickCheckbox() {
    action.moveToElement(termsCheckbox).perform();
    termsCheckbox.click();
  }

  public Map<String, Double> verifyLoanDetails() {
    wait.until(ExpectedConditions.visibilityOf(totalAmountBase));
    action.moveToElement(emiAmount).perform();
    String totalAmount = totalAmountBase.getText();
    String loanAmountBase = finalLoanAmountBase.getText();
    String interest = interestAmount.getText();
    String loanAmountEMI = finalLoanAmountEMI.getText();
    String emi = totalEMI.getText();
    String amountEmi = emiAmount.getText();
    double loanDetailTotalAmount = Double.parseDouble(totalAmount);
    double loanDetailFinalAmountBase = Double.parseDouble(loanAmountBase);
    double loanDetailInterest = Double.parseDouble(interest);
    double loanDetailLoanAmountEMI = Double.parseDouble(loanAmountEMI);
    double loanDetailEMI = Double.parseDouble(emi);
    double loanDetailAmountEmi = Double.parseDouble(amountEmi);

    loanDetail.put("loanDetailTotalAmount", loanDetailTotalAmount);
    loanDetail.put("loanDetailFinalAmountBase", loanDetailFinalAmountBase);
    loanDetail.put("loanDetailInterest", loanDetailInterest);
    loanDetail.put("loanDetailLoanAmountEMI", loanDetailLoanAmountEMI);
    loanDetail.put("loanDetailEMI", loanDetailEMI);
    loanDetail.put("loanDetailAmountEmi", loanDetailAmountEmi);
    return loanDetail;
  }

public void ddlLoanStatus(String loanStatus){
    wait.until(ExpectedConditions.visibilityOf(ddlLoanStatus));
  Select select=new Select(ddlLoanStatus);
  select.selectByVisibleText(loanStatus);
  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ContentPlaceHolder1_pnlUserDetailVW']//div[contains(.,'Apply Adhoc Limit Loan')]")));
  action.sendKeys(Keys.END).perform();
}
public void addRemarks(String remark){
  action.moveToElement(txtApprovedRemarks).perform();
  txtApprovedRemarks.sendKeys(remark);
}

public void btnGoAhead(){
    wait.until(ExpectedConditions.visibilityOf(btnGoAhead));
    btnGoAhead.click();
    wait.until(ExpectedConditions.alertIsPresent());
    driver.switchTo().alert().accept();
}
  /*int installmentcalculate = 0;
  int previousBalance;
  int installmentamountinteger;
  double lblInterestAmt;
  double interestAmountC;
  int prinAmt;
  int intrstamt;
  int prinAmtCal;
  boolean principalBoolean;
  double finalInterestAmt = 0.00;
  double R=0.24;
  ArrayList arrayList = new ArrayList<>();*/

 /* public int checkBalance(){
    String prevBalance = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center'][" + (1) + "]/td[5]")).getText();
    previousBalance = Integer.parseInt(prevBalance);
    return previousBalance;
  }*/

  public void moveToEmiTable() {
    action.moveToElement(emiTable).perform();
  }
  /*public void collectLoanDetailFromTable(){
    moveToEmiTable();
    List<WebElement> emiDetais = driver.findElements(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center']"));
    for (int i = 0; i < emiDetais.size(); i++) {
      String prevBalance = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center'][" + (i + 1) + "]/td[5]")).getText();
      previousBalance = Integer.parseInt(prevBalance);
      if (previousBalance == 0) {
        break;
      } else {
        System.out.println("this is previous balance in integer format: " + previousBalance);
        WebElement emiInstallment = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center'][" + (i + 2) + "]/td[2]"));
        action.moveToElement(emiInstallment).perform();
        WebElement installmentAmountele = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center'][" + (i + 2) + "]/td[2]"));
        String installmentAmt = installmentAmountele.getText();
        installmentamountinteger = Integer.parseInt(installmentAmt);
        installmentcalculate += installmentamountinteger;
        WebElement interestAmountele = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center'][" + (i + 2) + "]/td[3]"));
        String interestAmt = interestAmountele.getText();
        interestAmountC = Double.parseDouble(interestAmt);
        intrstamt = Integer.parseInt(interestAmt);
        WebElement principalAmount = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center'][" + (i + 2) + "]/td[4]"));
        String prinString = principalAmount.getText();
        prinAmt = Integer.parseInt(prinString);
        System.out.println("this is principal amount in the grid: " + prinAmt);
        prinAmtCal = installmentamountinteger - intrstamt;
        System.out.println("this is principal amount after my caculation: " + prinAmtCal);
        //softAssert.assertEquals(prinAmtCal,prinAmt,1.00,"principal amount does not match with my calculation. Expected: "+prinAmtCal+", Actual: "+prinAmt);
        principalBoolean = prinAmt == prinAmtCal;
        System.out.println("this is the boolean value for comparison of principal values: " + principalBoolean);
        System.out.println("this is interest amount on frontend after converting to integer: " + interestAmountC);
        lblInterestAmt = Math.round((previousBalance * (R / 12 / 25)));
        System.out.println("this is interest amount from my calculation: " + lblInterestAmt);
        //softAssert.assertEquals(interestAmountC, lblInterestAmt, 1.00, "interest amount mismatch. Expected: " + interestAmountC + ", Actual: " + lblInterestAmt);
        boolean b = interestAmountC == lblInterestAmt;
        System.out.println("checking the interest value if they are equal on frontend and through calculation: " + b);
        String installment = emiInstallment.getText();
        System.out.println("this is emi on frontend: " + installment);
        finalInterestAmt += lblInterestAmt;
        System.out.println("__________________________________________________________________________________");
        arrayList.add(installment);
      }

      double finalLoanAmtStr = loanDetail.get("loanDetailLoanAmountEMI");
      //int finaloanAmtInt = Integer.parseInt(finalLoanAmtStr);
      System.out.println("this is final total of installement= " + finalLoanAmtStr);
     // softAssert.assertEquals(finaloanAmtInt, installmentcalculate, 1.00, "the difference is more than 1.00");
      double interestAmountStr = loanDetail.get("loanDetailInterest");
      //nt interestAmountInt = Integer.parseInt(interestAmountStr);
      System.out.println("this is final total of interest= " + interestAmountStr);

    }
  }*/
}
