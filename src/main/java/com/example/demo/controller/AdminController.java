package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.ApiBigQueryAuthentication;
import com.example.demo.dao.AccountDAO;

import lombok.Setter;

@Setter
@Controller
public class AdminController {
	@Autowired
	private AccountDAO a_dao;
	
	@GetMapping("/admin/user")
	public void list(Model model){
		model.addAttribute("list",a_dao.findAll());
	}
	
	@GetMapping("/start")
    public String getBigQueryData(Model model) {
		System.out.println("루트 컨트롤러 동작함!");
//        ApiBigQueryAuthentication apiBigQueryAuthentication = new ApiBigQueryAuthentication();
//        try {
//            java.util.List<Map<String, Object>> dataList = apiBigQueryAuthentication.selectBigQuery();
//            model.addAttribute("dataList", dataList);
//        } catch (Exception e) {
//            // Handle any exceptions that may occur during the BigQuery query.
//            // You may want to add error handling logic here.
//            e.printStackTrace();
//        }
        return "index";
    }
}
