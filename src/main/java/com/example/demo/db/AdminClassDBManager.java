package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.AdminClass;

public class AdminClassDBManager {
	
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
	
	public static int getTotalRecord(String mapperId) {
		int n =0;
		SqlSession session = sqlSessionFactory.openSession();
		n=session.selectOne(mapperId +".getTotalRecord");
		session.close();
		return n;
	}
	
	public static List<AdminClass> findAll(String mapperId, HashMap<String, Object> map){
		SqlSession session = sqlSessionFactory.openSession();
		List<AdminClass> list = session.selectList(mapperId +".findAll", map);
		session.close();
		return list;
	}    

	public static AdminClass findByClno(String mapperId, int clno) {
		AdminClass ac = null;
		SqlSession session = sqlSessionFactory.openSession();
		ac = session.selectOne(mapperId +".findByClno", clno);
		session.close();
		return ac;
	}
	
	public static int delete(String mapperId, int clno) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession(true);
		re = session.delete(mapperId +".delete", clno);
		session.close();
		return re;
	}

	public static int update(HashMap<String, Object> map) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession(true);
		re = session.update("adminClassPending.update", map);
		session.close();
		return re;
	}



	
}