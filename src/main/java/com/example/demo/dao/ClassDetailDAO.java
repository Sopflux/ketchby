package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.ClassDetailDBManager;
import com.example.demo.entity.Account;
import com.example.demo.entity.ClassDetailVO;
import com.example.demo.entity.ClassTimeVO;
import com.example.demo.entity.ReviewInfoVO;

@Repository
public class ClassDetailDAO {
	public ClassDetailVO findAllClass(int clno) {
		return ClassDetailDBManager.findAllClass(clno);
	}
	
	public Account findAccountInfo(int clno) {
		return ClassDetailDBManager.findAccountInfo(clno);
	}
	
	public double totalClassTime(int clno) {
		return ClassDetailDBManager.totalClassTime(clno);
	}
	
	public List<ClassTimeVO> findClassTime(int clno) {
		return ClassDetailDBManager.findClassTime(clno);
	}
	
	public double findScore(int clno) {
		return ClassDetailDBManager.findScore(clno);
	}
	
	public List<ReviewInfoVO> findReview(int clno) {
		return ClassDetailDBManager.findReview(clno);
	}
	
	public int deleteLike(HashMap<String, Object> map) {
		return ClassDetailDBManager.deleteLike(map);
	}
	
	public int countLike(HashMap<String, Object> map) {
		return ClassDetailDBManager.countLike(map);
	}
	
	public int insertLike(HashMap<String, Object> map) {
		return ClassDetailDBManager.insertLike(map);
	}
	
	public int checkLike(HashMap<String, Object> map) {
		return ClassDetailDBManager.checkLike(map);
	}
}
