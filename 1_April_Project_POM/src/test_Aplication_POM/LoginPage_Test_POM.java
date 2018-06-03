package test_Aplication_POM;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import baseTest_POM.BaseTest;
import excel_Util_POM.ExcelReader_Test;
import page_POM.LoginPage;


public class LoginPage_Test_POM extends BaseTest{
	public LoginPage login;
	public static final Logger log=Logger.getLogger(LoginPage_Test_POM.class.getName());
    public String TC="Registration";
	@Test(dataProvider="getTestData")
	public void loginTest_Care(Hashtable<String,String> tb){
		log.info("Login Test -- Login Test -- Login Test -- Login Test");
		test=ext.startTest("Care Login Test Start");
		openBro(pro.getProperty("Browser"));
		navigate("appurl");
		screenshot();
		login=new LoginPage(dr,test);			
		login.doLogin(tb.get("user"), tb.get("Password"));
		test.log(LogStatus.PASS,"Login Test Done");
		log.info("Login Test successfully Done -- Login Test successfully Done -- Login Test successfully Done.");
	}
	
	@AfterTest
	public void tearDown(){
		ext.endTest(test);
		ext.flush();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return ExcelReader_Test.getExcelTestData(xls, TC);
	}
	
}
