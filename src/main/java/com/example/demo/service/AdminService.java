package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClassDAO;

import lombok.Setter;
import com.example.demo.entity.Class;

@Service
@Setter
public class AdminService {
	
	@Autowired
	private ClassDAO classDao;

	public List<Class> findAll() {
		return classDao.selectAll();
	}
}
