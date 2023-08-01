package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.AdminDBManager;
import com.example.demo.entity.Account;

@Repository
public class AdminAccountDAO {
	//전체 레코드 
	public static int userTotalRecord;
	//전체 페이지 
	public static int userTotalPage;
	//한 화면에 보여줄 레코드 수 
	public static int userPageSize = 15;
	
	public List<Account> findAll(HashMap<String, Object> map){
		userTotalRecord = AdminDBManager.getTotalRecord();
		int userTotalForPage = AdminDBManager.getTotalForPage(map);
		userTotalPage = (int)Math.ceil(userTotalRecord/(double)userPageSize);
		if(userTotalRecord != userTotalForPage) {
			userTotalPage = (int)Math.ceil(userTotalForPage/(double)userPageSize);
		}
		return AdminDBManager.findAll(map);
	}
}

