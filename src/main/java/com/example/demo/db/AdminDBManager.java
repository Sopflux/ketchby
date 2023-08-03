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

	public static Account findByAid(String aid) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		Account a = null;
		a = session.selectOne("adminAccount.findByAid", aid);
		session.close();
		System.out.println("a: "+a);
		return a;
	}

	public static int deleteAccount(String aid) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = -1;
		re = session.delete("adminAccount.deleteAccount",aid);
		session.close();
		return re;
	}

	public static int updateAccount(Account a) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = -1;
		re = session.update("adminAccount.updateAccount", a);
		session.close();
		return re;
	}
	
	public static int insertAccount(Account a) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = -1;
		re = session.delete("adminAccount.insertAccount", a);
		session.close();
		return re;
	}

}