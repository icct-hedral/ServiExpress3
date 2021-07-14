package com.empresa.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminvista")
public class AdminController {
	
	@GetMapping
	public String adminVista() {
		return "dashboard/vista_admin";
	}
	
	
}
