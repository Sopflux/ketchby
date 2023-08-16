package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.QandADAO;
import com.example.demo.entity.QandA;

import autovalue.shaded.com.google.common.base.Optional;
import lombok.Setter;

@Controller
@Setter
public class QandAController {
	@Autowired
	private QandADAO dao;

	
	@GetMapping("/qanda/qandamain")
	public void list() {}
	
	@GetMapping("/qanda/qandamain/ajax")
	@ResponseBody
	public List<QandA> findList(int qno) {
	    List<QandA> list = null;
	    if (qno == 0) {
	        list = dao.findAll();
	    } else {
	        list = dao.findByQno(qno);
	    }
	    return list;
	}
}
