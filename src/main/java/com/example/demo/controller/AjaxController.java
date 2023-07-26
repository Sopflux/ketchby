package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AccountService;

import lombok.Setter;
@Setter
@RestController
public class AjaxController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AccountService as;
	
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
