package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configReader {

	Properties prop = new Properties();

	public void readFile() throws IOException {
		
		
		/*String FilePath = System.getProperty("user.dir")
				+ File.separator + "config"
				+File.separator + "config/properties";*/
		 InputStream input =
	                getClass()
	                .getClassLoader()
	                .getResourceAsStream("config/config.properties");
		
		//FileInputStream fis = new FileInputStream(FilePath);
		
	
		
		prop.load(input);
		

		}
	
	public  String getBrowser() {
		return prop.getProperty("browser").trim();
	}
	public String getUrl() {
		return prop.getProperty("url").trim();
	}

}
