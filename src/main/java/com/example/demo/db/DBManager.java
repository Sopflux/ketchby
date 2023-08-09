package com.example.demo.db;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;

import com.example.demo.entity.Accclub;
import com.example.demo.entity.Acclike;
import com.example.demo.entity.Account;
import com.example.demo.entity.Reclass;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Review;



public class DBManager {
	
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
	
	public static Account findByNick(String nick) {
		SqlSession session =  sqlSessionFactory.openSession();
		Account a = null;
		a = session.selectOne("account.findByNick", nick);
		return a;
	}
	public static int findFollow(String email) {
		SqlSession session =  sqlSessionFactory.openSession();
		int follow = 0;
		follow = session.selectOne("account.findFollow", email);
		return follow;
	}
	
	public static Account emailCheckWithEmail(String email, String id) {
		SqlSession session =  sqlSessionFactory.openSession();
		Account a = null;
		HashMap<String, String> params = new HashMap<>();
	    params.put("email", email);
	    params.put("id", id);
	    System.out.println("DBManager id:"+id);
	    System.out.println("DBManager email:"+email);
		a = session.selectOne("account.emailCheckWithEmail",params);
		return a;
	}
	
	public static Account findByEmail(String email) {
		SqlSession session =  sqlSessionFactory.openSession();
		Account a = null;
		a = session.selectOne("account.findByEmail", email);
		return a;
	}

	public static Account findByAid(String id) {
		SqlSession session =  sqlSessionFactory.openSession();
		Account a = null;
		a = session.selectOne("account.findByAid", id);
		return a;
	}
	public static int findFollowing(String email) {
		SqlSession session =  sqlSessionFactory.openSession();
		int follow = 0;
		follow = session.selectOne("account.findFollowing", email);
		return follow;
	
	}
	public static int update(Account a) {
		SqlSession session =  sqlSessionFactory.openSession();
		int result = 0;
		result = session.update("account.update", a);
		session.commit();
		return result;
	}
	public static List findFeedImage(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Object> list = session.selectList("account.findFeedImage", aid);
		return list;
	}
	public static List<Reservation> findReservation(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Reservation> list = session.selectList("account.findReservation", aid);
		return list;
	}
	public static List<Review> findReview(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Review> list = session.selectList("account.findReview", aid);
		return list;
	}
	public static List<Accclub> findClub(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Accclub> list = session.selectList("account.findClub", aid);
		return list;
	}
	public static List<Acclike> findLike(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Acclike> list = session.selectList("account.findLike", aid);
		return list;
	}
	public static int insertReview(Review r) {
		SqlSession session =  sqlSessionFactory.openSession();
		int result = session.insert("account.insertReview", r);
		session.commit();
		return result;
	}
	public static Reclass findClassInfo(int clno) {
		SqlSession session =  sqlSessionFactory.openSession();
		Reclass rc = session.selectOne("account.findClassInfo", clno);
		return rc;
	}

	
}