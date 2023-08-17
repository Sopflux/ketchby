package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.ClassDetailDAO;
import com.example.demo.entity.Account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ClassDetailController {
	@Autowired
	public ClassDetailDAO dao;
	
	@GetMapping("/classdetail")
	public String list (Model model, int clno, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Account a = (Account)session.getAttribute("a");
		String aid = a.getAid();
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("aid", aid);
		map.put("clno", clno);
		
		int likes = dao.countLike(map);
		double avg = dao.findScore(clno);
		avg = Math.round(avg*100)/100.0;
		double times = dao.totalClassTime(clno);
		int check = dao.checkLike(map);
		
		String clcontent = dao.findAllClass(clno).getClcontent();
		
		model.addAttribute("clcontent", clcontent);		
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
	public int checklikes(HttpServletRequest request, HttpSession session) {
		int clno = Integer.parseInt(request.getParameter("clno"));
		
		Account a = (Account)session.getAttribute("a");
		String aid = a.getAid();
	
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