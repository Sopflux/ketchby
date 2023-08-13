package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.AdminDBManager;
import com.example.demo.entity.NoticeVO;

@Repository
public class AdminNoticeDAO {
	//전체 레코드 
	public static int noticeTotalRecord;
	//전체 페이지 
	public static int noticeTotalPage;
	//한 화면에 보여줄 레코드 수 
	public static int noticePageSize = 15;
	
	public List<NoticeVO> findAll(HashMap<String, Object> map){
		noticeTotalRecord = AdminDBManager.getTotalNotice();
		String keyword = (String)map.get("keyword");
		int noticeTotalForPage = AdminDBManager.getTotalForPageNotice(keyword);
		noticeTotalPage = (int)Math.ceil(noticeTotalForPage/(double)noticePageSize);
		return AdminDBManager.findAllNotice(map);
	}
	
	public NoticeVO findNotice(int noticeno) {
		return AdminDBManager.findNotice(noticeno);
	}
	
	public int deleteNotice(int noticeno) {
		return AdminDBManager.deleteNotice(noticeno);
	}
	
	public int updateNotice(NoticeVO n) {
		return AdminDBManager.updateNotice(n);
	}
	
	public int insertNotice(NoticeVO n) {
		return AdminDBManager.insertNotice(n);
	}
}
