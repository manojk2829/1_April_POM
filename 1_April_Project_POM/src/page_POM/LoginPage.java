package page_POM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import baseTest_POM.BaseTest;

public class LoginPage extends BaseTest{
	
    @FindBy(name="txtUserName")
	WebElement userLogin;
	
	@FindBy(name="txtPassword")
	WebElement Pass;
	
	@FindBy(xpath="//input[@value='Log In']")
	WebElement loginBTN;

	public LoginPage(WebDriver dr,ExtentTest test){
        super(dr,test);
		PageFactory.initElements(dr, this);
	}

	public void doLogin(String u,String p){
		userLogin.sendKeys(u);
		Pass.sendKeys(p);
		log.info("Entered user name and Password : ");
		screenshot();
		Pass.sendKeys(Keys.ENTER);		
		test.log(LogStatus.INFO,"User Name and Password entered successfully... " + u + " -- "+ p);
	}
}
