package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value ="/joinPage")
	public String joinPage() {
		return "join";
	}
	
	@GetMapping(value ="/loginPage")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping(value ="/logout")
	public String logout() {
		return "index";
	}
	
}
