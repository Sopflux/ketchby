package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.Account;
import com.example.demo.entity.ClassDetailVO;
import com.example.demo.entity.ClassTimeVO;
import com.example.demo.entity.ReviewInfoVO;

public class ClassDetailDBManager {
	
	public static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ClassDetailDBManager error: "+e.getMessage());
		}
	}
	
	//Class Detail
		public static ClassDetailVO findAllClass(int clno){
			SqlSession session = sqlSessionFactory.openSession();
			ClassDetailVO c = null;
			c = session.selectOne("classDetail.findAllClass", clno);
			session.close();
			return c;
		}
		
		public static Account findAccountInfo(int clno){
			SqlSession session = sqlSessionFactory.openSession();
			Account a = null;
			a = session.selectOne("classDetail.findAccountInfo", clno);
			session.close();
			return a;
		}
		
		public static double totalClassTime(int clno) {
			SqlSession session = sqlSessionFactory.openSession();
			double c = -1;
			c = session.selectOne("classDetail.totalClassTime", clno);
			session.close();
			return c;
		}
		
		public static List<ClassTimeVO> findClassTime(int clno) {
			SqlSession session = sqlSessionFactory.openSession();
			List<ClassTimeVO> c = null;
			c = session.selectList("classDetail.findClassTime", clno);
			session.close();
			return c;
		}
		
		public static double findScore(int clno) {
			SqlSession session = sqlSessionFactory.openSession();
			double re = -1;
			re = session.selectOne("classDetail.findScore", clno);
			session.close();
			return re;
		}
		
		public static List<ReviewInfoVO> findReview(int clno) {
			SqlSession session = sqlSessionFactory.openSession();
			List<ReviewInfoVO> re = null;
			re = session.selectList("classDetail.findReview", clno);
			session.close();
			return re;
		}
		
		public static int deleteLike(HashMap<String, Object> map) {
			SqlSession session = sqlSessionFactory.openSession(true);
			int re = -1;
			re = session.delete("classDetail.deleteLikes", map);
			session.close();
			return re;
		}
		
		public static int countLike(HashMap<String, Object> map) {
			SqlSession session = sqlSessionFactory.openSession();
			int re = -1;
			re = session.selectOne("classDetail.countLike", map);
			session.close();
			return re;
		}
		
		public static int insertLike(HashMap<String, Object> map) {
			SqlSession session = sqlSessionFactory.openSession(true);
			int re = -1;
			re = session.insert("classDetail.insertLike", map);
			session.close();
			return re;
		}

		public static int checkLike(HashMap<String, Object> map) {
			// TODO Auto-generated method stub
			SqlSession session = sqlSessionFactory.openSession();
			int re = -1;
			re = session.selectOne("classDetail.checkLike",map);
			return re;
		}
		
}
