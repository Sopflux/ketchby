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
}
