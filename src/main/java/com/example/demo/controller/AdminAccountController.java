package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.AdminAccountDAO;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class AdminAccountController {
	@Autowired
	public AdminAccountDAO a_dao;
	
	
	@GetMapping("/admin/user")
	public String list(Model model, @RequestParam(required=false, defaultValue = "1") int pageNum
			, @RequestParam(required = false, defaultValue = "all") String column
			, @RequestParam(required = false, defaultValue = "") String keyword) {
		
		int start = (pageNum-1)*a_dao.userPageSize+1;
		int end = start + a_dao.userPageSize-1;
		
		System.out.println("controller-----------------------------------");
		System.out.println("pageNum: "+pageNum);
		System.out.println("column: "+column);
		System.out.println("keyword: "+keyword);
		System.out.println("start: "+start);
		System.out.println("end: "+end);
		
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("column", column);
		map.put("keyword", keyword);
		map.put("pageNum", pageNum);
		
		model.addAttribute("column", column);
		model.addAttribute("keyword", keyword);
		model.addAttribute("list", a_dao.findAll(map));
		model.addAttribute("totalPage", a_dao.userTotalPage);
		model.addAttribute("member", a_dao.userTotalRecord);
		return "/admin/user";
	}
	
}
