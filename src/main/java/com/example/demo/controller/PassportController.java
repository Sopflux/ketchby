package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PassportController {
	@GetMapping("/main")
	public String mainpage() {
		return "main.html";
	}
}
