package main.Pages;


import main.Base.BaseClassUAT2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClassUAT2 {

  // Pagefactory or Object Repository

  @FindBy(xpath = "//input[@id='txtUserCode']")
  // @CacheLookup:It is used when need to improve the performance. It is used to
  // take the data from Cache so it save the time.
  WebElement UserCode;

  @FindBy(xpath = "//input[@id='txtPassword']")
  WebElement Password;

  @FindBy(xpath = "//input[@id='txtCaptcha']")
  WebElement Captchafield;

  @FindBy(xpath ="//input[@id='btnLogin']")
  WebElement loginbuton;

  @FindBy(name = "btnReset")
  WebElement ResetButton;

  @FindBy(xpath = "//a[@class='forgot-link']")
  WebElement ForgotPassword;

  @FindBy(xpath = "//a[@id='lnkForgotUserCode']")
  WebElement ForgotUserCode;

  @FindBy(xpath = "//a[@id='lnkRefresh']")
  WebElement CaptchaRefreshButton;

  @FindBy(xpath = "//a[text()='Visit Facebook ']")
  WebElement Facebook;

  @FindBy(xpath = "//a[text()='Visit LinkedIn']")
  WebElement Linkdin;

  @FindBy(xpath = "//a[text()='Visit Youtube ']")
  WebElement Youtube;

  @FindBy(xpath = "//a[text()='Visit Twitter']")
  WebElement twitter;

  @FindBy(xpath = "//a[text()='Visit Website ']")
  WebElement RoinetWebsite;

  @FindBy(xpath = "//a[text()='ABOUT ROINET']")
  WebElement AboutRoinet;

  @FindBy(xpath = "//a[text()='BECOME PARTNER']")
  WebElement BECOMEPARTNER;

  @FindBy(xpath = "//a[text()='CONTACT US']")
  WebElement CONTACTUS;

  @FindBy(xpath = "//a[normalize-space()='124-4154700']")
  WebElement Contactno;

  @FindBy(xpath = "//span[@id='rfvUserCode']")
  WebElement UserCodeStartmrk;

  @FindBy(xpath = "//span[@id='rfvPassword']")
  WebElement PasswordStartmrk;

  @FindBy(xpath = "//span[@id='rfvCaptcha']")
  WebElement CaptchaStartmrk;

  @FindBy(xpath="//label[text()='Partner Login']")
  WebElement Partnerlogintext;

  // Elements of 'Validate Login OTP' Page.
  @FindBy(xpath = "//h5[text()='Validate Login OTP']")
  WebElement OTP_PopHeading;

  @FindBy(id = "txtOtp")
  WebElement EnterOTPfield;

  @FindBy(id = "btnValidateOTP")
  WebElement ValOTPLoginButtn;

  @FindBy(id = "btnResendOTP")
  WebElement ResendOTPButtn;

  @FindBy(id = "imgCloseOTP")
  WebElement ClosePopicon;

  @FindBy(id="rfvOTP")
  WebElement StarMarkOTP;



  public LoginPage() {
    PageFactory.initElements(driver, this);
  }

  public String GetUserAStrik() {
    return UserCodeStartmrk.getText();
  }

  public String GetPassdAStrik() {
    return PasswordStartmrk.getText();
  }

  public String GetCaptchaAStrik() {
    return CaptchaStartmrk.getText();
  }

  public String Validate_loginPage_titile() {
    return driver.getTitle();

  }

  public void EnterUserCode(String UsrCode) {

    UserCode.sendKeys(UsrCode);
  }

  public void EnterPsswd(String Pswd) {

    Password.sendKeys(Pswd);
  }

  public void Entercptcha(String cptcha) {

    Captchafield.sendKeys(cptcha);
  }

  public void Click_Loginbuttn() {

    loginbuton.click();
  }

  public void Click_ResetButton() {
    ResetButton.click();

  }

  public boolean Validate_ContactNo() {
    return Contactno.isEnabled();
  }

  public void Validate_About_Roinet() {
    AboutRoinet.click();

  }

  public void Validate_BECOMEPARTNER() {
    BECOMEPARTNER.click();

  }

  public void Validate_CONTACTUS() {
    CONTACTUS.click();

  }

  public void Validate_CaptchaRefreshButton() {
    CaptchaRefreshButton.click();

  }

  public void Validate_ForgotPassword() {
    ForgotPassword.click();

  }

  public void Validate_ForgotUserCode() {
    ForgotUserCode.click();

  }

  public void Validate_Facebook() {
    Facebook.click();

  }

  public void Validate_Linkdin() {
    Linkdin.click();

  }

  public void Validate_Youtube() {
    Youtube.click();

  }

  public void Validate_twitter() {
    twitter.click();

  }

  public void Validate_RoinetWebsiter() {
    RoinetWebsite.click();

  }

  public HomePage login(String User, String Passd, String Captcha) {
    UserCode.sendKeys(User);
    Password.sendKeys(Passd);
    Captchafield.sendKeys(Captcha);
    loginbuton.click();
    return new HomePage();
  }

  public String  partnerlogintext() {
    return Partnerlogintext.getText();

  }

  // Methods Of Validate OTP Pop-up window
  public String OTP_Popup_Heading() {

    return OTP_PopHeading.getText();
  }

  public boolean OTP_Popup_ResendDisabe() {
    return ResendOTPButtn.isEnabled();

  }
  public void Login_With_OTP(String OTP) {
    EnterOTPfield.sendKeys(OTP);
    ValOTPLoginButtn.click();

  }

  public String StarOTPMark() {
    return StarMarkOTP.getText();
  }

  public void  CloseOTPpopup() {
    ClosePopicon.click();
  }
}
