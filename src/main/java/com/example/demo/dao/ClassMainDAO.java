package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.ClassMainDBManager;
import com.example.demo.entity.MainPage;

@Repository
public class ClassMainDAO {

	public List<MainPage> findAllClass(String bcaname) {
		return ClassMainDBManager.findAllClass(bcaname);
	}


}
	