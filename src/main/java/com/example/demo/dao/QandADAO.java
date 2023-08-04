package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.db.DBManagerQandA;
import com.example.demo.entity.QandA;

@Repository
public class QandADAO {
	public List<QandA> findAll(){
		return DBManagerQandA.findAll();
	}
	
	public List<QandA> findByQno(int qno){
		return DBManagerQandA.findByQno(qno);
	}
}
