package operation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class AbstractClass {
	protected static WebDriver driver;
	@BeforeTest
	public void SetUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\IT\\AllDriver\\chromedriver.exe");
    	driver = new ChromeDriver();
    	//driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

}
