package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManagerBoardHot;
import com.example.demo.entity.BoardHot;

@Repository
public class BoardHotDAO {
	public List<BoardHot> findHotAll(){
		return DBManagerBoardHot.findHotAll();
	}
}
