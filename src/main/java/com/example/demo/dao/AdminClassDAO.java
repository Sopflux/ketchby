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
	public static int pageSize=15;
	public static int totalRecord;
	public static int totalPage;
	
	// 클래스 전체 목록 조회
	public List<AdminClass> findAll(String mapperId, HashMap<String, Object> map) {
		
		totalRecord = AdminClassDBManager.getTotalRecord(mapperId);
		totalPage = (int)Math.ceil(totalRecord/(double)pageSize);
		
		return AdminClassDBManager.findAll(mapperId, map);
	}

	// 클래스 단일 목록 조회
	public AdminClass findByClno(String mapperId, int clno) {
		return AdminClassDBManager.findByClno(mapperId, clno);
	}

	// 클래스 삭제
	public int delete(String mapperId, int clno) {
		return AdminClassDBManager.delete(mapperId, clno);
	}

	// 클래스 업데이트
	public int update(HashMap<String, Object> map) {
		return AdminClassDBManager.update(map);
	}

	
}
