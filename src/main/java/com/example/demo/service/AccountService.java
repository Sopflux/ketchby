package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountDAO;
import com.example.demo.entity.Account;

@Service
public class AccountService implements UserDetailsService{
	
	@Autowired
	private AccountDAO dao;
	
	public List<Account> findAll(){
		return dao.findAll();
	}
	
	public String findOneByNick(String nick) {
		return dao.findOneByNick(nick);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = null;
		
		System.out.println(dao.findByAid(username));
		System.out.println("id"+username);
		
		
		
		Account acc = dao.findByAid(username);
		if (acc == null) {
			try {
				throw new UsernameNotFoundException(username);
			} catch (Exception e) {
				System.out.println("예외발생 :"+e.getMessage());
			}
			}else {
				user = User.builder().username(username) // id 설정
						.password(acc.getPwd()) // 비밀번호 설정
						.roles("user").build(); 
			}
		
		return user;
	}

	public void save(Account a) {
		dao.save(a);
		
	}
}
