package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.Account;

public class AdminDBManager {
	
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
	
	public static List<Account> findAll(HashMap<String, Object> map) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Account> list = null;
		System.out.println("manager에서 map확인 "+map);
		list = session.selectList("adminAccount.findAll", map);
		session.close();
		return list;
	}
	
	public static int getTotalRecord() {
		// TODO Auto-generated method stub
		int n = 0;
		SqlSession session = sqlSessionFactory.openSession();
		n = session.selectOne("adminAccount.getTotalUser");
		session.close();
		return n;
	}

	public static Account findAccount(String aid) {
		SqlSession session = sqlSessionFactory.openSession();
		Account a = null;
		a = session.selectOne("adminAccount.findAccount", aid);
		session.close();
		return a;
	}
	
	public static int getTotalForPage(HashMap<String, Object> map) {
		SqlSession session = sqlSessionFactory.openSession();
		int m = 0;
		m = session.selectOne("adminAccount.getTotalForPage", map);
		session.close();
		System.out.println("검색어 : "+m);
		return m;
	}

}
