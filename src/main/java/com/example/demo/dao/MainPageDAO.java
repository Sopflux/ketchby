package com.example.demo.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.MainPageDBManager;
import com.example.demo.entity.MainPage;

@Repository
public class MainPageDAO {

	public List<MainPage> findRecClass() {
		return MainPageDBManager.findRecClass();
	}

	public List<MainPage> findHotClass() {
		return MainPageDBManager.findHotClass();
	}

	public List<MainPage> findHotClub() {
		return MainPageDBManager.findHotClub();
	}

	public List<MainPage> findHotBoard() {
		return MainPageDBManager.findHotBoard();
	}


}