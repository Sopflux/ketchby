package com.example.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.Setter;

@Controller
@Setter
public class MypageMailController {
	@Autowired
	private MailSender mailSender;
	
	
		@PostMapping("/mypage/mail")
		public String MailCommunication(String femail, String name, String email, String subject, String message) {

			SimpleMailMessage mailMessage = 
					new SimpleMailMessage();
			
			String data = name + "님께서 문의하신 내용입니다.\n\n"
		            + "제목: " + subject + "\n"
		            + "내용: " + message + "\n"
		            + "답변하시고자 한다면 " + email + "로 연락주십시오";
		
			
			
			mailMessage.setFrom("ketchby0821@gmail.com");
			mailMessage.setTo(femail);
			mailMessage.setSubject("Ketchby에서 발송된 문의 메일입니다.");
			mailMessage.setText(data);
			
			try {
				mailSender.send(mailMessage);
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
			
			
			return "/feed/feed";
		}
	
	    @GetMapping("/mypage/mail")
	    public void Mail() {
	    	
	    }
	
	
	
	
}
