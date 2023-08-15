package com.example.demo.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.AccountDAO_mb;

import lombok.Setter;

@Controller
@Setter
public class HtmlMailController {
	@Autowired
	private AccountDAO_mb dao_mb;
	
	@Autowired
	private JavaMailSender sender;
	
	@GetMapping("/ajax/emailCheck")
	@ResponseBody
	public String send(String email) {

		Random r = new Random();
		int a = r.nextInt(10); //0~9
		int b = r.nextInt(10); //0~9
		int c = r.nextInt(10); //0~9
		int d = r.nextInt(10); //0~9
		
		String n = a+""+b+""+c+""+d+"";
		sender.send(new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				String data = "<h1>해당 인증번호를 입력해 주세요!</h1>";
				data += "<hr>"
						+ "<h2>"
				 +a+""+b+""+c+""+d+"</h2>";
				
				MimeMessageHelper message =
					new MimeMessageHelper(mimeMessage, "utf-8");
				message.setFrom("qlqlql8448@gmail.com");
				message.setTo(email);
				message.setSubject("Ketchby 이메일 인증");
				message.setText(data, true);				
			}
		});
		
		return n;
	}
}






