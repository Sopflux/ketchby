package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.FeedImgDAO_mb;

import lombok.Setter;

@Setter
@Controller
public class FeedController {
	
	@Autowired
	private FeedImgDAO_mb dao;

	@GetMapping("/feed/feed")
	public void main(Model model){
		model.addAttribute("list", dao.findAllFeedIMg());
	}
	
}

