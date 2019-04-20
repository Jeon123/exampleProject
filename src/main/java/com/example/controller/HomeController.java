package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.freeboard.CommonService;

@Controller
public class HomeController {
	
	@Autowired
	private CommonService commonService;
	
	@GetMapping(value ="/join")
	public String joinPage() {
		return "join";
	}
	
	@GetMapping(value ="/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping(value ="/123")
	public void sideBar() {
		commonService.getsideBarInfo();
	}
}
