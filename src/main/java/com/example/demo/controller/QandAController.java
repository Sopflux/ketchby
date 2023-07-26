package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ApiBigQueryAuthentication;
import com.example.demo.service.QandAService;

import lombok.Setter;

@Controller
@Setter
public class QandAController {
	@Autowired
	private QandAService qs;
	
	@GetMapping("/qanda/qandamain")
	public void list(Model model, int qno) {
		model.addAttribute("list",qs.findByQno(qno));
	}
}
