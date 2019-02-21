package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.freeboard.FreeBoardDeleteService;
import com.example.service.freeboard.FreeBoardInfoService;
import com.example.service.freeboard.FreeBoardListService;
import com.example.service.freeboard.FreeBoardWriteService;

@Controller
public class FreeBoardController {
	
	@Autowired
	private FreeBoardListService freeBoardListService;
	
	@Autowired
	private FreeBoardWriteService freeBoardWriteService;
	
	@Autowired
	private FreeBoardInfoService freeBoardInfoService;
	
	@Autowired
	private FreeBoardDeleteService freeBoardDeleteService;
	
	private int returnIntValue(String stringToInt) {
		return Integer.parseInt(stringToInt);
	}
	
	@GetMapping(value ="/freeBoardWritePage")
	public String freeBoardWritePage() {
		return "freeBoardWrite";
	}
	
	@GetMapping(value ="/freeBoardUpdatePage")
	public String freeBoardUpdatePage() {
		return "freeBoardUpdate";
	}
	
	@GetMapping("/freeBoard")
	public String freeBoard(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum) {
		
		String page = freeBoardListService.freeBoardList(returnIntValue(pageNum));
		
		return page;
	}
	
	@PostMapping("/freeBoardWriteRequest")
	public String freeBoardWriteRequest(@RequestParam Map<String, String> paramMap) {
		String title = paramMap.get("title");
		String content = paramMap.get("content");
		String writer = paramMap.get("writer");
		
		freeBoardWriteService.write(title, content, writer);
		
		return "redirect:/freeBoard";
	}
	
	@PostMapping("/freeBoardUpdateRequest")
	public String freeBoardUpdateRequest(@RequestParam Map<String, String> paramMap) {
		String freeId = paramMap.get("freeId");
		String title = paramMap.get("title");
		String content = paramMap.get("content");
		String writer = paramMap.get("writer");
		
		freeBoardWriteService.update(freeId, title, content, writer);
		
		return "redirect:/freeBoardInfo?freeId=" + freeId;
	}
	
	@GetMapping("/freeBoardInfo")
	public String getPost(@RequestParam(value = "freeId") String freeId) {
		String page = freeBoardInfoService.getFreeBoardInfo(freeId);
		
		return page;
	}
	
	@GetMapping("/freeBoardDelete")
	public String freeBoardDeleteRequest(@RequestParam(value = "freeId") String freeId) {
		freeBoardDeleteService.delete(freeId);
		
		return "redirect:/freeBoard";
	}
	
	
}
