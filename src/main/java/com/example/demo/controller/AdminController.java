package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.AccountDAO;

import lombok.Setter;

@Setter
@Controller
public class AdminController {
	@Autowired
	private AccountDAO a_dao;
	
	@GetMapping("/admin/user")
	public void list(Model model){
		model.addAttribute("list",a_dao.findAll());
	}
}
