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
		userTotalRecord = AdminDBManager.getTotalRecordAccount();
		
		//검색 결과에 해당하는 레코드 갯수 가져오기
		int userTotalForPage = AdminDBManager.getTotalForPageAccount(map);
		
		//페이지 갯수 구하기
		userTotalPage = (int)Math.ceil(userTotalForPage/(double)userPageSize);
		return AdminDBManager.findAllAccount(map);
	}

	public Account findByAid(String aid) {
		// TODO Auto-generated method stub
		return AdminDBManager.findByAid(aid);
	}

	//계정 삭제
	public int deleteAccount(String aid) {
		// TODO Auto-generated method stub
		return AdminDBManager.deleteAccount(aid);
	}
	
	//계정 수정
	public int updateAccount(Account a) {
		return AdminDBManager.updateAccount(a);
	}
	
	//계정 추가
	public int insertAccount(Account a) {
		return AdminDBManager.insertAccount(a);
	}
}