package com.qiaoyu.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
	
	 public static Workbook createWorkBook(InputStream is, String excelFileName)
	            throws IOException {
	        if (excelFileName.toLowerCase().endsWith("xls")) {
	            return new HSSFWorkbook(is);
	        }
	        if (excelFileName.toLowerCase().endsWith("xlsx")) {
	            return new XSSFWorkbook(is);
	        }
	        return null;
	    }

	    public static List<List<Object>> readExcel(String basePath, String fileName)
	            throws FileNotFoundException, IOException {
	        File file = new File(basePath+fileName);
	        List<List<Object>> list = new ArrayList<List<Object>>(); 
	        Workbook book = createWorkBook(new FileInputStream(file), fileName);
	        Sheet sheet = book.getSheetAt(0);
	        Cell cell = null; 
	        
	        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i);
	            
	            if(row==null||row.getFirstCellNum()==i)
	            {
	            	continue;
	            } 
	            
	            List<Object> li = new ArrayList<Object>(); 
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {  
                    cell = row.getCell(j);  
                    li.add(getCellValue(cell));  
                }
                
                list.add(li);
	        }
	        return list;
	    }
	    
	    public static Object getCellValue(Cell cell){  
	        Object value = null;  
	        DecimalFormat df = new DecimalFormat("0");  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); 
	        DecimalFormat df2 = new DecimalFormat("0.00");
	          
	        switch (cell.getCellType()) {  
	        case Cell.CELL_TYPE_STRING:  
	            value = cell.getRichStringCellValue().getString();  
	            break;  
	        case Cell.CELL_TYPE_NUMERIC:  
	            if("General".equals(cell.getCellStyle().getDataFormatString())){  
	                value = df.format(cell.getNumericCellValue());  
	            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
	                value = sdf.format(cell.getDateCellValue());  
	            }else{  
	                value = df2.format(cell.getNumericCellValue());  
	            }  
	            break;  
	        case Cell.CELL_TYPE_BOOLEAN:  
	            value = cell.getBooleanCellValue();  
	            break;  
	        case Cell.CELL_TYPE_BLANK:  
	            value = "";  
	            break;  
	        default:  
	            break;  
	        }  
	        return value;  
	    }
	    
	    
	    public static void writeExcel(OutputStream out, List<String> header, List<Object> cdata) throws IOException
		{
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet=workbook.createSheet("QuotesByCustomer");

	        HSSFFont font_h = workbook.createFont();
	        font_h.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        HSSFCellStyle cellStyle= workbook.createCellStyle();
	        cellStyle.setFont(font_h);            

	        HSSFRow row = sheet.createRow(0);
	        for(int i=0;i<header.size();i++){
	            HSSFCell cell = row.createCell(i);
	            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	            cell.setCellStyle(cellStyle);
	            cell.setCellValue((String)header.get(i));
	        }

	        for (int i=0;i<cdata.size();i++){
	            HSSFRow row1 = sheet.createRow(i+1);
	            ArrayList rdata =(ArrayList)cdata.get(i);
	            for (int j=0;j<rdata.size();j++){
	               HSSFCell cell = row1.createCell(j);
	               cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	               Object o = rdata.get(j);
	               if(o instanceof BigDecimal){
	                    BigDecimal b=(BigDecimal)o;
	                    cell.setCellValue(b.doubleValue());
	                }
	                else if(o instanceof Integer){
	                       Integer it =(Integer)o;
	                       cell.setCellValue(it.intValue());
	                       
	                }
	                else if(o instanceof Long){
	                    Long l =(Long)o;
	                    cell.setCellValue(l.intValue());
	                    
	                }
	                else if(o instanceof Double){
	                    Double d =(Double)o;
	                    cell.setCellValue(d.doubleValue());
	                }
	                else if(o instanceof Float){
	                    Float f = (Float)o;
	                    cell.setCellValue(f.floatValue());
	                }
	                else{
	                    cell.setCellValue(o+"");
	                }             
	            }
	          }
	        workbook.write(out); 
		}
	    

}
