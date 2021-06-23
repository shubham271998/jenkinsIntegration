package bms.selenium;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;






public class Test_Execution{
	String userDIR = System.getProperty("user.dir");
	String mainPageHandle;
	JavascriptExecutor js;
	  //Initializing the driver
	    WebDriver driver;
	    
	    Properties prop = new Properties();
	    
	    @Parameters({"browser","Path0"})
	    @BeforeTest
	    public void setup(String browser, String Path0) throws Exception{
 
	    driver = WebDriverFactory.setDriver(browser);
         
	   driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	   driver.manage().window().maximize();
	   
	   
	   try{ScreenShotFunctionality.takeSnapShot(driver, userDIR + Path0 );} //Take Screenshot
		catch(ScreenshotException e) {System.out.println("Unable to take Screen Shot");}

	    }
	    
	 
	    @Parameters({"URL","Path1"})
	    @Test(priority = 0)
	    public void openBrowser(String URL, String Path1) throws Exception{
	    	//opening the page 
	    	driver.get(URL);
	    	//getting the page title
	    	String title = driver.getTitle(); 
	    	
	    	String expectedTitle =  "Movie Tickets, Plays, Sports, Events & Cinemas nearby - BookMyShow";
	    	//Checking if the right page opened
	    	Assert.assertEquals(title, expectedTitle); //Checking expected and actual result
	    	//Waiting 
	    	 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    	 
	    	 try{ScreenShotFunctionality.takeSnapShot(driver, userDIR + Path1 );} //Take Screenshot
	 		catch(ScreenshotException e) {System.out.println("Unable to take Screen Shot");}
	    }
	    
	    @Parameters({"Path2","Path3"})
	    @Test(priority = 1)
	    public void signUpNCR(String Path2, String Path3) throws Exception{
	    	
	        mainPageHandle = driver.getWindowHandle();
            
	        try{ScreenShotFunctionality.takeSnapShot(driver, userDIR + Path2 );} //Take Screenshot
	 		catch(ScreenshotException e) {System.out.println("Unable to take Screen Shot");}
	                
	        
            driver.findElement(By.xpath("//button[@class='Sign me Up!']")).click();
            
    
            //select the city as 'NCR'
          driver.findElement(By.xpath("//img[@alt='NCR']")).click();
    //        Thread.sleep(2000);
            
   
          try{ScreenShotFunctionality.takeSnapShot(driver, userDIR + Path3 );} //Take Screenshot
	 		catch(ScreenshotException e) {System.out.println("Unable to take Screen Shot");}
            
	    }
	    
	    @Parameters("Path4")
	    @Test(priority = 2)
	    public void signUp(String Path4) throws Exception{
	    	
	    	 driver.findElement(By.xpath("//div[text()='Sign in']")).click();
	    	 
	    	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    	 
	    	 try{ScreenShotFunctionality.takeSnapShot(driver, userDIR + Path4 );} //Take Screenshot
		 		catch(ScreenshotException e) {System.out.println("Unable to take Screen Shot");}
             
	    }
	    
	    @Parameters("Path5")
	    @Test(priority = 3)
	    public void signUpUsingGoogle(String Path5) throws Exception{
	    	//click on google link
            driver.findElement(By.xpath("//*[@id='modal-root']/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/div")).click();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            
            try{ScreenShotFunctionality.takeSnapShot(driver, userDIR + Path5 );} //Take Screenshot
	 		catch(ScreenshotException e) {System.out.println("Unable to take Screen Shot");}
	    }
	    
	    @Parameters("Path6")
	    @Test(priority = 4)
	    public void switchWindow(String Path6) throws Exception{
	    	 //get all the window links
            Set<String> windowIDs = driver.getWindowHandles();
            
            //get the title of sign in window
            String title = "Sign in – Google accounts";
            String currentWindow = driver.getWindowHandle();
            driver.switchTo().window(currentWindow);
           // System.out.println(windowIDs);
            
            for(String winHandle : windowIDs){
    			
      		  //checking the title of window is equal to "Visit Us" or not 
      		     if (driver.switchTo().window(winHandle).getTitle().equals(title)) {
      		    	 
      		    	 driver.findElement(By.xpath("//*[@id='identifierId']")).sendKeys("srao9152@hds.com");
      		    	 
      		    	 //click on next button
      		    	 driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span")).click(); 
      		    	 
      		    	 //get the error message
      		    	 String errmsg = driver.findElement(By.xpath("//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[2]/div[2]/div")).getText();
      		    	 System.out.println(errmsg);
      		    	 driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      		    	 
      			  
      		    	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      			 	 driver.close();
      			 	 //System.out.println(winHandle);
      			 	 //System.out.println(mainPageHandle);
      			 	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      			 	 driver.switchTo().window(mainPageHandle); //navigate to the main page window
      		     } 
      		     else {
      		        driver.switchTo().window(currentWindow);
      		     } 
      		  }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            //cancel the signin button now
            driver.findElement(By.xpath("//*[@id='modal-root']/div/div/div/div/div[1]/div[3]/div")).click();
          
            try{ScreenShotFunctionality.takeSnapShot(driver, userDIR + Path6 );} //Take Screenshot
	 		catch(ScreenshotException e) {System.out.println("Unable to take Screen Shot");}
            
	    }
	    
	    @Test(priority = 5)
	    public void moviesData() {
         
      // click on movies button
      driver.findElement(By.xpath("//*[@id=\'super-container\']/div[2]/header/div[2]/div/div/div/div[1]/div/a[1]")).click();
        
      //click on upcoming movies
      driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[2]/a/div/div[2]/div/img")).click();
 	    
      //create a list to store the language of movies 
      List<String> list = new ArrayList<String>();
      
      String hindi = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[1]/div/div")).getText();
      list.add(hindi);
      String English = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[2]/div/div/div")).getText();
      list.add(English);
      String Telugu = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[3]/div/div/div")).getText();
      list.add(Telugu);
      String Tamil = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[4]/div/div/div")).getText();
      list.add(Tamil);
      String Malayalam = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[6]/div/div/div")).getText();
      list.add(Malayalam);
      String Marathi = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[7]/div/div/div")).getText();
      list.add(Marathi);
      String Bengali = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[8]/div/div/div")).getText();
      list.add(Bengali);
      String Punjabi = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[9]/div/div/div")).getText();
      list.add(Punjabi);
      String Bhojpuri = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[10]/div/div/div")).getText();
      list.add(Bhojpuri);
      String Gujarati = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[11]/div/div/div")).getText();
      list.add(Gujarati);
      String Assamese = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[12]/div/div/div")).getText();
      list.add(Assamese);
      String Rajasthani = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[13]/div/div/div")).getText();
      list.add(Rajasthani);
      String Sanskrit = driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[2]/div/div[14]/div/div/div")).getText();
      list.add(Sanskrit);
     
      
      System.out.println(list);  
      }
     
	 
	    
	    
	    @Parameters("Path7")
		@Test(priority =6 )
		 public void sports(String Path7) throws Exception{
			//click on sports link
            driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[2]/header/div[2]/div/div/div/div[1]/div/a[5]")).click();
            Thread.sleep(4000);
            
            try{ScreenShotFunctionality.takeSnapShot(driver, userDIR + Path7 );} //Take Screenshot
	 		catch(ScreenshotException e) {System.out.println("Unable to take Screen Shot");}
            
            
          }
	    
	    @Parameters("Path8")
		@Test(priority = 7)
	    public void weekendSelector(String Path8) throws Exception{
	    	//import the javascript executor
            js = (JavascriptExecutor) driver;
            
			//scroll the window so that we can find the this weekend button
            js.executeScript("window.scrollBy(0,200)");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            
            //click on this weekend button
            driver.findElement(By.xpath("//*[@id='super-container']/div[2]/div[4]/div[1]/div[1]/div[2]/div[1]/div[2]/div[3]/div[2]/div/div")).click();
    	     Thread.sleep(4000);
    	     
    	     try{ScreenShotFunctionality.takeSnapShot(driver, userDIR + Path8 );} //Take Screenshot
		 		catch(ScreenshotException e) {System.out.println("Unable to take Screen Shot");}
		}
	    
	    @Parameters("Path9")
		@Test(priority = 8)
	    public void priceSelector(String Path9) throws Exception{
			js.executeScript("window.scrollBy(200,300)");
    	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	    
    	    //click on price button
    	    driver.findElement(By.xpath("//*[@id='super-container']/div[2]/div[4]/div[1]/div[1]/div[2]/div[4]/div/div[1]")).click();
    	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	    
    	    try{ScreenShotFunctionality.takeSnapShot(driver, userDIR + Path9 );} //Take Screenshot
	 		catch(ScreenshotException e) {System.out.println("Unable to take Screen Shot");}
    	    
		}
	    
	    
	    @Parameters("Path10")
		@Test(priority = 9)
	    public void rangeSelector(String Path10) throws Exception{
			js.executeScript("window.scrollBy(200,300)");
    	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	    
			  //click on 0-500 range button
    	    driver.findElement(By.xpath("//*[@id='super-container']/div[2]/div[4]/div[1]/div[1]/div[2]/div[4]/div[2]/div[2]/div[2]/div/div")).click();
    	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	    
    	    try{ScreenShotFunctionality.takeSnapShot(driver, userDIR + Path10 );} //Take Screenshot
	 		catch(ScreenshotException e) {System.out.println("Unable to take Screen Shot");}
    	    
		}
	    
	    
	    
	  /*  @Test(priority = 10)
	    public void saveEventData() throws InterruptedException, IOException{
	    //creating a properties file to store the data related to games
 	     prop = new Properties();
 	    OutputStream writeFile = null;
 	    
        writeFile = new FileOutputStream("gameDetails.properties"); 
        
        //all games under 500
        prop.setProperty("game under 500", "game details");
        
 	    
       // First game details
 	   js.executeScript("window.scrollBy(200,350)");
 	   driver.findElement(By.xpath("//*[@id=\'super-container\']/div[2]/div[4]/div[2]/div[3]/div/div[2]/a[1]/div/div[3]/div[1]/div")).click();
 	   js.executeScript("window.scrollBy(0,200)");
 	  
 	   String gameName = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("first game name", gameName);
 	   System.out.println(gameName);
 	  
 	   String gameDate = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("first game Date", gameDate);
 	  System.out.println(gameDate);
 	  
	   String gamePrice = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("first game price", gamePrice);
	   System.out.println(gamePrice);
	   
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	   //second game details
	   js.executeScript("window.scrollBy(200,350)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[3]/div/div[2]/a[2]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	  
 	   String gameName2 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("Second game name", gameName2);
 	  System.out.println(gameName2);
 	  
 	   String gameDate2 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("Second game Date", gameDate2);
 	  System.out.println(gameDate2);
 	  
	   String gamePrice2 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("Second game price", gamePrice2);
	   System.out.println(gamePrice2);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	 //Third game details
	   js.executeScript("window.scrollBy(200,350)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[3]/div/div[2]/a[3]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	  
 	   String gameName3 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("Third game name", gameName3);
 	  System.out.println(gameName3);
 	  
 	   String gameDate3 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("Third game Date", gameDate3);
 	  System.out.println(gameDate3);
 	  
	   String gamePrice3 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("Third game price", gamePrice3);
	   System.out.println(gamePrice3);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	 //Fourth game details
	   js.executeScript("window.scrollBy(200,350)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[3]/div/div[2]/a[4]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	  
 	   String gameName4 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("Fourth game name", gameName4);
 	  System.out.println(gameName4);
 	  
 	   String gameDate4 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("Fourth game Date", gameDate4);
 	  System.out.println(gameDate4);
 	  
	   String gamePrice4 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("Fourth game price", gamePrice4);
	   System.out.println(gamePrice4);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	   
	   // now scroll further down for next 4 games
	   //js.executeScript("window.scrollBy(350,950)");
	   //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	 //Fifth game details
	   js.executeScript("window.scrollBy(350,950)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[4]/div/div[2]/a[1]/div/div[3]/div[1]/div")).click();
 	  //js.executeScript("window.scrollBy(0,20)");
 	   
 	   String gameName5 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("Fifth game name", gameName5);
 	  System.out.println(gameName5);
 	  
 	   String gameDate5 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("Fifth game Date", gameDate5);
 	  System.out.println(gameDate5);
 	  
	   String gamePrice5 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("Fifth game price", gamePrice5);
	   System.out.println(gamePrice5);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	 //Sixth game details
	   js.executeScript("window.scrollBy(350,950)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[4]/div/div[2]/a[2]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName6 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("Sixth game name", gameName6);
 	  System.out.println(gameName6);
 	  
 	   String gameDate6 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("Sixth game Date", gameDate6);
 	  System.out.println(gameDate6);
 	  
	   String gamePrice6 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("Sixth game price", gamePrice6);
	   System.out.println(gamePrice6);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	 //Seventh game details
	   js.executeScript("window.scrollBy(350,950)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[4]/div/div[2]/a[3]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName7 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("Seventh game name", gameName7);
 	  System.out.println(gameName7);
 	  
 	   String gameDate7 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("Seventh game Date", gameDate7);
 	  System.out.println(gameDate7);
 	  
	   String gamePrice7 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("Seventh game price", gamePrice7);
	   System.out.println(gamePrice7);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	 //8th game details
	   js.executeScript("window.scrollBy(350,950)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[4]/div/div[2]/a[4]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName8 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("8th game name", gameName8);
 	  System.out.println(gameName8);
 	  
 	   String gameDate8 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("8th game Date", gameDate8);
 	  System.out.println(gameDate8);
 	  
	   String gamePrice8 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("8th game price", gamePrice8);
	   System.out.println(gamePrice8);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	  
	 //9th game details
	   js.executeScript("window.scrollBy(750,1450)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[5]/div/div[2]/a[1]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName9 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("9th game name", gameName9);
 	  System.out.println(gameName9);
 	  
 	   String gameDate9 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("9th game Date", gameDate9);
 	  System.out.println(gameDate9);
 	  
	   String gamePrice9 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("9th game price", gamePrice9);
	   System.out.println(gamePrice9);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	 //10th game details
	   js.executeScript("window.scrollBy(750,1450)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[5]/div/div[2]/a[2]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName10 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("10th game name", gameName10);
 	  System.out.println(gameName10);
 	  
 	   String gameDate10 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("10th game Date", gameDate10);
 	  System.out.println(gameDate10);
 	  
	   String gamePrice10 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("10th game price", gamePrice10);
	   System.out.println(gamePrice10);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	 //11th game details
	   js.executeScript("window.scrollBy(750,1450)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[5]/div/div[2]/a[3]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName11 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("10th game name", gameName11);
 	  System.out.println(gameName11);
 	  
 	   String gameDate11 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("11th game Date", gameDate11);
 	  System.out.println(gameDate11);
 	  
	   String gamePrice11 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("11th game price", gamePrice11);
	   System.out.println(gamePrice11);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	   //12th game details
	   js.executeScript("window.scrollBy(750,1450)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[5]/div/div[2]/a[4]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName12 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("12th game name", gameName12);
 	  System.out.println(gameName12);
 	  
 	   String gameDate12 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("12th game Date", gameDate12);
 	  System.out.println(gameDate12);
 	  
	   String gamePrice12 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("12th game price", gamePrice12);
	   System.out.println(gamePrice12);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	   //13th game details
	   js.executeScript("window.scrollBy(1450,2000)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[6]/div/div[2]/a[1]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName13 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("13th game name", gameName13);
 	  System.out.println(gameName13);
 	  
 	   String gameDate13 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("13th game Date", gameDate13);
 	  System.out.println(gameDate13);
 	  
	   String gamePrice13 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("13th game price", gamePrice13);
	   System.out.println(gamePrice13);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	   //14th game details
	   js.executeScript("window.scrollBy(1450,2000)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[6]/div/div[2]/a[2]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName14 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("14th game name", gameName14);
 	  System.out.println(gameName14);
 	  
 	   String gameDate14 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("14th game Date", gameDate14);
 	  System.out.println(gameDate14);
 	  
	   String gamePrice14 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("14th game price", gamePrice14);
	   System.out.println(gamePrice14);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	 //15th game details
	   js.executeScript("window.scrollBy(1450,2000)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[6]/div/div[2]/a[3]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName15 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("15th game name", gameName15);
 	  System.out.println(gameName15);
 	  
 	   String gameDate15 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("15th game Date", gameDate15);
 	  System.out.println(gameDate15);
 	  
	   String gamePrice15 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("15th game price", gamePrice15);
	   System.out.println(gamePrice15);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	 //16th game details
	   js.executeScript("window.scrollBy(1450,2000)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[6]/div/div[2]/a[3]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName16 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("16th game name", gameName16);
 	  System.out.println(gameName16);
 	  
 	   String gameDate16 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("16th game Date", gameDate16);
 	  System.out.println(gameDate16);
 	  
	   String gamePrice16 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("16th game price", gamePrice16);
	   System.out.println(gamePrice16);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	 //17th game details
	   js.executeScript("window.scrollBy(2000,2600)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[7]/div/div[2]/a[1]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName17 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("17th game name", gameName17);
 	  System.out.println(gameName17);
 	  
 	   String gameDate17 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("17th game Date", gameDate17);
 	  System.out.println(gameDate17);
 	  
	   String gamePrice17 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("17th game price", gamePrice17);
	   System.out.println(gamePrice17);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	   //18th game details
	   js.executeScript("window.scrollBy(2000,2600)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[7]/div/div[2]/a[2]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName18 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("18th game name", gameName18);
 	  System.out.println(gameName18);
 	  
 	   String gameDate18 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("18th game Date", gameDate18);
 	  System.out.println(gameDate18);
 	  
	   String gamePrice18 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("18th game price", gamePrice18);
	   System.out.println(gamePrice18);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	 //19th game details
	   js.executeScript("window.scrollBy(2000,2600)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[7]/div/div[2]/a[3]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName19 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("19th game name", gameName19);
 	  System.out.println(gameName19);
 	  
 	   String gameDate19 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("19th game Date", gameDate19);
 	  System.out.println(gameDate19);
 	  
	   String gamePrice19 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("19th game price", gamePrice19);
	   System.out.println(gamePrice19);
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 //20th game details
	   js.executeScript("window.scrollBy(2000,2600)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[7]/div/div[2]/a[4]/div/div[3]/div[1]/div")).click();
 	  js.executeScript("window.scrollBy(0,200)");
 	   
 	   String gameName20 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("19th game name", gameName20);
 	  System.out.println(gameName20);
 	  
 	   String gameDate20 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("20th game Date", gameDate20);
 	  System.out.println(gameDate20);
 	  
	   String gamePrice20 = driver.findElement(By.xpath("//*[@id=\'app\']/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div")).getText();
	   prop.setProperty("20th game price", gamePrice20);
	   System.out.println(gamePrice20);
	   
	   driver.navigate().back(); 
	   
        
      //scroll to the 0-500 range 
      js.executeScript("window.scrollBy(200,300)");
      
      //click on 0-500 button again to deselect the same
      driver.findElement(By.xpath("//*[@id='super-container']/div[2]/div[4]/div[1]/div[1]/div[2]/div[4]/div[2]/div[2]/div[2]/div/div")).click();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	  //click on price button again
	   
	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[1]/div[1]/div[2]/div[4]/div/div[1]")).click();
	   
	   //click on above 2000 range button
	   
	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[1]/div[1]/div[2]/div[4]/div[2]/div[4]/div[2]/div/div")).click();
	   
	   Thread.sleep(7000);
	   
	   
	   // Details of the games which is greater than 2000  
	   
	   
	   
	   // 21th game details
 	   js.executeScript("window.scrollBy(200,350)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[3]/div/div[2]/a[1]/div/div[3]/div[1]/div")).click();
 	   js.executeScript("window.scrollBy(0,200)");
 	  
 	   String gameName21 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("21th game name", gameName21);
 	   System.out.println(gameName21);
 	  
 	   String gameDate21 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("21th game Date", gameDate21);
 	  System.out.println(gameDate21);
 	  
	   String gamePrice21 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div[1]")).getText();
	   prop.setProperty("21th game price", gamePrice21);
	   System.out.println(gamePrice21);
	   
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	// 22th game details
 	   js.executeScript("window.scrollBy(200,350)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[3]/div/div[2]/a[2]/div/div[3]/div[1]/div")).click();
 	   js.executeScript("window.scrollBy(0,200)");
 	  
 	   String gameName22 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("22th game name", gameName22);
 	   System.out.println(gameName22);
 	  
 	   String gameDate22 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("22th game Date", gameDate22);
 	  System.out.println(gameDate22);
 	  
	   String gamePrice22 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div[1]")).getText();
	   prop.setProperty("22th game price", gamePrice22);
	   System.out.println(gamePrice22);
	   
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	// 23th game details
 	   js.executeScript("window.scrollBy(200,350)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[3]/div/div[2]/a[3]/div/div[3]/div[1]/div")).click();
 	   js.executeScript("window.scrollBy(0,200)");
 	  
 	   String gameName23 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("22th game name", gameName23);
 	   System.out.println(gameName23);
 	  
 	   String gameDate23 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("22th game Date", gameDate23);
 	  System.out.println(gameDate23);
 	  
	   String gamePrice23 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div[1]")).getText();
	   prop.setProperty("23th game price", gamePrice23);
	   System.out.println(gamePrice23);
	   
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	// 24th game details
 	   js.executeScript("window.scrollBy(200,350)");
 	   driver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[3]/div/div[2]/a[4]/div/div[3]/div[1]/div")).click();
 	   js.executeScript("window.scrollBy(0,200)");
 	  
 	   String gameName24 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[1]/div[1]/div[1]/h1")).getText();
 	   prop.setProperty("24th game name", gameName24);
 	   System.out.println(gameName24);
 	  
 	   String gameDate24 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[1]/div/div")).getText();
 	   prop.setProperty("24th game Date", gameDate24);
 	  System.out.println(gameDate24);
 	  
	   String gamePrice24 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[2]/div/header/div/div/div[2]/div/div/div[2]/div[3]/div[1]")).getText();
	   prop.setProperty("24th game price", gamePrice24);
	   System.out.println(gamePrice24);
	   
	   
	   driver.navigate().back();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	  prop.store(writeFile, null);
	   writeFile.close();     
	   }*/
          
   
		
	  @AfterTest
		//function to close the driver
		public void closeDriver(){
			driver.quit();
		}
	  
	}


