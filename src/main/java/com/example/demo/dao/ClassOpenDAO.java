package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.entity.TimeVO;
import com.example.demo.entity.ClassVO;
import com.example.demo.entity.ScategoryVO;

@Repository
public class ClassOpenDAO {
	public int insertClass(ClassVO c) {
		return DBManager.insertClass(c);
	}
	
	public int insertClassTime(TimeVO c) {
		return DBManager.insertClassTime(c);
	}
	
	public List<ScategoryVO> findScaName(String bcaname){
		return DBManager.findScaName(bcaname);
	}

	public int findScaNo(String scaname) {
		// TODO Auto-generated method stub
		return DBManager.findScaNo(scaname);
	}
}
