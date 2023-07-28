package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.AdminClassDBManager;
import com.example.demo.entity.AdminClass;

@Repository
public class AdminClassDAO {

	public List<AdminClass> findAll() {
		return AdminClassDBManager.findAll();
	}

}
