package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;

import com.example.demo.entity.Account;



public class DBManager {
	
	public static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static Account findByNick(String nick) {
		SqlSession session =  sqlSessionFactory.openSession();
		Account a = null;
		a = session.selectOne("account.findByNick", nick);
		return a;
	}
	
	public static Account emailCheckWithEmail(String email, String id) {
		SqlSession session =  sqlSessionFactory.openSession();
		Account a = null;
		HashMap<String, String> params = new HashMap<>();
	    params.put("email", email);
	    params.put("id", id);
	    System.out.println("DBManager id:"+id);
	    System.out.println("DBManager email:"+email);
		a = session.selectOne("account.emailCheckWithEmail",params);
		return a;
	}
	
	public static Account findByEmail(String email) {
		SqlSession session =  sqlSessionFactory.openSession();
		Account a = null;
		a = session.selectOne("account.findByEmail", email);
		return a;
	}

	
}