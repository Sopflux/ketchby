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

	public static int findTotalUsers() {
		int n =0;
		SqlSession session = sqlSessionFactory.openSession();
		n=session.selectOne("adminDashBoard.findTotalUsers");
		session.close();
		return n;
	}
	public static List<AdminDashBoard> findDailyUsers(HashMap<String, Object> map) {
		SqlSession session = sqlSessionFactory.openSession();
		List<AdminDashBoard> list = session.selectList("adminDashBoard.findDailyUsers", map); 
		session.close();
		return list;
	}

	
}