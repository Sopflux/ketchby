package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.dao.AdminClassDAO;
import com.example.demo.entity.AdminClass;
import com.google.api.client.json.Json;

import lombok.Setter;

@Setter
@Controller
public class AdminController {
	
	@Autowired
	private AdminClassDAO adminClassDao;
	
	@GetMapping("/admin")
	public String admin() {
		return "/admin/main";
	}
	
	//Ajax 통신으로 호출되어 응답 responsebody
	@GetMapping("/admin/class")
	public void listClass (Model model){
		System.out.println("게시물 목록 컨트롤러 동작함");
		model.addAttribute("listClass", adminClassDao.findAll());
	}
	
	
}
