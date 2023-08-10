package com.example.demo.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.AdminQnaDAO;
import com.example.demo.entity.AdminQnainsertVO;
import com.example.demo.entity.QnainsertVO;

@Controller
public class AdminQnAController {
	@Autowired
	public AdminQnaDAO q_dao;
	
	@Autowired
	private MailSender mailSender;
	
	@GetMapping("/admin/qna")
	public String qna(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum 
		, @RequestParam(required = false, defaultValue = "all")String column
		, @RequestParam(required = false, defaultValue = "") String keyword
		, @RequestParam(required = false, defaultValue="all") String condition) {
		System.out.println("condition: "+condition);
			int start = (pageNum-1) * q_dao.qnaPageSize-1;
			int end = start + q_dao.qnaPageSize-1;
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("column", column);
			map.put("keyword", keyword);
			
			int totalPage = q_dao.qnaTotalPage;
			if(totalPage == 0) {
				totalPage = 1;
			}
			
			if(condition.equals("none")) {
				condition = null;
			}
			model.addAttribute("column", column);
			model.addAttribute("keyword", keyword);
			model.addAttribute("condition", condition);
			model.addAttribute("qlist", q_dao.findAll(map));
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("qnainsert", q_dao.qnaTotalRecord);
			return "/admin/qna";
	}
	
	@RequestMapping("/admin/qna/detail")
	@ResponseBody
	public AdminQnainsertVO qnaInsert(int qino) {
		AdminQnainsertVO q = q_dao.findByQino(qino);
		return q;
	}
	
	@RequestMapping("/admin/qna/mail")
	@ResponseBody
	public String qnaMail(int qino, String email, String mailtitle, String mailcontent) {
		String str = "메일전송에 성공하였습니다.";
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("ketchby0821@gmail.com");
		mailMessage.setTo(email);
		mailMessage.setSubject(mailtitle);
		mailMessage.setText(mailcontent);
		try {
			mailSender.send(mailMessage);
			q_dao.updateCondition(qino);
		} catch (Exception e) {
			// TODO: handle exception
			str = "메일전송에 실패하였습니다.";
			System.out.println("send mail error: "+e.getMessage());
		}
		return str;
	}
	
}
