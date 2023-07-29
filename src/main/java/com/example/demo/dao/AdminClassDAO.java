package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.AdminClassDBManager;
import com.example.demo.entity.AdminClass;

@Repository
public class AdminClassDAO {
	
	/*
	 * 페이징 처리를 위한 요소
	 * pageSize = 한화면에 보여줄 레코드 수
	 * totalRecord = 전체 레코드의 수
	 * totalPage = 전체 페이지 수
	 * */
	public static int pageSize=10;
	public static int totalRecord;
	public static int totalPage;
	
	// 클래스 전체 목록 조회
	public List<AdminClass> findAll(HashMap<String, Object> map) {
		totalRecord = AdminClassDBManager.getTotalRecord();
		totalPage = (int)Math.ceil(totalRecord/(double)pageSize);
		
		return AdminClassDBManager.findAll(map);
	}

}
