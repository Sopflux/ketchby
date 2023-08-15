package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.BoardHot;

public class DBManagerBoardHot {
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
	
	public static List<BoardHot> findHotAll(){
		SqlSession session=sqlSessionFactory.openSession();
		List<BoardHot> list=session.selectList("boardhot.findHotAll");
		session.close();
		return list;
	}
}
