package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		return "/admin/dashboard";
	}
	
	@GetMapping("/admin/class")
	public void listClass (@RequestParam(value="pageNo", defaultValue="1") int pageNo, Model model){
		System.out.println("pageNo:"+ pageNo);
		int start = (pageNo-1)*adminClassDao.pageSize+1;
		int end = start+AdminClassDAO.pageSize-1;
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		model.addAttribute("listClass", adminClassDao.findAll(map));
		model.addAttribute("totalPage", adminClassDao.totalPage);
	}
	
	
}
