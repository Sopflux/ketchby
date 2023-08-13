package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.ClassDetailDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ClassDetailController {
	@Autowired
	public ClassDetailDAO dao;
	
	@GetMapping("/classdetail")
	public String list (Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		//int clno = (int)session.getAttribute("clno");
		//String aid = (String)session.getAttribute("aid");
		
		int clno = 5001;
		String aid = "qlqlql8448";
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("aid", aid);
		map.put("clno", clno);
		
		int likes = dao.countLike(map);
		double avg = dao.findScore(clno);
		avg = Math.round(avg*100)/100.0;
		double times = dao.totalClassTime(clno);
		int check = dao.checkLike(map);
		
		model.addAttribute("check", check);
		model.addAttribute("r_list", dao.findReview(clno));
		model.addAttribute("cl_list", dao.findAllClass(clno));
		model.addAttribute("t_list", dao.findClassTime(clno));
		model.addAttribute("a_info", dao.findAccountInfo(clno));
		model.addAttribute("clno", clno);
		model.addAttribute("likes", likes);
		model.addAttribute("avg", avg);
		model.addAttribute("times", times);
		model.addAttribute("aid", aid);
		
		return "/classdetail";
	}
	
	@GetMapping("/classdetail/checklike")
	@ResponseBody
	public int checklikes(HttpServletRequest request) {
		int clno = Integer.parseInt(request.getParameter("clno"));
		String aid = (String)request.getParameter("aid");
		System.out.println("clno: " +clno);
		System.out.println("aid: "+aid);
		HashMap<String, Object> map = new HashMap<>();
		map.put("clno", clno);
		map.put("aid", aid);
		int check = dao.checkLike(map);
		
		if(check==1){
			check = dao.deleteLike(map);
		}else{
			check = dao.insertLike(map);
		}
		
		int likes = dao.countLike(map);
		return likes;
	}
}