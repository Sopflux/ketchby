package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QandADAO;
import com.example.demo.entity.QandA;

import lombok.Setter;

@Service
@Setter
public class QandAService {
	@Autowired
	private QandADAO dao;
	
	public List<QandA> findAll(){
		return dao.findAll();
	}
}
