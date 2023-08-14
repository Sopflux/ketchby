package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Account;

public interface AccountDAO extends JpaRepository<Account, Integer> {
	public Optional<Account> findByAid(String id);
	public Account findByNick(String nick);
	public Optional<Account> findByEmail(String email);
}
