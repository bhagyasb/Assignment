package test;

import java.awt.Desktop.Action;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver;
		 WebDriverManager.edgedriver().setup();

		 
		 EdgeOptions option = new EdgeOptions();
		 option.addArguments("--guest");
	
		 driver = new EdgeDriver(option);
		 
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
		driver.get("https://www.fitpeo.com");
		
		String ExpectedCurentURL=driver.getCurrentUrl();
		String ActualCurrentURL="https://www.fitpeo.com/";
		
		Assert.assertEquals(ExpectedCurentURL, ActualCurrentURL,"Actual and Expected Url are same");
		
		driver.manage().window().maximize();	
	
		
		LandingPage Landingpage=new LandingPage(driver);	
		Landingpage.NavigateToRevenueCalculatorPage();
		
		Landingpage.ScrollDowntotheSlidersection();
		Landingpage.AdjustTheSlider(); 
		
		
		Landingpage.UpdateTheTextField();
		Landingpage.ValidateSliderValue();
		
		Landingpage.SelectCPTCodes();
		Landingpage.TotalReimbursment();
		
		driver.quit();
		
		
		
		
		
		
		
          

	
		

				
				
		
		
		
		
		
	}

	

	
}
