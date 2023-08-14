package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.MainPage;

public class ClassMainDBManager {
	
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

	public static List<MainPage> findAllClass(String bcaname) {
		SqlSession session = sqlSessionFactory.openSession();
		List<MainPage> list = null;
		list = session.selectList("classMain.findAllClass",bcaname);
		session.close();
		return list;
	}


}
