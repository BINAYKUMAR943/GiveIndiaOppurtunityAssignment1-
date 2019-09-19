

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Assignment {

	public static void main(String[] args) throws AWTException, InterruptedException, IOException {
		
		
		System.setProperty("webdriver.chrome.driver","ChromeDriver//chromedriver.exe");
		
    	WebDriver driver = new ChromeDriver();
    	//Step 1 
    	driver.get("https://en.wikipedia.org/wiki/Selenium");
    	driver.manage().window().maximize();
    	String extText=driver.findElement(By.xpath("//span[contains(@class,'head') and contains(text(),'External li')]")).getText();
    	/*Step 2
    	verify External Links Text*/
    	
    	if(extText.equals("External links")){
    		System.out.println("External Link Verfied");
    	}
    	/*Step 3
    	click on oxygen link*/
    	
    	//"C:\Users\binay\workspace\GiveIndiaOppurtunityAssignment\ChromeDriver\chromedriver.exe"
    	driver.findElement(By.xpath("//a/span[text()='O']")).click();
    	
    	//Step4
    	//Verifying that Article is featured or not, by verifying star image in the page.
    	if (driver.findElement(By.xpath("//img[contains(@alt,'This is a featured')]")).isDisplayed()){
    		System.out.println("It is a featured Article");
    	}
    	//Step 5
    	//Screenshot of box containing Properties
    	WebElement elem=driver.findElement(By.xpath("//table[@class='infobox']"));
    	
    	Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
    	BufferedImage img=screenshot.getImage();
        Point p = elem.getLocation();
        int width = elem.getSize().getWidth();
        int height = elem.getSize().getHeight();
        Rectangle rect = new Rectangle(width, height);
        
        //Crop Image of drop down web element from the screen shot
        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
        
        // write cropped image into File Object
        ImageIO.write(dest, "png", new File("Screenshot\\ElementPropertiesScreenShot1.png"));
 
        
       //Step 6
    	//get the List of PDF links in references*/
    	List<WebElement> w;
    	w=driver.findElements(By.xpath("//span[@class='cs1-format']"));
    	System.out.println("Total Number of PDF links-> "+w.size());
    	
    	//Step 7
    	//search Pluto and verify Plutonium
    	driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Pluto");
    	Thread.sleep(1000);
    	if(driver.findElement(By.xpath("//div[text()='nium']")).isDisplayed()){
    		System.out.println("Plutonium is displayed");
    	}
    	
    	
    	
    	
    	
    	
	}
	

}
