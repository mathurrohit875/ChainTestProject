package main.OtherProductsLoan;

import BasePackage.BaseClassUAT2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class OtherProductGuarantor extends BaseClassUAT2 {

  WebDriverWait wait;
  Actions action;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtGuarantorName")
  WebElement txtGuarantorName;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtGuarantorMobile")
  WebElement txtGuarantorMobile;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtEmailid")
  WebElement txtEmailid;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtGuarantorOccupation")
  WebElement txtGuarantorOccupation;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtCustomerRelation")
  WebElement txtCustomerRelation;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtKnownyears")
  WebElement txtKnownyears;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtPincode")
  WebElement txtPincode;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtAadharNo")
  WebElement txtAadharNo;

  @FindBy(name = "ctl00$ContentPlaceHolder1$txtPanCardNo")
  WebElement txtPanCardNo;

  @FindBy(name = "ctl00$ContentPlaceHolder1$fluGuarantorPan")
  WebElement fluGuarantorPan;

  @FindBy(name = "ctl00$ContentPlaceHolder1$fluGuarantorAadhar")
  WebElement fluGuarantorAadhar;

  @FindBy(name = "ctl00$ContentPlaceHolder1$fluGuarantorbankstatement")
  WebElement fluGuarantorbankstatement;

  @FindBy(name = "ctl00$ContentPlaceHolder1$btnUpdate")
  WebElement btnUpdate;

  @FindBy(name = "ctl00$ContentPlaceHolder1$btnCancel")
  WebElement btnCancel;

  @FindBy(name = "ctl00$hdnLanguage")
  WebElement hdnLanguage;

  @FindBy(name = "ctl00$ContentPlaceHolder1$ddlState")
  WebElement ddlState;

  @FindBy(name = "ctl00$ContentPlaceHolder1$ddlDistrict")
  WebElement ddlDistrict;

  @FindBy(name="ctl00$ContentPlaceHolder1$txtGuarantorAddress")
  WebElement txtGuarantorAddress;

  @FindBy(name="ctl00$ContentPlaceHolder1$txtActiveCSPLoan")
  WebElement txtActiveCSPLoan;

  @FindBy(name="ctl00$ContentPlaceHolder1$btnSave")
  WebElement btnSave;

  @FindBy(id="ContentPlaceHolder1_txtDOB")
  WebElement dateOfBirth;


  JavascriptExecutor js;
  public OtherProductGuarantor() {
    PageFactory.initElements(driver, this);
    wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    action = new Actions(driver);
    js=(JavascriptExecutor) driver;
  }

  public void enterGuarantorDetail(String state, String district, String guarantorName,

                                   String guarantorMobileNumber, String guarantorEmail,
                                   String guarantorProfile, String guarantorRelation,
                                   String guarantorYearKnown,String guarantorAddress, String guarantorPinCode,String guaarantorAadhar
  ,String panNumber,String guarantorPanDoc,String guarantorAadharDoc,String guarantorBankStmt,
                                   String guarantorTotalLoan,String dob,String gender) {
    wait.until(ExpectedConditions.visibilityOf(txtGuarantorName));
    action.moveToElement(ddlState).perform();
    Select stateSelect = new Select(ddlState);
    stateSelect.selectByVisibleText(state);
    wait.until(ExpectedConditions.visibilityOf(txtGuarantorName));
    action.moveToElement(ddlDistrict).perform();
    Select districtSelect = new Select(ddlDistrict);
    districtSelect.selectByVisibleText(district);
    txtGuarantorName.sendKeys(guarantorName);

    txtGuarantorMobile.sendKeys(guarantorMobileNumber);
    txtEmailid.sendKeys(guarantorEmail);
    txtGuarantorOccupation.sendKeys(guarantorProfile);
    txtCustomerRelation.sendKeys(guarantorRelation);
    txtKnownyears.sendKeys(guarantorYearKnown);
    txtGuarantorAddress.sendKeys(guarantorAddress);
    txtPincode.sendKeys(guarantorPinCode);
    txtAadharNo.sendKeys(guaarantorAadhar);
    txtPanCardNo.sendKeys(panNumber);
    fluGuarantorPan.sendKeys(guarantorPanDoc);
    fluGuarantorAadhar.sendKeys(guarantorAadharDoc);
    fluGuarantorbankstatement.sendKeys(guarantorBankStmt);
    txtActiveCSPLoan.sendKeys(guarantorTotalLoan);
    js.executeScript("arguments[0].value='"+dob+"';",dateOfBirth);
    driver.findElement(By.xpath("//label[contains(.,'"+gender+"')]/ancestor::td/input")).click();


  }

  public void clickSaveButton(){
    btnSave.click();

  }
}
