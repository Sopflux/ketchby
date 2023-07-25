package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;

@Controller
@Setter
public class MailController {
	@Autowired
	private MailSender mailSender;
	
	@GetMapping("/admin/sendMail")
	public String mail() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		String data = String.valueOf((int)(Math.random() * 8999) + 1000);
		String a_mail = "";
		mailMessage.setFrom("y48704508@gmail.com");
		mailMessage.setTo(a_mail);
		mailMessage.setSubject("보내주신 문의에 대한 답변");
		mailMessage.setText(data);
		try {
			mailSender.send(mailMessage);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("sendMail error: "+e.getMessage());
		}
		return "admin/question";
	}
}
