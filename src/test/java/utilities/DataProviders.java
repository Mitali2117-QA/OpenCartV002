package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// DataProvider 1
	
	@DataProvider(name = "LoginData")
	
	public String [][] getData() throws IOException
	
	{
		//String path = "./testData/OpenCart_LoginData.xlsx";
		String path = System.getProperty("user.dir")
		        + "/testData/Opencart_LoginData.xlsx";
		//String sheetName = "Sheet1";
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating object for xlutilities
		
		int totalrows = xlutil.getRowCount("Sheet1");

		int totalcols = xlutil.getCellCount("Sheet1",1);
		totalcols = 3; 
		String logindata [][] = new String[totalrows][totalcols];
		
		for (int i=1;i<=totalrows;i++)
		{
			for(int j=0; j<totalcols;j++) 
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		return logindata;
		
	}
//	System.out.println("Rows = " + totalrows + ", Cols = " + totalcols);


}
