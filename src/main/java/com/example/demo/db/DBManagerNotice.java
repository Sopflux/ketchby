package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.Notice;

public class DBManagerNotice {
	public static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}
	}

	public static List<Notice> findAll(HashMap<String, Object> map) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Notice> list = session.selectList("notice.findAll", map);
		session.close();
		return list;
	}

	public static int getTotalRecord() {
		int n = 0;
		SqlSession session = sqlSessionFactory.openSession();
		n = session.selectOne("notice.getTotalRecord");
		session.close();
		return n;
	}
	
	public static Notice findByNo(int noticeno) {
		SqlSession session = sqlSessionFactory.openSession();
		Notice n=session.selectOne("notice.findByNo", noticeno);
		session.close();
		return n;
	}
}
