package testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import operation.AbstractClass;
import operation.ReadObject;
import operation.UIOperation;

public class HybridTestCases extends AbstractClass {
	@Test(dataProvider="hybridData")
	public void UnderTest(String testcaseID, String testcaseName, String keyword, String objectName, String objectType, String data) throws Exception {
        
	    /*if(testcaseName!=null && testcaseName.length()!=0){
	    	//System.setProperty("webdriver.chrome.driver", "E:\\All Driver\\chromedriver.exe");
	    	//driver = new ChromeDriver();
	    	driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    }*/
		ReadObject object = new ReadObject();
		Properties allObjects = object.getObjectRepository();
		UIOperation operation = new UIOperation(driver);   
		operation.KeyWordperform(allObjects, keyword, objectName, objectType, data);
	     
	}
	@DataProvider(name="hybridData")
	public Object[][] getDataFromDataprovider() throws IOException{
	    Object[][] object = null;
	    
	    File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData","TestCaseData.xlsx");
	    
	    FileInputStream fis=new FileInputStream(file);
	    
		Workbook wb =  new XSSFWorkbook(fis);
	    Sheet ws = wb.getSheet("Data1");
	    
	    int rowCount = ws.getLastRowNum()- ws.getFirstRowNum();
	    object = new Object[rowCount][6];
	    for (int i = 0; i <rowCount; i++) {
	        
	        Row row = ws.getRow(i+1);
	        
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	            
	            object[i][j] = row.getCell(j).toString();
	        }
	   }
	    	
	    	return object;    
    }
		

}
