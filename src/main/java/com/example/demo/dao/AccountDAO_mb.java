package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.entity.Accclub;
import com.example.demo.entity.Account;
import com.example.demo.entity.Follow;
import com.example.demo.entity.Review;

@Repository
public class AccountDAO_mb {
	public Account findByNick(String nick) {
		return DBManager.findByNick(nick);
	}
	public Account findByEmail(String email) {
		return DBManager.findByEmail(email);
	}
	public Account emailCheckWithEmail(String email,String id) {
		return DBManager.emailCheckWithEmail(email, id);
	}
	public Account findByAid(String id) {
		return DBManager.findByAid(id);
	}
	public List<Follow> findFollow(String aid) {
		return DBManager.findFollow(aid);
	}
	public List<Follow> findFollowing(String aid) {
		return DBManager.findFollowing(aid);
	}
	public int update(Account a) {
		return DBManager.update(a);
		
	}
	public boolean checkFollow(HashMap<String, String> map) {
		return DBManager.checkFollow(map);
	}
	public List<com.example.demo.entity.Class> findOpenClass(String aid) {
		return DBManager.findOpenClass(aid);
	}
	public void unfollow(HashMap<String, String> map) {
		  DBManager.unfollow(map);
		
	}
	public void follow(HashMap<String, String> map) {
		DBManager.follow(map);
	}
	public List<Accclub> findOpenClub(String aid) {
		return DBManager.findOpenClub(aid);
	}
	public List<Review> findAReview(String aid) {
		return DBManager.findAReview(aid);
	}
}
