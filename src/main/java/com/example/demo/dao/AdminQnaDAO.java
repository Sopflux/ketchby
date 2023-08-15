package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.db.AdminDBManager;
import com.example.demo.entity.AdminQnainsertVO;
import com.example.demo.entity.QnainsertVO;

@Repository
public class AdminQnaDAO {
	//전체 레코드
	public static int qnaTotalRecord;
	//전체 페이지
	public static int qnaTotalPage;
	//한 화면에 보여줄 레코드 수
	public static int qnaPageSize = 15;
	
	public List<AdminQnainsertVO> findAll(HashMap<String, Object>map) {
		qnaTotalRecord = AdminDBManager.getTotalRecordQna();
		
		System.out.println("dao에 있는 맵: "+map);
		
		//검색 결과에 해당하는 레코드 갯수
		int qnaTotalForPage = AdminDBManager.getTotalForPageQna(map);
		
		//페이지 갯수
		qnaTotalPage = (int)Math.ceil(qnaTotalForPage/(double)qnaPageSize);
		return AdminDBManager.findAllQna(map);
	}
	
	public AdminQnainsertVO findByQino(int qino) {
		return AdminDBManager.findByQino(qino);
	}
	
	public int updateCondition(int qino) {
		return AdminDBManager.updateCondition(qino);
	}
	
}