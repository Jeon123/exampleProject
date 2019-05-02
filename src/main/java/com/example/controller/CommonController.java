package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.common.ExcelDownload;
import com.example.service.freeboard.CommonService;

@Controller
public class CommonController {
	
	@Autowired
	private CommonService commonService;
	
	// Excel Download
	@GetMapping("/download/excel/{defaultName}")
	public <T> void freeBoardExcelDown(HttpServletResponse response, @PathVariable(value="defaultName") String defaultName) throws Exception {
		List<T> list = commonService.getFindAll(defaultName);
		
		String fileName =  defaultName + "List";
		String sheetName = defaultName + "Sheet";
		
		ExcelDownload.excelDownload(fileName, sheetName, list, response);
	}
}
