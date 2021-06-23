package com.selenium.hack1;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class performAllTasks{
	  //Initializing the driver
	    WebDriver driver;
	    Properties prop = new Properties();
	    
	    @BeforeMethod
	    public void setup(){
 
	    //set path to chromedriver.exe
	    //System.setProperty("webdriver.chrome.driver",prop.getProperty("chromeDriverPath"));
	    System.setProperty("webdriver.chrome.driver","C:/Users/srao9/workspace/FirstSeleniumProject/drivers/chromedriver_win32 (1)/chromedriver.exe");
	    //create chrome instance

	    driver = new ChromeDriver();
        
	    //implicit wait
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();

	    }
		
		
		@Test
		public void clickAllLinks() throws IOException, InterruptedException{
			//open the page 
			driver.get("https://in.bookmyshow.com/");
             Thread.sleep(3000);
             String mainPageHandle = driver.getWindowHandle();
             //System.out.println(mainPageHandle);
             driver.findElement(By.xpath("//button[@class='Sign me Up!']")).click();
             
             //driver.navigate().to(mainPageHandle);
             Thread.sleep(3000);
            //driver.findElement(By.xpath("//*[@class='sc-kZmsYB jJgfRB']/strong"));
     
             //Thread.sleep(5000);
             
             //selct the city as 'NCR'
            driver.findElement(By.xpath("//img[@alt='NCR']")).click();
             Thread.sleep(2000);
             
             //click the button
             driver.findElement(By.xpath("//div[text()='Sign in']")).click();
             Thread.sleep(2000);
             
             //click on google link
             driver.findElement(By.xpath("//*[@id='modal-root']/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/div")).click();
             Thread.sleep(2000);
             
             //get all the window links
             Set<String> windowIDs = driver.getWindowHandles();
             
             //get the title of sign in window
             String title = "Sign in – Google accounts";
             String currentWindow = driver.getWindowHandle();
             driver.switchTo().window(currentWindow);
             System.out.println(windowIDs);
             
             for(String winHandle : windowIDs){
     			
       		  //checking the title of window is equal to "Visit Us" or not 
       		     if (driver.switchTo().window(winHandle).getTitle().equals(title)) {
       			  driver.findElement(By.xpath("//*[@id='identifierId']")).sendKeys("srao9152@hds.com");
       			  Thread.sleep(2000);
       			  //driver.findElement(By.xpath("//*[@id='identifierNext']/div/button/span")).click();
       			  //String error = driver.findElement(By.id("//*[@id='view_container']/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[2]/div[2]/div/text()")).getText();
                  //String errormsg = error.getText();
                  //System.out.println(errormsg);
       			  Thread.sleep(2000);
       			driver.close();
       			System.out.println(winHandle);
       			System.out.println(mainPageHandle);
       			
       			  driver.switchTo().window(mainPageHandle); //navigate to the main page window
       			 
       		      
       		     } 
       		     else {
       		        driver.switchTo().window(currentWindow);
       		     } 
       		  }
             
             //cancel the signin button now
             driver.findElement(By.xpath("//*[@id='modal-root']/div/div/div/div/div[1]/div[3]/div")).click();
           //driver.get("https://in.bookmyshow.com/explore/sports-national-capital-region-ncr");
   
             //click on sports link
             driver.findElement(By.xpath("//*[@id='super-container']/div[2]/header/div[2]/div/div/div/div[1]/div/a[5]")).click();
             Thread.sleep(4000);
             
             //import the javascript executor
             JavascriptExecutor js = (JavascriptExecutor) driver;
             
             //scroll the window so that we can find the this weekend button
             js.executeScript("window.scrollBy(0,200)");
             Thread.sleep(3000);
             
             //click on this weekend button
             driver.findElement(By.xpath("//*[@id='super-container']/div[2]/div[4]/div[1]/div[1]/div[2]/div[1]/div[2]/div[3]/div[2]/div/div")).click();
     	     Thread.sleep(4000);
     	     
     	    js.executeScript("window.scrollBy(200,300)");
     	    Thread.sleep(3000);
     	    
     	    //click on price button
     	    driver.findElement(By.xpath("//*[@id='super-container']/div[2]/div[4]/div[1]/div[1]/div[2]/div[4]/div/div[1]")).click();
     	    Thread.sleep(3000);
     	    
     	    //click on 0-500 range button
     	    driver.findElement(By.xpath("//*[@id='super-container']/div[2]/div[4]/div[1]/div[1]/div[2]/div[4]/div[2]/div[2]/div[2]/div/div")).click();
     	    Thread.sleep(3000);
     	    
     	    //create a list to store games name,date and price
     	    //List
     	
		}
		
		
	  @AfterMethod
		//function to close the driver
		public void closeDriver(){
			driver.quit();
		}
	  
	}


