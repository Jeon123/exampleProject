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
import com.example.service.freeboard.FreeBoardInfoService;
import com.example.service.freeboard.FreeBoardListService;
import com.example.service.freeboard.FreeBoardSearch;
import com.example.service.freeboard.FreeBoardService;

@Controller
public class FreeBoardController {
	
	@Autowired
	private FreeBoardListService freeBoardListService;
	
	@Autowired
	private FreeBoardInfoService freeBoardInfoService;
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	@Autowired
	private FreeBoardSearch freeBoardSearch;
	
	private int returnIntValue(String stringToInt) {
		return Integer.parseInt(stringToInt);
	}
	
	@GetMapping(value ="/boardWrite")
	public String freeBoardWritePage() {
		return "freeBoardWrite";
	}
	
	@GetMapping("/boardUpdate/{freeId}")
	public String freeBoardUpdatePage(Model model, @PathVariable(value = "freeId") String freeId) {
		model.addAttribute("boardInfo", freeBoardInfoService.getFreeBoardInfo(freeId));
		return "freeBoardUpdate";
	}
	
	@GetMapping("/")
	public String freeBoard(Model model, @RequestParam(value = "pageNum", defaultValue = "1") String pageNum) {
		FreeBoardSearchVo fbsv = freeBoardListService.freeBoardList(returnIntValue(pageNum));
		
		model.addAttribute("freeBoard", fbsv.getFreeBoardList());
		model.addAttribute("pageMaker", fbsv.getPageMaker());
		//model.addAttribute("category", fbsv.getCategory());
		
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
		model.addAttribute("boardInfo", freeBoardInfoService.getFreeBoardInfo(boardNum));
		
		return "freeBoardInfo";
	}
	
	@GetMapping("/boardDelete/{freeId}")
	public String freeBoardDeleteRequest(@PathVariable(value = "freeId") String freeId) {
		freeBoardService.delete(freeId);
		
		return "redirect:/";
	}
	
	@GetMapping("/{category}/{searchtext}")
	public String freeBoardSearchRequest(
			Model model, 
			@PathVariable(value = "category") String category, 
			@PathVariable(value = "searchtext") String searchText, 
			@RequestParam(value = "pageNum", defaultValue = "1") String pageNum) {
		
		FreeBoardSearchVo fbsv = freeBoardSearch.freeBoardList(category, searchText, returnIntValue(pageNum));
		
		model.addAttribute("freeBoard", fbsv.getFreeBoardList());
		model.addAttribute("pageMaker", fbsv.getPageMaker());
		model.addAttribute("searchText", searchText);
		
		return "/search";
	}
}
