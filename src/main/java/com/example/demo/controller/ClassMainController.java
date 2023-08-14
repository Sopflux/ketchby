package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.ClassMainDAO;
import com.example.demo.dao.MainPageDAO;
import com.example.demo.entity.MainPage;
import lombok.Setter;

@Controller
@Setter
public class ClassMainController {
	
	@Autowired
	public ClassMainDAO classmaindao;
	
	@GetMapping("/classmain")
	public void classMain(
		@RequestParam(name = "bcaname", required=false) String bcaname,	Model model){
		System.out.println("bcaname:"+bcaname);
		model.addAttribute("classMain", classmaindao.findAllClass(bcaname));
	}
	
}
