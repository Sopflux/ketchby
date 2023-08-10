package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.MainPageDAO;
import com.example.demo.entity.MainPage;
import lombok.Setter;

@Controller
@Setter
public class MainPageController {
	
	@Autowired
	public MainPageDAO mainpagedao;
	
	@GetMapping("/mainpage")
	public String mainPage(Model model){
		
		List<MainPage> recClassList = mainpagedao.findRecClass();
		List<MainPage> hotClassList = mainpagedao.findHotClass();
		List<MainPage> hotClubList = mainpagedao.findHotClub();
		List<MainPage> hotBoardList = mainpagedao.findHotBoard();
		
		model.addAttribute("recClassList", recClassList);
		model.addAttribute("hotClassList", hotClassList);
		model.addAttribute("hotClubList", hotClubList);
		model.addAttribute("hotBoardList", hotBoardList);
		
		return "mainpage";
		
	}
}
