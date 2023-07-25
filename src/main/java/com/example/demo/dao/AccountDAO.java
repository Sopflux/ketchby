package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Account;

public interface AccountDAO extends JpaRepository<Account, Integer> {
	public Account findByAid(String id);
	public String findOneByNick(String nick);
}
