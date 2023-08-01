package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.db.AdminDashBoardDBManager;
import com.example.demo.entity.AdminDashBoard;

@Repository
public class AdminDashBoardDAO {
	
	public Integer getTotalUsers() {
        return AdminDashBoardDBManager.getTotalUsers();
    }

    public List<AdminDashBoard> getDailyUsers() {
        return AdminDashBoardDBManager.getDailyUsers();
    }
}
