package com.mmit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

	@GetMapping("/403")
	public String showAccessDeniedPage()
	{
		return "403";
	}
	
	@GetMapping("/login")
	public String showLoginForm()
	{
		return "login";
	}
}
