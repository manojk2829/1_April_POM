package baseTest_POM;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import excel_Util_POM.Xls_Reader;
import extent_Report_POM.ExtentReport_POM;

public class BaseTest {
	
	public WebDriver dr;
	public Properties pro;
	public Logger log=Logger.getLogger(BaseTest.class.getName());
	public ExtentReports ext=ExtentReport_POM.getReporting_POM();
	public ExtentTest test;
	public Xls_Reader xls;
	
	public BaseTest(){
		init();
		xls=new Xls_Reader(pro.getProperty("ExcelPath"));
	    PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\property_POM\\log4j_POM.properties");
	  }
	
	
	public BaseTest(WebDriver dr,ExtentTest test){
        this.dr=dr;
        this.test=test;
	}	
	
     public void init(){
		if(pro==null){
			pro=new Properties();
			try{FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\property_POM\\or.properties");
			pro.load(fis);}
			catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		System.out.println(pro.getProperty("Browser"));

	}
	
	public void openBro(String b){
		if(b.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", pro.getProperty("Browser_exe"));
			dr=new ChromeDriver();
		}else{
			dr=new FirefoxDriver();
		}
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "Browser Open " + pro.getProperty(b));
	}
	
	public void navigate(String url){
		dr.get(pro.getProperty(url));
		log.info("URL entered in Browser -- > " + pro.getProperty(url));
		test.log(LogStatus.INFO, "URL entered in Browser " + pro.getProperty(url));
		System.out.println(pro.getProperty("appurl"));
	}
	
	public void screenshot(){
		Date d=new Date();
		String FN=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		File srcFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"report"+FN));
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		log.info("Screen shot capture done.");
		test.log(LogStatus.INFO, "Screenshot taken -- > " + test.addScreenCapture(System.getProperty("user.dir")+"report"+FN));
		System.out.println("Screen shot Done .....");
	}
}
