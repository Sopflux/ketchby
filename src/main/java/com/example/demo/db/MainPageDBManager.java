package com.example.demo.db;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.MainPage;

import lombok.Setter;

public class MainPageDBManager {
	
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
	
	public static List<MainPage> findRecClass() {
	    SqlSession session = sqlSessionFactory.openSession();
	    List<MainPage> list = null;
	    list = session.selectList("mainPage.findRecClass");
	    session.close();
	    return list;
	}


	public static List<MainPage> findHotClass() {
		SqlSession session = sqlSessionFactory.openSession();
	    List<MainPage> list = null;
	    list = session.selectList("mainPage.findHotClass");
	    session.close();
	    return list;
	}
	
	public static List<MainPage> findHotClub() {
		SqlSession session = sqlSessionFactory.openSession();
	    List<MainPage> list = null;
	    list = session.selectList("mainPage.findHotClub");
	    session.close();
	    return list;
	}

	public static List<MainPage> findHotBoard() {
		SqlSession session = sqlSessionFactory.openSession();
	    List<MainPage> list = null;
	    list = session.selectList("mainPage.findHotBoard");
	    session.close();
	    return list;
	}

}
