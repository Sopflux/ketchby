package com.example.demo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ApiBigQueryAuthentication;

import lombok.Setter;

@Controller
@Setter
public class QandAController {
	@GetMapping("/qanda/qandamain")
    public void ret() {
      
    }
}
