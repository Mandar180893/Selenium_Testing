package Ajio;

import static org.testng.Assert.assertTrue;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ArticlePageNavigation {
	public static void main(String args[]) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

        System.setProperty("webdriver.chrome.driver","C:\\\\Users\\\\MANDAR\\\\eclipse-workspace\\\\selenium_project\\\\chromedriver.exe");
		WebDriver driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		driver.navigate().to("https://www.ajio.com");  
	    
		WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/header/div[3]/div[2]/form/div/div/input"));
		search.sendKeys("NIKE Revolution 5 Lace-Up Sports Shoes");
		  
		WebElement searchbutton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/header/div[3]/div[2]/form/div/button/span"));
		searchbutton.click();
		
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,800);");
		//Thread.sleep(5000);

		WebElement image = driver.findElement(By.xpath("//div[contains(@class,'469038187001')]//img[contains(@src,'20211117')]"));
		  image.click();
		
		ArrayList<String> nTabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("no of Tabs " + nTabs);
		driver.switchTo().window(nTabs.get(1));
		
		//For MRP
		WebElement mrpElement = driver.findElement(By.className("prod-cp"));
		System.out.println("mrpElement is "+ mrpElement);
		String txt = mrpElement.getText();
		System.out.println("Original price is: " + txt);
		assertTrue(txt.equalsIgnoreCase("₹3,695"));
		
		//For Deal Price
		WebElement rate = driver.findElement(By.className("prod-sp"));
		String disrate = rate.getText(); 
		System.out.println("Discounted price is: " + disrate);
		assertTrue(disrate.equalsIgnoreCase("₹2,845"));
		
		/*For Description
		WebElement about = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div[3]/div/section/h3"));
		String text = about.getText();
		System.out.println("Tag is " + text);
		assertTrue(text.equalsIgnoreCase("Product Details"));*/
		
		//Select size = new Select(driver.findElement(By.xpath("//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[6]/div[2]/button[2]")));
		//size.selectByValue("9");
		
		String size = "8";
		while(true) 
		{
			String S = driver.findElement(By.xpath("//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[6]/div[2]/div/div/div[6]/div/span")).getText();
			System.out.println(S);
			
			if(S.equals(size)) {
				break;
			}
			else {
			driver.findElement(By.xpath("//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[6]/div[2]/button[2]")).click();
			
			}
		driver.findElement(By.xpath("//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[6]/div[2]/div/div/div[6]/div/span")).click();
		}
		//driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[6]/div[1]/table/thead/tr[1]/th[3]/i")).click();
	
		//For Add to bag button
		WebElement bagbtn = driver.findElement(By.xpath("//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[9]/div[1]/div[1]/div/span[2]"));
		System.out.println("Add to bag button is "+ bagbtn);
		bagbtn.click();
		assertTrue(bagbtn.isEnabled());
		
		//Actions act = new Actions(driver);
		WebElement cartbtn = driver.findElement(By.xpath("//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[9]/div[1]/div[1]/a/div/span[2]"));
		System.out.println("Go to bag button is "+ cartbtn);
		//act.doubleClick(cartbtn).perform();
		cartbtn.click();
		assertTrue(cartbtn.isEnabled());
		
		
		

		driver.quit();
	}
}


