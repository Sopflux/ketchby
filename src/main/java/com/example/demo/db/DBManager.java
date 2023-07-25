//package com.example.demo.db;
//
//import java.io.InputStream;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
//
//
//
//public class DBManager {
//	
//	public static SqlSessionFactory sqlSessionFactory;
//	static {
//		try {
//			String resource = "com/example/demo/db/sqlMapConfig.xml";
//			InputStream inputStream = Resources.getResourceAsStream(resource);
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		}catch (Exception e) {
//			System.out.println("예외발생:"+e.getMessage());
//		}
//	}
//	
//	public static Dept findByDno(int dno) {
//		SqlSession session = sqlSessionFactory.openSession();
//		Dept d = null;
//		d = session.selectOne("dept.findByDno", dno);
//		session.close();
//		return d;
//	}
//	
//}