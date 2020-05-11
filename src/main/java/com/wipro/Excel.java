package com.wipro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


@Test
public class Excel {
	
	public void retriveExcel() throws IOException{
		FileInputStream excel = new FileInputStream("OlayUKData.xlsx");
		XSSFWorkbook  book = new XSSFWorkbook(excel);
		XSSFSheet sheet=book.getSheet("UKData");
		
		Map<String, String> map= new HashedMap<>();
        List<String> list = new ArrayList<>();
        list.add("EmailName");
        list.add("Password");
        list.add("BirthDay");
        list.add("FirstName");
        list.add("LastName");
        
		
		
		int r=sheet.getPhysicalNumberOfRows();
		System.out.println("row "+r);
		int noofcell=sheet.getRow(0).getPhysicalNumberOfCells();
		String value=null;
		
		for (int i =1; i<r;i++){
			XSSFRow row=sheet.getRow(i);
			int c=row.getPhysicalNumberOfCells();
			for (int j=0; j<c ;j++)
			{
			XSSFCell cell =row.getCell(j);
			if(cell.getCellTypeEnum()==CellType.STRING){
				 value=cell.getStringCellValue();
				System.out.println(value);
				System.out.println(list.get(j));
				map.put(list.get(j), value);
				
				
			}else{
				Date d=cell.getDateCellValue();
				System.out.println("Not in string format");
				System.out.println(d);
				map.put(list.get(j), value);
			}
		}
		
		 
	}
		
		book.close();

}
}
