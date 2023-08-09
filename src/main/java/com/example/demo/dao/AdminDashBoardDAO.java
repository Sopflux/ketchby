package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.db.AdminDashBoardDBManager;
import com.example.demo.entity.AdminDashBoard;

@Repository
public class AdminDashBoardDAO {

	public Object findTodayUsers() {
		return AdminDashBoardDBManager.findTodayUsers();
	}

	public Object findYesterdayUsers() {
		return AdminDashBoardDBManager.findYesterdayUsers();
	}

	public Object findTodayClass() {
		return AdminDashBoardDBManager.findTodayClass();
	}


	public Object findYesterdayClass() {
		return AdminDashBoardDBManager.findYesterdayClass();
	}

	public Object findTodayPayment() {
		return AdminDashBoardDBManager.findTodayPayment();
	}

	public Object findYesterdayPayment() {
		return AdminDashBoardDBManager.findYesterdayPayment();
	}

	public Object findTodayQuit() {
		return AdminDashBoardDBManager.findTodayQuit();
	}

	public Object findYesterdayQuit() {
		return AdminDashBoardDBManager.findYesterdayQuit();
	}
	
	public List<AdminDashBoard> findDailyUsers(HashMap<String, Object> map) {
		return AdminDashBoardDBManager.findDailyUsers(map);
	}

	public List<AdminDashBoard> findLevelByUser() {
		return AdminDashBoardDBManager.findLevelByUser();
	}


	public List<AdminDashBoard> findClassByBca() {
		return AdminDashBoardDBManager.findClassByBca();
	}


	public List<AdminDashBoard> findDailyClass(HashMap<String, Object> map) {
		return AdminDashBoardDBManager.findDailyClass(map);
	}


	public List<AdminDashBoard> findDailyPayment(HashMap<String, Object> map) {
		return AdminDashBoardDBManager.findDailyPayment(map);
	}

	public List<AdminDashBoard> findQuitReason() {
		return AdminDashBoardDBManager.findQuitReason();
	}

	
	
}
	