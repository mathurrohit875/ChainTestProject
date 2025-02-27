package main.Pages;


import main.Base.BaseClassUAT2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BaseClassUAT2 {

  @FindBy(xpath = "//h2[normalize-space()='WELCOME TO Silver PARTNER']")
  WebElement HeadingPartnrType;

  @FindBy(xpath = "//p[contains(text(),'Santosh')]")
  WebElement Logedusername;

  @FindBy(xpath = "//span[@id='lblAvailLimit']")
  WebElement AvailaibleBalance;

  @FindBy(xpath = "//img[@id='Img']")
  WebElement XpressoImage;

  @FindBy(xpath = "//img[@id='imgPerformance']")
  WebElement performance;

  @FindBy(xpath = "//i[@class='fa fa-home']")
  WebElement Home;

  @FindBy(xpath = "//i[@class='fa fa-user-circle-o']")
  WebElement UsrInfo;

  @FindBy(xpath = "//a[text()='CHANNEL']")
  WebElement Channel;

  @FindBy(xpath = "//a[text()='Covid Widow']")
  WebElement CovidWidowTab;

  @FindBy(xpath = "//a[text()='WALLET']")
  WebElement WALLET;

  @FindBy(xpath = "//a[contains(.,'SERVICES ')]")
  WebElement SERVICES;

  @FindBy(xpath = "//a[text()='PAN CARD']")
  WebElement PANCARD;

  @FindBy(xpath = "//*[contains(text(),'HELPDESK ')]")
  WebElement HELPDESK;

  @FindBy(xpath = "//a[text()='MIS']")
  WebElement MIS;

  @FindBy(xpath = "//a[text()='DOCUMENT CENTER']")
  WebElement DOCUMENTCENTER;

  @FindBy(xpath = "//div[@class='supporting']/ul/li[1]")
  WebElement FarudAdvisory;

  @FindBy(xpath = "//div[@class='supporting']/ul/li[2]")
  WebElement CSpBusinessProfile;

  @FindBy(xpath = "//div[@class='supporting']/ul/li[3]")
  WebElement DownldYesBankIcard;


  @FindBy(xpath = "//div[@class='supporting']/ul/li[4]")
  WebElement DownldYesBankCertificate;

  @FindBy(xpath = "//ul[@id='Menu2_Menuid']/li[1]/ul/li[4]")
  WebElement GoladLoanLead;

  @FindBy(xpath = "//a[contains(.,'Courier Service')]")
  WebElement courierService;

  @FindBy(xpath = "//a[text()='Wallet Advance']")
  WebElement walletAdvance;

  @FindBy(xpath = "//a[text()='Lending']")
  WebElement lending;

  @FindBy(xpath = "//a[contains(.,'Long Term Wallet Advance')]")
  WebElement longTermWalletAdvance;

  @FindBy(xpath = "//a[contains(.,'Apply Weekend Wallet Advance')]")
  WebElement weekendWalletAdvance;

  @FindBy(xpath = "//a[contains(.,'Apply CMS Wallet Advance')]")
  WebElement cmsWalletAdvance;

  @FindBy(xpath = "//a[contains(.,'Short Term Wallet Advance')]")
        WebElement shorttermWalletAdvance;

  @FindBy(xpath = "//a[contains(.,'Other products Wallet Advance')]")
        WebElement otherProductWalletAdvance;

  Actions actions;
  WebDriverWait wait;
  //Unwanted pop-up coming on hom page so added this method
  @FindBy(xpath = "//i[@class='fa fa-close']")
  WebElement UnwabtedWindow;

  public HomePage() {
    PageFactory.initElements(driver, this);
    actions = new Actions(driver);
    wait = new WebDriverWait(driver, Duration.ofSeconds(60));
  }

  public void goToCourierService() {
    wait.until(ExpectedConditions.visibilityOf(SERVICES));
    actions.moveToElement(SERVICES).perform();
    actions.moveToElement(courierService).click().perform();


  }

  public String verifypagetitle() {
    return driver.getTitle();
  }

  public String verifyUserName() {
    return Logedusername.getText();

  }

  public String verifyBalance() {
    return AvailaibleBalance.getText();

  }

  public void ClickXpressoImge() {
    XpressoImage.click();

  }

  public void ClickonPerformnace() {
    performance.click();

  }

  public void ClickonHome() {
    Home.click();

  }

  public void ClickonChannel() {
    Channel.click();
  }

  public void ClickCovidWidowTab() {
    Channel.click();
    CovidWidowTab.click();
  }

  public void ClickonWALLET() {
    wait.until(ExpectedConditions.visibilityOf(WALLET));
    WALLET.click();
  }

  public void ClickonServices() {
    SERVICES.click();
  }

  public void ClickonPANCARD() {
    PANCARD.click();
  }

  public void ClickonHELPDESK() {
    HELPDESK.click();
  }

  public void ClickonMIS() {
    MIS.click();
  }

  public void ClickonDOCUMENTCENTER() {
    DOCUMENTCENTER.click();
  }

  public void ClickonAdvisoryLink() {
    FarudAdvisory.click();
  }

  public void ClickonCSpBusinessProfile() {
    CSpBusinessProfile.click();
  }

  public void ClickonDownldYesBankIcard() {
    DownldYesBankIcard.click();
  }

  public void ClickonDownldYesBankCertificated() {
    DownldYesBankCertificate.click();
  }

  //Click on Gold Loan Lead
  public void ClickonGoldLoanLead() {
    Channel.click();
    GoladLoanLead.click();

  }

  public void goToLongTermLoan() {
    wait.until(ExpectedConditions.visibilityOf(walletAdvance));
    actions.moveToElement(walletAdvance).perform();
    wait.until(ExpectedConditions.visibilityOf(longTermWalletAdvance));
    actions.moveToElement(longTermWalletAdvance).click().perform();
  }

  public void goToWeekendLoan(String str){
    System.out.println("str: "+str);
    System.out.println("condition check: "+(str.equalsIgnoreCase("MONA.SHARMA")));
    if(str.equalsIgnoreCase("MONA.SHARMA")){
      wait.until(ExpectedConditions.visibilityOf(walletAdvance));
      actions.moveToElement(walletAdvance).perform();
      wait.until(ExpectedConditions.visibilityOf(weekendWalletAdvance));
      actions.moveToElement(weekendWalletAdvance).click().perform();
    } else{
      wait.until(ExpectedConditions.visibilityOf(lending));
      actions.moveToElement(lending).perform();
      wait.until(ExpectedConditions.visibilityOf(weekendWalletAdvance));
      actions.moveToElement(weekendWalletAdvance).click().perform();
    }

  }

  public void goToShortTermLoan(){
    wait.until(ExpectedConditions.visibilityOf(walletAdvance));
    actions.moveToElement(walletAdvance).perform();
    wait.until(ExpectedConditions.visibilityOf(shorttermWalletAdvance));
    actions.moveToElement(shorttermWalletAdvance).click().perform();
  }

  public void goToOtherProductLoan(){
    wait.until(ExpectedConditions.visibilityOf(walletAdvance));
    actions.moveToElement(walletAdvance).perform();
    wait.until(ExpectedConditions.visibilityOf(otherProductWalletAdvance));
    actions.moveToElement(otherProductWalletAdvance).click().perform();

  }
  public void goToCMSLoan(){
    wait.until(ExpectedConditions.visibilityOf(walletAdvance));
    actions.moveToElement(walletAdvance).perform();
    wait.until(ExpectedConditions.visibilityOf(cmsWalletAdvance));
    actions.moveToElement(cmsWalletAdvance).click().perform();
  }

  public void closeUnwantedWindow() {
    UnwabtedWindow.click();
  }
}
