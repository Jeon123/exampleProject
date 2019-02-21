package com.example.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.JoinService;
import com.example.service.LoginService;

@Controller
public class UsersController {
	
	@Autowired
	private JoinService joinService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private HttpSession session;

	@PostMapping(value = "/joinRequest")
	public String joinRequest(@RequestParam Map<String, String> paramMap) {
		String userId = paramMap.get("user_id");
		String userPw = paramMap.get("user_pw");
		String userName = paramMap.get("user_name");
		
		String page = joinService.joinUser(userId, userPw, userName);
		
		
		return page;
	}
	
	@PostMapping(value = "/loginRequest")
	public String loginRequest(@RequestParam Map<String, String> paramMap) {
		String userId = paramMap.get("user_id");
		String userPw = paramMap.get("user_pw");
		
		String page = loginService.login(userId, userPw);
		
		return page;
	}
	
	@GetMapping(value ="/logout")
	public String logout() {
		
		session.removeAttribute("loginUser");
		
		return "redirect:/";
	}
	
	
}
