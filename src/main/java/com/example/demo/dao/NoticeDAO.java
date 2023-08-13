package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManagerNotice;
import com.example.demo.entity.Notice;

@Repository
public class NoticeDAO {
	public List<Notice> findAll(HashMap<String, Object> map){
		return DBManagerNotice.findAll(map);
	}
	public int getTotalRecord() {
		return DBManagerNotice.getTotalRecord();
	}
	public Notice findByNo(int noticeno) {
		return DBManagerNotice.findByNo(noticeno);
	}
}
