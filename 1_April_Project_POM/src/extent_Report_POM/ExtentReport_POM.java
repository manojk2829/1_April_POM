package extent_Report_POM;

import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReport_POM {
	public static ExtentReports ext;
	
	public static ExtentReports getReporting_POM(){
		if(ext==null){
			Date d=new Date();
			String FN=d.toString().replace(" ", "_").replace(":", "_")+".html";
			ext=new ExtentReports("D://report//"+FN,true,DisplayOrder.NEWEST_FIRST);
			ext.addSystemInfo("QA Test Environment", "172.19.70.194");
		}
		return ext;
	}
}
