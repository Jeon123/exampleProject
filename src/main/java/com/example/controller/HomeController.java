package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(value ="/join")
	public String joinPage() {
		return "join";
	}
	
	@GetMapping(value ="/login")
	public String loginPage() {
		return "login";
	}
	
}
