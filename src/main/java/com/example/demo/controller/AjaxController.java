package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;

import lombok.Setter;
@Setter
@RestController
public class AjaxController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AccountService as; 
	
	@GetMapping("/duplicateEmail")
	public String duplicateEmail(String email) {
		System.out.println("duplicate Check email : "+email);
		if(as.findByEmail(email)==null) {
			return "";
		}
		return as.findByEmail(email).getEmail();
	}
	
	@GetMapping("/emailCheckWithEmail")
	public String emailCheckWithEmail(String email, String id) {
		Account a = as.emailCheckWithEmail(email, id);
		if (a == null) {
			return "";
		}
		return a.getEmail();
	}

	@GetMapping("/idCheck")
	public String idCHeck(String id) {
		if (as.findByAid(id) != null) {
			return as.findByAid(id).getAid();
		}
		return "";
	}
	
	@GetMapping("/getID")
	public String getID(String email) {
		return as.findByEmail(email).getAid();
	}
	
	
	@GetMapping("/nickCheck")
	public String nickCheck(String nick) {
		String msg = "사용 가능한 닉네임입니다!";
		String check = as.findByNick(nick);
		if (check != null && !check.equals("")) {
			msg = "사용불가능한 닉네임입니다!";
		}
		System.out.println("msg:" + msg);
		return msg;
	}

}
