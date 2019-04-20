package com.example.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelDownload {
	
	@SuppressWarnings("rawtypes")
	public static <T> void excelDownload(String fileName, String sheetName, List<T> list, HttpServletResponse response) throws Exception {
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet(sheetName);
		Row headRow = null;
		Row valueRow = null;
		Cell headCell = null;
		Cell valueCell = null;
		int rowNo = 1;
		
		CellStyle headStyle = wb.createCellStyle();
		
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		
		headStyle.setAlignment(HorizontalAlignment.LEFT);
		
		CellStyle bodyStyle = wb.createCellStyle();
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		
		// Convert List to Map
		List<Map<String, Object>> lists = convertListToMap(list);
		headRow = sheet.createRow(rowNo-1);
		
		for(Map<String, Object> fb : lists) {
			int i = 0;
			valueRow = sheet.createRow(rowNo++);
			Set set = fb.entrySet();

			Iterator iterator = set.iterator();

			while(iterator.hasNext()){
			  Entry entry = (Entry)iterator.next();
			  if(headRow.getRowNum()==0) {
				  headCell = headRow.createCell(i);
				  headCell.setCellStyle(headStyle);
				  headCell.setCellValue((String)entry.getKey());
			  }

			  valueCell = valueRow.createCell(i++); 
			  valueCell.setCellStyle(bodyStyle);
			  valueCell.setCellValue(entry.getValue().toString());
			}
		}
		
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename="+ fileName +".xls");
		
		wb.write(response.getOutputStream());
		wb.close();
	}
	
	// Convert List to Map
	public static <T> List<Map<String, Object>> convertListToMap(Collection<T> target) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (T element : target) {
			Map<String,Object> resultMap = new HashMap<String,Object>();
			Field[] fieldList = element.getClass().getDeclaredFields();
			if (fieldList != null && fieldList.length > 0) {
				try {
					for (int i = 0; i < fieldList.length; i++) {
						String curInsName = fieldList[i].getName();
						Field field = element.getClass().getDeclaredField(curInsName);
						field.setAccessible(true);
						Object targetValue = field.get(element);
						resultMap.put(curInsName, targetValue);
					}
					resultList.add(resultMap);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultList;
	}
}
