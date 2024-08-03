package test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LandingPage {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//a/div[text()='Revenue Calculator']")
	WebElement RevenueCalculatorele;
	
	@FindBy(xpath="//input[@aria-orientation='horizontal']")
	WebElement SliderMove;
	

	@FindBy(xpath="//p[text()='Total Individual Patient/Month:']/p")
	WebElement TotalIndPatientMonth;
	
	@FindBy(xpath="//p[text()='Total Recurring Reimbursement for all Patients Per Month:']/p")
	WebElement TotalRecur;
	
	
	
	@FindBy(xpath="//input[@id=':r0:']")
	WebElement SliderValue1;
	
	@FindBy(xpath="//div//fieldset[@class='MuiOutlinedInput-notchedOutline css-igs3ac']")
	WebElement SliderValue;

	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 
	  String value1="560";
	
	public void JSScroll(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}
	

	public void JSClick(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
	}
	
	public void JSSendKey(WebElement ele,String Value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='"+ Value +"';", ele);
	}
	
	
	String ActualRevenueCalURL="https://www.fitpeo.com/revenue-calculator";
	
	public void NavigateToRevenueCalculatorPage() throws InterruptedException {
	RevenueCalculatorele.click();
	Thread.sleep(7000);
	String ExpectedRevenueCalURL=driver.getCurrentUrl();
	Assert.assertEquals(ExpectedRevenueCalURL, ActualRevenueCalURL,"Actual and Expected Url are same");
	
	}
	
	public void ScrollDowntotheSlidersection() throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");	
		
		}
	
	public void AdjustTheSlider() throws InterruptedException {
		Actions move = new Actions(driver);
		move.moveToElement(SliderMove).clickAndHold().moveByOffset(94,0).release().perform();
		Thread.sleep(7000);		
		}
	
	
	
	public void UpdateTheTextField() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(5000);
		SliderValue1.click();
//		SliderValue1.clear();
		SliderValue1.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
		SliderValue1.sendKeys(value1);
		Thread.sleep(7000);	
		}
	
	public void ValidateSliderValue() throws InterruptedException {
	     WebElement slidertext = driver.findElement(By.xpath("//input[@aria-orientation='horizontal']"));
	     String value=slidertext.getAttribute("value") ;
	     Assert.assertEquals(value, value1,"Actual and Expected Url are same");
		}
	
	
	public int SelectCPTCodes() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		int arr[]= {99091,99454,99474};		
		int TotalRecurssive=Integer.parseInt(TotalIndPatientMonth.getText());
		int Total = 0;		
			for(int i=0;i<arr.length;i++) {
				WebElement element1 = driver.findElement(By.xpath("//p[text()='CPT-"+arr[i]+"']/parent::div/label/span/input[@type='checkbox']"));
				JSScroll(element1);
				Actions move = new Actions(driver);
				move.moveToElement(element1);
				JSClick(element1);
				Thread.sleep(5000);
				WebElement Recurr = driver.findElement(By.xpath("//p[text()='CPT-"+arr[i]+"']/parent::div/label/span[contains(@class,'MuiTypography-root')]"));	
				int TotalRecurr=Integer.parseInt(Recurr.getText());
				Total=TotalRecurr*TotalRecurssive+Total;	
			}
			return Total;
		}
	
	
	public void TotalReimbursment() throws InterruptedException {
		String a=TotalRecur.getText().replace("$",""); 
		int TotalReembu=Integer.parseInt(a);
		Assert.assertEquals(TotalReembu,SelectCPTCodes(),"Actual and Expected Total Recurring Reimbursement for all Patients Per Month are Same");
		System.out.println("Total Recurring Reimbursement for all Patients Per Month" +SelectCPTCodes());

		}
	
}
