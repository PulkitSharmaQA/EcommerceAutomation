package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestData {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("D:\\eclipse-java-2024-09-R-win32-x86_64\\eclipse\\karate-final\\SalesForce\\EcommerceAutomation\\TestData\\Data.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		workbook.getSheet("Data");
	}

}
