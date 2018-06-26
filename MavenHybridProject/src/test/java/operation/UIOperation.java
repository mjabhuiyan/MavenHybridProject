package operation;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class UIOperation extends AbstractClass{
	
public UIOperation(WebDriver driver){
		
		AbstractClass.driver = driver;
	}
	public void KeyWordperform (Properties p, String keyword, String objectName, String objectType, String data) throws Exception{
	
		switch (keyword.toUpperCase()) {
		
		case "GOTOURL":
			driver.get(p.getProperty(data));
			break;
			
		case "SENDKEYS":
			driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(data);
			break;
			
		case "CLICK":
			driver.findElement(this.getObject(p,objectName,objectType)).click();
			break;
			
		case "SELECT":
			new Select(driver.findElement(this.getObject(p,objectName,objectType))).selectByVisibleText(data);
			break;
		
		case "GETTEXT":
			String actualText=driver.findElement(this.getObject(p,objectName,objectType)).getText();
			System.out.println("Text is : "+actualText);
			break;
			
		case "GETTYPEDTEXT":
			String typedText=driver.findElement(this.getObject(p,objectName,objectType)).getAttribute("value");
			System.out.println("Typed Text Is : "+typedText);
			break;
			
		case "GETTITLE":
			String title=driver.getTitle();
			System.out.println("Title is : "+title);
			break;	
			
		case "GETPOINTOFELEMENT":
			Point point=driver.findElement(this.getObject(p, objectName, objectType)).getLocation();
		    System.out.println("X = "+point.x +" and Y= "+point.y);
			break;
			
		case "IFRAMEHANDLING":
			driver.switchTo().frame(driver.findElement(this.getObject(p, objectName, objectType)));
			break;
			
		case "RETURNTOMAINFRAME":
			driver.switchTo().defaultContent();
			break;
			
		case "GOTOBACKPAGE":
			driver.navigate().back();
			break;
			
		case "GOTOFORWARDPAGE":
			driver.navigate().forward();
			break;
			
		case "PAGERERESH":
			driver.navigate().refresh();
			break;
			
		case "SCREENSHOT":
			File Srcfile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Srcfile, new File(System.getProperty("user.dir")+"\\","\\CaptureShot\\Sreenshot1.jpg"));
			break;
		case "HIGHLIGHT":
			WebElement ele=driver.findElement(this.getObject(p, objectName, objectType));
			JavascriptExecutor js=((JavascriptExecutor)driver);
			js.executeScript("arguments[0].setAttribute('style', 'background: ; border: 2px solid red;');", ele);
			break;
			
		case "SUBMENU":
			WebElement element=driver.findElement(this.getObject(p, objectName, objectType));
			Actions act=new Actions(driver);
			act.moveToElement(element).build().perform();
			break;
			
		case "CHILDWINDOWHANDLE":
			Set<String>Childwindows=driver.getWindowHandles();
			for(String Childwindow:Childwindows){
				driver.switchTo().window(Childwindow);
			}
			break;
			
		case "MAINWINDOWHANDLE" :
			String Mainwindow=driver.getWindowHandle();
			driver.switchTo().window(Mainwindow);
			break;
			
		case "ALERTHANDLING":
			Alert alt=driver.switchTo().alert();
			alt.accept();
			break;
			
		case "IMAGEVERIFY":
			Boolean Image = driver.findElement(this.getObject(p,objectName,objectType)).isDisplayed();
	          if(Image==true){
	               System.out.println("Image is displayed.");
	          }
	          else{
	              System.out.println("Image is not displayed.");
	          }
			break;
			
		case "DRAGANDDROP":
			WebElement source=driver.findElement(this.getObject(p,objectName,objectType));
			WebElement target=driver.findElement(this.getObject(p,objectName,objectType));
			Actions act1=new Actions(driver);
			act1.dragAndDrop(source, target).build().perform();
			System.out.println("Drag and drop performed successfully");
			break;
			
		case "DOUBLECLICK":
			Actions act2=new Actions(driver);
			act2.moveToElement(driver.findElement(this.getObject(p,objectName,objectType))).doubleClick().perform();
			break;
		case "RIGHTCLICK":
			Actions act3=new Actions(driver);
			act3.contextClick(driver.findElement(this.getObject(p,objectName,objectType))).perform();
			break;
		case "FILEUPLOAD":
			driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(data);
			System.out.println("File upload is completed");
			break;
		case "FILEDOWNLOAD":
			FirefoxProfile fp=new FirefoxProfile();
			fp.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			driver.findElement(this.getObject(p,objectName,objectType)).click();
			System.out.println("File download is completed");
			break;
		
		case "LINKCOUNTING":
			List<WebElement> links=driver.findElements(By.tagName("a"));
			
			System.out.println("Number of Total links on the web page is: "+links.size());
			
			System.out.println("Text of the links are : ");
			 
			 for(int i=1; i<links.size(); i=i+1){
				 System.out.println( i+". " +links.get(i).getText());
			 }
			break;
			
		case "SCROLLDOWN":
			JavascriptExecutor jsxDown = (JavascriptExecutor)driver;
			jsxDown.executeScript("window.scrollBy(0,2300)", "");
			break;
			
		case  "SCROLLUP":
			JavascriptExecutor jsxUp = (JavascriptExecutor)driver;
			jsxUp.executeScript("window.scrollBy(0,-2300)", "");
			System.out.println("Scroll up and down is completed ");
			break;
			
		case "THREAD":
			Thread.sleep(3000);
			break;
			
		case "CLOSE":
			driver.close();
			break;
			
		case "QUIT":
			driver.quit();
			break;
		
		default:
			break;
		}
	}
	
	/**
	 * Find element By using object type and value
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObject(Properties p,String objectName,String objectType) throws Exception{
		
		if(objectType.equalsIgnoreCase("ID")){
					
			return By.id(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("XPATH")){
			
			return By.xpath(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("CLASSNAME")){
			
			return By.className(p.getProperty(objectName));	
		}
		
		else if(objectType.equalsIgnoreCase("NAME")){
			
			return By.name(p.getProperty(objectName));	
		}
		
		else if(objectType.equalsIgnoreCase("CSSSELECTOR")){
			
			return By.cssSelector(p.getProperty(objectName));
		}
		
		else if(objectType.equalsIgnoreCase("LINKTEXT")){
			
			return By.linkText(p.getProperty(objectName));
		}
		
		else if(objectType.equalsIgnoreCase("PARTIALLINKTEXT")){
			
			return By.partialLinkText(p.getProperty(objectName));
		}
		
		else if(objectType.equalsIgnoreCase("TAGNAME")){
					
			return By.tagName(p.getProperty(objectName));
		}
		else{
		
			throw new Exception("Wrong object type");
		}
	}


}
