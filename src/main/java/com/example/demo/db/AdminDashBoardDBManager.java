package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.AdminClass;
import com.example.demo.entity.AdminDashBoard;

public class AdminDashBoardDBManager {
	
	public static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			inputStream.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
			
		}
	}

	public static Object findTodayUsers() {
		int n =0;
		SqlSession session = sqlSessionFactory.openSession();
		n=session.selectOne("adminDashBoard.findTodayUsers");
		session.close();
		return n;
	}

	public static Object findYesterdayUsers() {
		int n =0;
		SqlSession session = sqlSessionFactory.openSession();
		n=session.selectOne("adminDashBoard.findYesterdayUsers");
		session.close();
		return n;
	}
	
	public static int findTodayClass() {
		int n =0;
		SqlSession session = sqlSessionFactory.openSession();
		n=session.selectOne("adminDashBoard.findTodayClass");
		session.close();
		return n;
	}
	public static int findYesterdayClass() {
		int n =0;
		SqlSession session = sqlSessionFactory.openSession();
		n=session.selectOne("adminDashBoard.findYesterdayClass");
		session.close();
		return n;
	}
	
	
	public static Object findTodayPayment() {
		int n =0;
		SqlSession session = sqlSessionFactory.openSession();
		n=session.selectOne("adminDashBoard.findTodayPayment");
		session.close();
		return n;
	}

	public static Object findYesterdayPayment() {
		int n =0;
		SqlSession session = sqlSessionFactory.openSession();
		n=session.selectOne("adminDashBoard.findYesterdayPayment");
		session.close();
		return n;
	}

	public static Object findTodayQuit() {
		int n =0;
		SqlSession session = sqlSessionFactory.openSession();
		n=session.selectOne("adminDashBoard.findTodayQuit");
		session.close();
		return n;
	}

	public static Object findYesterdayQuit() {
		int n =0;
		SqlSession session = sqlSessionFactory.openSession();
		n=session.selectOne("adminDashBoard.findYesterdayQuit");
		session.close();
		return n;
	}
	
	
	public static List<AdminDashBoard> findDailyUsers(HashMap<String, Object> map) {
		SqlSession session = sqlSessionFactory.openSession();
		List<AdminDashBoard> list = session.selectList("adminDashBoard.findDailyUsers", map); 
		session.close();
		return list;
	}
	
	public static List<AdminDashBoard> findLevelByUser() {
		SqlSession session = sqlSessionFactory.openSession();
		List<AdminDashBoard> list = session.selectList("adminDashBoard.findLevelByUser"); 
		session.close();
		return list;
	}

	public static List<AdminDashBoard> findClassByBca() {
		SqlSession session = sqlSessionFactory.openSession();
		List<AdminDashBoard> list = session.selectList("adminDashBoard.findClassByBca"); 
		session.close();
		return list;
	}
	
	public static List<AdminDashBoard> findDailyClass(HashMap<String, Object> map) {
		SqlSession session = sqlSessionFactory.openSession();
		List<AdminDashBoard> list = session.selectList("adminDashBoard.findDailyClass", map);
	    session.close();
	    return list;
	}
	public static List<AdminDashBoard> findDailyPayment(HashMap<String, Object> map) {
		SqlSession session = sqlSessionFactory.openSession();
		List<AdminDashBoard> list = session.selectList("adminDashBoard.findDailyPayment", map);
	    session.close();
	    return list;
	}

	public static List<AdminDashBoard> findQuitReason() {
		SqlSession session = sqlSessionFactory.openSession();
		List<AdminDashBoard> list = session.selectList("adminDashBoard.findQuitReason"); 
		session.close();
		return list;
	}


	
}