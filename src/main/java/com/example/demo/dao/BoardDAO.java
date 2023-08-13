package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManagerBoard;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comments;

@Repository
public class BoardDAO {
    public List<Board> findAll(HashMap<String, Object> map) {
        return DBManagerBoard.findAll(map);
    }
	
	public int getTotalRecord(HashMap<String, Object> map2) {
		return DBManagerBoard.getTotalRecord(map2);
	}
	
	public int getNextNo() {
		return DBManagerBoard.getNextNo();
	}
	public int getNextCommentsNo() {
		return DBManagerBoard.getNextCommentsNo();
	}
	
	public int insert(Board b) {
		return DBManagerBoard.insert(b);
	}
	public int insertComments(Comments c) {
		return DBManagerBoard.insertComments(c);
	}
	
	public int deleteComments(int cbno) {
		return DBManagerBoard.deleteComments(cbno);
	}
	
	public Board findByNo(int bno) {
		return DBManagerBoard.findByNo(bno);
	}
	public List<Comments> findByComments(int bno){
		return DBManagerBoard.findByComments(bno);
	}
	public int delete(int bno) {
		return DBManagerBoard.delete(bno);
	}
	public int update(Board b) {
		return DBManagerBoard.update(b);
	}
}
