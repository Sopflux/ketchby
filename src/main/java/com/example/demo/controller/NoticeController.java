package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.NoticeDAO;

import lombok.Setter;

@Controller
@Setter
public class NoticeController {
	public int pageSIZE = 20;
	public int totalRecord;
	public int totalPage;
	
	@Autowired
	private NoticeDAO noticedao;
	
	@GetMapping("/notice/noticemain/{pageNum}")
	public String list(Model model, @PathVariable("pageNum") int pageNum) {
		
		totalRecord = noticedao.getTotalRecord();
		totalPage = (int) Math.ceil(totalRecord / (double) pageSIZE);
		
		int start = (pageNum - 1) * pageSIZE + 1;
		int end = start + pageSIZE - 1;
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);

		model.addAttribute("list", noticedao.findAll(map));
		model.addAttribute("totalPage", totalPage);
		
		return "/notice/noticemain";
	}
	
	@GetMapping("/notice/notice_detail/{noticeno}")
	public String detail(@PathVariable("noticeno") int noticeno, Model model) {
		model.addAttribute("n", noticedao.findByNo(noticeno));
		return "/notice/notice_detail";
	}
}
