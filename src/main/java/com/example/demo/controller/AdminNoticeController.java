package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.AdminNoticeDAO;
import com.example.demo.entity.Notice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class AdminNoticeController {
	@Autowired
	public AdminNoticeDAO dao;
	
	@PostMapping("/admin/notice/insert")
	@ResponseBody
	public String insertNotice(HttpServletRequest request) {
		String str = "추가 성공했습니다.";
		
		String noticetitle = request.getParameter("noticetitle");
		String noticecontent = request.getParameter("noticecontent");
		Notice n = new Notice();
		
		n.setNoticetitle(noticetitle);
		n.setNoticecontent(noticecontent);
		
		int re = dao.insertNotice(n);
		if(re != 1) {
			str = "추가 실패했습니다.";
		}
		return str;
	}
	
	@PostMapping("/admin/notice/update")
	@ResponseBody
	public String updateNotice(HttpServletRequest request) {
		String str = "수정 성공했습니다.";
		
		Notice n = dao.findNotice(Integer.parseInt(request.getParameter("noticeno")));
		String noticetitle = request.getParameter("noticetitle");
		String noticecontent = request.getParameter("noticecontent");
		
		n.setNoticetitle(noticetitle);
		n.setNoticecontent(noticecontent);
		
		int re = dao.updateNotice(n);
		if(re != 1) {
			str = "수정 실패했습니다.";
		}
		return str;
	}
	
	@PostMapping("/admin/notice/delete")
	@ResponseBody
	public String deleteNotice(@RequestParam(value = "noticeno")int noticeno, HttpServletRequest request) {
		String str = "삭제 성공했습니다.";
		System.out.println("noticeno: "+noticeno);
		int re = dao.deleteNotice(noticeno);
		if(re != 1) {
			str = "삭제 실패하였습니다.";
		}
		return str;
	}
	
	@RequestMapping("/admin/notice/detail")
	@ResponseBody
	public Notice detailNotice(@RequestParam(value = "noticeno")int noticeno) {
		Notice n = dao.findNotice(noticeno);
		System.out.println(n);
		return n;
	}
	
	@GetMapping("/admin/notice")
	public String list(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum
			, @RequestParam(required = false, defaultValue = "")String keyword) {
		int start = (pageNum-1)*dao.noticePageSize+1;
		int end = start + dao.noticePageSize-1;
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("keyword", keyword);
		map.put("pageNum", pageNum);
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("list", dao.findAll(map));
		model.addAttribute("totalPage", dao.noticeTotalPage);
		model.addAttribute("notice", dao.noticeTotalRecord);
		return "/admin/notice";
	}
}