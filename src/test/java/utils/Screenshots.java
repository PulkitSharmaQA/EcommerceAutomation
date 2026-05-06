package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;

import com.google.common.io.Files;

public class Screenshots  implements ITestListener {
	WebDriver driver;
	
	public static String screenShots(WebDriver driver , String testName) {
		TakesScreenshot sc = (TakesScreenshot)driver;
		
		File src = sc.getScreenshotAs(OutputType.FILE);
		
		File path =  new File(System.getProperty("user.dir") 
				+ File.separator + "screenshots"  );
		
		String fullpath = path.getAbsolutePath() 
				+ File.separator + System.currentTimeMillis() +".jpeg";
		
		try {
			Files.copy(src, new File(fullpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fullpath;
	}


}
