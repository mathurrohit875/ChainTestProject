package main.OtherProductsLoan;

import BasePackage.BaseClassUAT2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class OtherProductLoanResultPage extends BaseClassUAT2 {

  WebDriverWait wait;
  Actions action;
  Select select;


  @FindBy(name = "ctl00$ContentPlaceHolder1$btnAddLaonRequest")
  WebElement addLoanButton;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[1]")
  WebElement usercode;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[2]")
  WebElement username;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[3]")
  WebElement loanTerm;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[4]")
  WebElement loanPenalty;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[5]")
  WebElement loanRepayTime;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[6]")
  WebElement loanTenureDays;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[7]")
  WebElement loanNo;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[8]")
  WebElement loanAmount;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[9]")
  WebElement fileCharge;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[10]")
  WebElement fileProcessCharge;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[11]")
  WebElement durationInMonth;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[12]")
  WebElement loanInterestRate;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[13]")
  WebElement emiFrequency;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[14]")
  WebElement emiType;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[15]")
  WebElement totalPaidAmount;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[16]")
  WebElement balanceAmount;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[17]")
  WebElement numberOfDueEmis;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[18]")
  WebElement nextDueDate;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[19]")
  WebElement approvedBy;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[20]")
  WebElement approvedRemarks;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[21]")
  WebElement approvedDate;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[22]")
  WebElement remarks;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[23]")
  WebElement loanStatus;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[24]//a")
  WebElement gstStatement;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[25]//a")
  WebElement bankStatement;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[26]//a")
  WebElement otherDocs;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[27]")
  WebElement panCard;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[28]")
  WebElement gstNumber;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[29]")
  WebElement aadhar;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[30]//a")
  WebElement panCardDoc;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[31]//a")
  WebElement aadharDoc;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[32]//a")
  WebElement cibilDoc;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[33]//a")
  WebElement emailDoc;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[34]")
  WebElement eMandate;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[35]")
  WebElement eSign;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[36]")
  WebElement arthmateSign;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[37]")
  WebElement getLoanFromArthmate;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[38]")
  WebElement viewDetails;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[39]")
  WebElement reviewReport;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[40]")
  WebElement foreClosure;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[41]")
  WebElement emailDocPDF;


  @FindBy(xpath = "//tr[@class='dgrow1']/td[42]")
  WebElement defaultClosure;

  @FindBy(name ="ctl00$ContentPlaceHolder1$txtLoanNo")
  WebElement loanNoTxtBox;

  @FindBy(name = "ctl00$ContentPlaceHolder1$ddlSearchLoanStatus")
  WebElement loanStatusResult;

  @FindBy(name="ctl00$ContentPlaceHolder1$btnView")
  WebElement viewButton;

  @FindBy(name="ctl00$ContentPlaceHolder1$grdList$ctl02$btnViewDetails")
  WebElement btnViewDetails;

  @FindBy(xpath ="//a[contains(.,'Guarantor Detail')]")
  WebElement guarantorDetailButton;

  public OtherProductLoanResultPage() {
    PageFactory.initElements(driver, this);
    wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    action = new Actions(driver);
  }

  public void clickAddLoanButton() {
    wait.until(ExpectedConditions.visibilityOf(addLoanButton));
    addLoanButton.click();
  }

  public Map<String, String> getDataResultGrid() {
    Map<String, String> fieldsText = new HashMap<>();


    fieldsText.put("usercodeData",usercode.getText().trim());
    fieldsText.put( "usernameData" , username.getText().trim());
    fieldsText.put( "loanTermData", loanTerm.getText().trim());
    fieldsText.put( "loanPenaltyData", loanPenalty.getText().trim());
    fieldsText.put( "loanRepayTimeData", loanRepayTime.getText().trim());
    fieldsText.put( "loanTenureDaysData", loanTenureDays.getText().trim());
    fieldsText.put( "loanNoData", loanNo.getText().trim());
    fieldsText.put( "loanAmountData", loanAmount.getText().trim());
    fieldsText.put( "fileChargeData", fileCharge.getText().trim());
    fieldsText.put( "fileProcessChargeData", fileProcessCharge.getText().trim());
    fieldsText.put( "durationInMonthData", durationInMonth.getText().trim());
    fieldsText.put( "loanInterestRateData", loanInterestRate.getText().trim());
    fieldsText.put( "emiFrequencyData", emiFrequency.getText().trim());
    fieldsText.put( "emiTypeData", emiType.getText().trim());
    fieldsText.put( "totalPaidAmountData", totalPaidAmount.getText().trim());
    fieldsText.put( "balanceAmountData", balanceAmount.getText().trim());
    fieldsText.put( "numberOfDueEmisData", numberOfDueEmis.getText().trim());
    fieldsText.put( "nextDueDateData", nextDueDate.getText().trim());
    fieldsText.put( "approvedByData", approvedBy.getText().trim());
    fieldsText.put( "approvedRemarksData", approvedRemarks.getText().trim());
    fieldsText.put( "approvedDateData", approvedDate.getText().trim());
    fieldsText.put( "remarksData", remarks.getText().trim());
    fieldsText.put( "loanStatusData", loanStatus.getText().trim());
    fieldsText.put( "gstStatementData", gstStatement.getText().trim());
    fieldsText.put( "bankStatementData", bankStatement.getText().trim());
    fieldsText.put( "otherDocsData", otherDocs.getText().trim());
    fieldsText.put( "panCardData", panCard.getText().trim());
    fieldsText.put( "gstNumberData", gstNumber.getText().trim());
    fieldsText.put( "aadharData", aadhar.getText().trim());
    fieldsText.put( "panCardDocData", panCardDoc.getText().trim());
    fieldsText.put( "aadharDocData", aadharDoc.getText().trim());
    fieldsText.put( "cibilDocData", cibilDoc.getText().trim());

    fieldsText.put( "emailDocData", emailDoc.getText().trim());
    fieldsText.put( "eMandateData", eMandate.getText().trim());
    fieldsText.put( "eSignData", eSign.getText().trim());
    fieldsText.put( "arthmateSignData", arthmateSign.getText().trim());
    fieldsText.put( "getLoanFromArthmateData", getLoanFromArthmate.getText().trim());
    fieldsText.put( "viewDetailsData", viewDetails.getText().trim());
    fieldsText.put( "reviewReportData", reviewReport.getText().trim());
    fieldsText.put( "foreClosureData", foreClosure.getText().trim());
    fieldsText.put( "emailDocPDFData", emailDocPDF.getText().trim());
    fieldsText.put( "defaultClosureData", defaultClosure.getText().trim());

    return fieldsText;

  }

  public void enterLoanNumber(String loanNumber){
    wait.until(ExpectedConditions.visibilityOf(loanNoTxtBox));
    loanNoTxtBox.clear();
    loanNoTxtBox.sendKeys(loanNumber);
  }

  public void selectLoanStatus(String loanSta){
    Select select=new Select(loanStatusResult);
    select.selectByVisibleText(loanSta);
  }

  public void clickViewButton(){
    wait.until(ExpectedConditions.visibilityOf(viewButton));
    viewButton.click();
  }
  public void clickGuarantorDetailButton(String loanno){
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class='dgrow1']//td[contains(.,'"+loanno+"')]//ancestor::tr//a[contains(.,'Guarantor Detail')]"))).click();
  }
  public void btnViewDetails(){
    wait.until(ExpectedConditions.visibilityOf(btnViewDetails));
    btnViewDetails.click();
  }
}
