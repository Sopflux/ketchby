package com.example.demo.controller;

import java.util.Random;

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
	
	// 4자리 난수를 발행하여 메일로 전송 해 봅니다.
		@GetMapping("/emailCheck2")
		public String emailCheck(String email) {

			SimpleMailMessage mailMessage = 
					new SimpleMailMessage();
			
			Random r = new Random();
			int a = r.nextInt(10); //0~9
			int b = r.nextInt(10); //0~9
			int c = r.nextInt(10); //0~9
			int d = r.nextInt(10); //0~9
			
			String data = "<h1>해당 인증번호를 입력해 주세요!</h1>";
			data += "<hr>"
					+ "<h2>"
			 +a+""+b+""+c+""+d+"</h2>";
			
			
			mailMessage.setFrom("qlqlql8448@gmail.com");
			mailMessage.setTo(email);
			mailMessage.setSubject("Ketchby 이메일 인증");
			mailMessage.setText(data);
			
			try {
				mailSender.send(mailMessage);
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
			
			return "OK";
		}
	
	@GetMapping("/admin/")
	public String mail() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		String r = String.valueOf((int)(Math.random() * 8999) + 1000);
		String a_mail = "";
		mailMessage.setFrom("y48704508@gmail.com");
		mailMessage.setTo(a_mail);
		mailMessage.setSubject("보내주신 문의에 대한 답변");
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("send mail error: "+e.getMessage());
		}
		return "OK";
	}
	
	
	
	
}
