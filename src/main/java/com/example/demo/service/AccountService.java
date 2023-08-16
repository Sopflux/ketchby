package com.example.demo.service;

import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountDAO;
import com.example.demo.dao.AccountDAO_mb;
import com.example.demo.entity.Account;

@Service
public class AccountService implements UserDetailsService{
	@Autowired
	private AccountDAO_mb dao_mb;
	
	@Autowired
	private AccountDAO dao;
	
	public List<Account> findAll(){
		return dao.findAll();
	}
	
	public Account findByEmail(String email) {
		Account a = dao_mb.findByEmail(email);
		return a;
	}
	
	public Account emailCheckWithEmail(String email, String id) {
		Account a = dao_mb.emailCheckWithEmail(email, id);
		return a;
	}
	
	public Account findByAid(String id) {
		return dao_mb.findByAid(id);
	
	}
	
	public String findByNick(String nick) {
		Account a =  dao.findByNick(nick);
		if(a == null) {
			return "";
		}
		return a.getNick();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = null;
		
		//System.out.println(dao.findByAid(username));
		//System.out.println("id"+username);
		
		
		
		Account acc = dao_mb.findByAid(username);
		if (acc == null) {
			try {
				throw new UsernameNotFoundException(username);
			} catch (Exception e) {
				System.out.println("예외발생 :"+e.getMessage());
			}
			}else {
				System.out.println("존재하는 아이디이~~~");
				
				user = User.builder().username(username) // id 설정
						.password(acc.getPwd()) // 비밀번호 설정
						.roles(acc.getRole()+"").build(); 
				System.out.println("user role : "+user.getAuthorities());
			}
		
		return user;
	}

	public void save(Account a) {
		dao.save(a);
		
	}
	public int update(Account a) {
		int r = dao_mb.update(a);
		return r;
	}

}
