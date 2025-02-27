package main.WeekendLoan;

import main.Base.BaseClassUAT2;
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
import java.util.List;
import java.util.Map;

public class WeekendLoanResultPage extends BaseClassUAT2 {

  WebDriverWait wait;
  Actions action;

  @FindBy(name = "ctl00$ContentPlaceHolder1$btnAddLaonRequest")
  WebElement addLoanButton;

  @FindBy(id = "ContentPlaceHolder1_txtloanNo")
  WebElement loanNoTxtBox;

  @FindBy(name = "ctl00$ContentPlaceHolder1$btnView")
  WebElement viewButton;

  @FindBy(name = "ctl00$ContentPlaceHolder1$grdList$ctl02$btnViewDetails")
  WebElement btnViewDetails;

  @FindBy(xpath = "//a[text()='View']")
  WebElement guarantor;

  @FindBy(id = "ContentPlaceHolder1_ddlSearchloanStatus")
  WebElement ddlSearchLoanStatus;

  @FindBy(name="ctl00$ContentPlaceHolder1$ddlSearchAddendumloanStatus")
  WebElement ddlSearchAddendumloanStatus;


  public WeekendLoanResultPage() {
    PageFactory.initElements(driver, this);
    wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    action = new Actions(driver);
  }

  public void clickAddButton() {
    wait.until(ExpectedConditions.visibilityOf(addLoanButton)).click();
  }

  public void enterLoanNumber(String loannumber) {
    wait.until(ExpectedConditions.visibilityOf(loanNoTxtBox));
    loanNoTxtBox.clear();
    loanNoTxtBox.sendKeys(loannumber);
  }

  public void clickViewButton() {
    viewButton.click();
  }

  public void btnViewDetails() {
    wait.until(ExpectedConditions.visibilityOf(btnViewDetails)).click();

  }

  public void clickGuarantorDetailButton() {
    wait.until(ExpectedConditions.visibilityOf(guarantor)).click();
  }

  public void selectLoanStatus(String status) {
    Select select = new Select(ddlSearchLoanStatus);
    select.selectByVisibleText(status);
  }

  public Map<String, String> getTableColumn() {
    Map<String, String> column=new HashMap<>();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//th")));
    List<WebElement> tableColumn = driver.findElements(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//th"));
    for (WebElement webElement : tableColumn) {
      action.moveToElement(webElement).perform();
      column.put(webElement.getText(),webElement.getText());
    }
    return column;
  }


  public  Map<String, String>  getLabelNames(){

    Map<String,String> labelName=new HashMap<>();
    // Find all label elements
    List<WebElement> labels = driver.findElements(By.cssSelector("label.ng-binding"));

    // Iterate through the labels and print visible ones
    for (WebElement label : labels) {
      if (label.isDisplayed()) {

        labelName.put(label.getText(),label.getText());
      }
    }
    return labelName;
  }

  public String getTitle(){
    return driver.findElement(By.xpath("//div[@class='title']")).getText();
  }

  public Map<String, String> Loanstatus(){
    Map<String, String> lStatus=new HashMap<>();
    Select select=new Select(ddlSearchLoanStatus);
    List<WebElement> optionList = select.getOptions();
    System.out.println(optionList.size());
    for(WebElement ele:optionList){
      lStatus.put(ele.getText().trim(),ele.getText().trim());
    }
    return lStatus;
  }

  public Map<String, String> Addendumstatus(){
    Map<String, String> aStatus=new HashMap<>();
    Select select=new Select(ddlSearchAddendumloanStatus);
    List<WebElement> optionList = select.getOptions();
    System.out.println(optionList.size());
    for(WebElement ele:optionList){

      aStatus.put(ele.getText().trim(),ele.getText().trim());
    }
    return aStatus;
  }
}
