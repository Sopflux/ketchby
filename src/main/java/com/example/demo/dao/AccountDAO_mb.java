package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.entity.Account;

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
	public int findFollow(String email) {
		return DBManager.findFollow(email);
	}
	public int findFollowing(String email) {
		return DBManager.findFollowing(email);
	}
	public int update(Account a) {
		return DBManager.update(a);
		
	}
}
