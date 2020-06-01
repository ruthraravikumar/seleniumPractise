package com.wipro;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel {
	
	public static Logger log= Logger.getLogger(Excel.class.getName());
	
	public Map<String, String> retriveExcel() throws IOException{
		FileInputStream excel = new FileInputStream("OlayUKData.xlsx");
		XSSFWorkbook  book = new XSSFWorkbook(excel);
		XSSFSheet sheet=book.getSheet("UKData");
		
		Map<String, String> map= new HashedMap<>();
        /*List<String> list = new ArrayList<>();
        list.add("EmailName");
        list.add("Password");
        list.add("BirthDay");
        list.add("FirstName");
        list.add("LastName");*/
        
		
		
		int r=sheet.getPhysicalNumberOfRows();
		log.info("row "+r);
		int noofcell=sheet.getRow(0).getPhysicalNumberOfCells();
		String value=null;
		
		for (int i =1; i<r;i++){
			XSSFRow headerRow=sheet.getRow(0);
			XSSFRow row=sheet.getRow(i);
			int c=row.getPhysicalNumberOfCells();
			for (int j=0; j<c ;j++)
			{
			XSSFCell headerCell=headerRow.getCell(j);
			XSSFCell cell =row.getCell(j);
			
			if(cell.getCellTypeEnum()==CellType.STRING){
				 
				value=cell.getStringCellValue();
				log.info(headerCell.getStringCellValue());
				
				log.info(value);
				
				map.put(headerCell.getStringCellValue(), value);
				
				
			}else{
				Date date=cell.getDateCellValue();
				
				log.info("Not in string format");
				log.info(date);
				log.info(headerCell.getStringCellValue());
				DateFormat dateFormat= new SimpleDateFormat("yyyy-M-dd");
				String strDate=dateFormat.format(date);
				log.info(strDate);
				map.put(headerCell.getStringCellValue(), strDate);
			}
		}
		
			log.info("size"+map.size());
	}
		
		book.close();
		
return map;
}
}
