package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.FreeBoard;
import com.example.model.FreeBoardSearchVo;
import com.example.service.freeboard.CommonService;
import com.example.service.freeboard.FreeBoardService;

@Controller
public class FreeBoardController {
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	@Autowired
	private CommonService commonService;
	
	private int returnIntValue(String stringToInt) {
		return Integer.parseInt(stringToInt);
	}
	
	private Long returnLongValue(String stringToLong) {
		return Long.parseLong(stringToLong);
	}
	
	@GetMapping(value ="/boardWrite")
	public String freeBoardWritePage() {
		return "freeBoardWrite";
	}
	
	@GetMapping("/boardUpdate/{freeId}")
	public String freeBoardUpdatePage(Model model, @PathVariable(value = "freeId") String freeId) {
		model.addAttribute("boardInfo", freeBoardService.getFreeBoardInfo(freeId));
		return "freeBoardUpdate";
	}
	
	@GetMapping("/")
	public String freeBoard(Model model, @RequestParam(value = "pageNum", defaultValue = "1") String pageNum) {
		FreeBoardSearchVo fbsv = freeBoardService.freeBoardList(returnIntValue(pageNum));
		commonService.getsideBarInfo();
		
		model.addAttribute("freeBoard", fbsv.getFreeBoardList());
		model.addAttribute("pageMaker", fbsv.getPageMaker());
		
		return "freeBoard";
	}
	
	@ResponseBody
	@PostMapping("/boardWriteRequest")
	public FreeBoard freeBoardWriteRequest(@RequestBody FreeBoard freeBoard) {
		
		return freeBoardService.write(freeBoard);
	}

	@ResponseBody
	@PostMapping("/boardUpdateRequest")
	public FreeBoard freeBoardUpdateRequest(@RequestBody FreeBoard freeBoard) {
		
		return freeBoardService.update(freeBoard);
	}
	
	@GetMapping("/{boardNum}")
	public String getPost(Model model, @PathVariable(value = "boardNum") String boardNum) {
		model.addAttribute("boardInfo", freeBoardService.getFreeBoardInfo(boardNum));
		
		return "freeBoardInfo";
	}
	
	@GetMapping("/boardDelete/{freeId}")
	public String freeBoardDeleteRequest(@PathVariable(value = "freeId") String freeId) {
		freeBoardService.delete(returnLongValue(freeId));
		
		return "redirect:/";
	}
	
	@GetMapping("/search/{searchtext}")
	public String freeBoardSearchRequest(
			Model model, 
			@PathVariable(value = "searchtext") String searchText, 
			@RequestParam(value = "pageNum", defaultValue = "1") String pageNum) {
		
		FreeBoardSearchVo fbsv = freeBoardService.freeBoardSearchList(searchText, returnIntValue(pageNum));
		
		model.addAttribute("freeBoard", fbsv.getFreeBoardList());
		model.addAttribute("pageMaker", fbsv.getPageMaker());
		model.addAttribute("searchText", searchText);
		
		return "freeBoard";
	}
	
	@GetMapping("/category/{searchtext}")
	public String freeBoardCategoryRequest(
			Model model, 
			@PathVariable(value = "searchtext") String searchText, 
			@RequestParam(value = "pageNum", defaultValue = "1") String pageNum) {
		
		FreeBoardSearchVo fbsv = freeBoardService.freeBoardCategoryList(searchText, returnIntValue(pageNum));
		
		model.addAttribute("freeBoard", fbsv.getFreeBoardList());
		model.addAttribute("pageMaker", fbsv.getPageMaker());
		model.addAttribute("searchText", searchText);
		
		return "freeBoard";
	}
	
}
