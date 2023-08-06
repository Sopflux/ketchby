package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.db.AdminDashBoardDBManager;
import com.example.demo.entity.AdminDashBoard;

@Repository
public class AdminDashBoardDAO {

	public int findTotalUsers() {
		return AdminDashBoardDBManager.findTotalUsers();
	}


	public List<AdminDashBoard> findDailyUsers(HashMap<String, Object> map) {
		return AdminDashBoardDBManager.findDailyUsers(map);
	}


	public List<AdminDashBoard> findClassByBca() {
		return AdminDashBoardDBManager.findClassByBca();
	}
}
