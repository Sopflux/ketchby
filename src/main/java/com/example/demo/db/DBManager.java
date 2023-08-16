package com.example.demo.db;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.HashMap;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;

import com.example.demo.entity.Accclub;
import com.example.demo.entity.Acclike;
import com.example.demo.entity.Account;
import com.example.demo.entity.ClDate;
import com.example.demo.entity.Class;
import com.example.demo.entity.Confirm;
import com.example.demo.entity.Follow;
import com.example.demo.entity.Payment;
import com.example.demo.entity.PaymentInfo;
import com.example.demo.entity.Quit;
import com.example.demo.entity.Reason;
import com.example.demo.entity.Reclass;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Review;
import com.example.demo.entity.ScategoryVO;
import com.example.demo.entity.Time;



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

	// account
	public static Account findByNick(String nick) {
		SqlSession session =  sqlSessionFactory.openSession();
		Account a = null;
		a = session.selectOne("account.findByNick", nick);
		session.close();
		return a;
	}
	public static List<Follow> findFollow(String email) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Follow> list= session.selectList("account.findFollow", email);
		session.close();
		return list;
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
		session.close();
		return a;
	}
	
	
	
	public static Account findByEmail(String email) {
		SqlSession session =  sqlSessionFactory.openSession();
		Account a = null;
		a = session.selectOne("account.findByEmail", email);
		session.close();
		return a;
	}

	public static Account findByAid(String id) {
		SqlSession session =  sqlSessionFactory.openSession();
		Account a = null;
		a = session.selectOne("account.findByAid", id);
		session.close();
		return a;
	}
	public static List<Follow> findFollowing(String email) {	
		SqlSession session =  sqlSessionFactory.openSession();
		List<Follow> list = session.selectList("account.findFollowing", email);
		session.close();
		return list;
	
	}
	public static int update(Account a) {
		SqlSession session =  sqlSessionFactory.openSession();
		int result = 0;
		result = session.update("account.update", a);
		session.commit();
		session.close();
		return result;
	}
	public static List findFeedImage(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Object> list = session.selectList("account.findFeedImage", aid);
		session.close();
		return list;
	}
	public static List<Reservation> findReservation(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Reservation> list = session.selectList("account.findReservation", aid);
		session.close();
		return list;
	}
	public static List<Review> findReview(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Review> list = session.selectList("account.findReview", aid);
		session.close();
		return list;
	}
	public static List<Accclub> findClub(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Accclub> list = session.selectList("account.findClub", aid);
		session.close();
		return list;
	}
	public static List<Acclike> findLike(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Acclike> list = session.selectList("account.findLike", aid);
		session.close();
		return list;
	}
	public static int insertReview(Review r) {
		SqlSession session =  sqlSessionFactory.openSession();
		int result = session.insert("account.insertReview", r);
		session.commit();
		session.close();
		return result;
	}
	public static Reclass findClassInfo(int clno) {
		SqlSession session =  sqlSessionFactory.openSession();
		Reclass rc = session.selectOne("account.findClassInfo", clno);
		session.close();
		return rc;
	}
	public static List<Confirm> findConfirm(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Confirm> c = session.selectList("account.findConfirm", aid);
		session.close();
		return c;
	}
	public static List<Time> findClTime(int clno) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Time> list= session.selectList("reservation.findClTime", clno);
		session.close();
		return list;
	}
	public static ClDate findClDate(int clno) {
		SqlSession session =  sqlSessionFactory.openSession();
		ClDate c= session.selectOne("reservation.findClDate", clno);
		session.close();
		return c;
	}
	public static PaymentInfo findPaymentInfo(int clno) {
		SqlSession session =  sqlSessionFactory.openSession();
		PaymentInfo p = session.selectOne("reservation.findPaymentInfo", clno);
		session.close();
		return p;
	}
	public static int findClPeople(Map<String, Object> param) {
		SqlSession session =  sqlSessionFactory.openSession();
		int re = session.selectOne("reservation.findClPeople",param);
		session.close();
		return re;
	}
	public static int findRePeople(Map<String, Object> param) {
		SqlSession session =  sqlSessionFactory.openSession();
		int re = session.selectOne("reservation.findRePeople", param);
		session.close();
		return re;
	}
	public static Class findByClno(int clno) {
		SqlSession session =  sqlSessionFactory.openSession();
		Class c = session.selectOne("reservation.findByClno", clno);
		session.close();
		return c;
	}
	public static void insertReservation(Payment p) {
		SqlSession session =  sqlSessionFactory.openSession();
		int result = session.insert("reservation.insertReservation", p);
		session.commit();
		session.close();
		
		
	}
	public static boolean checkFollow(HashMap<String, String> map) {
		SqlSession session =  sqlSessionFactory.openSession();
		int r= session.selectOne("account.checkFollow", map);
		session.close();
		if(r != 0) {
			return true;
		}
		return false;
		
	}
	public static List<Class> findOpenClass(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Class> list= session.selectList("account.findOpenClass", aid);
		session.close();
		return list;
	}
	public static void unfollow(HashMap<String, String> map) {
		SqlSession session =  sqlSessionFactory.openSession();
		int result = session.insert("account.unfollow", map);
		session.commit();
		session.close();
	}
	public static void follow(HashMap<String, String> map) {
		SqlSession session =  sqlSessionFactory.openSession();
		int result = session.delete("account.follow", map);
		session.commit();
		session.close();
		
	}
	public static List<Accclub> findOpenClub(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Accclub> list= session.selectList("account.findOpenClub", aid);
		session.close();
		return list;
	}
	public static List<Review> findAReview(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Review> list= session.selectList("account.findAReview", aid);
		session.close();
		return list;
	}
	
	//class
		public static int insertClass(Class c) {
			SqlSession session = sqlSessionFactory.openSession(true);
			int re = -1;
			re = session.insert("classOpen.insertClass", c);
			session.close();
			return re;
		}
		
		public static int insertClassTime(Time c) {
			SqlSession session = sqlSessionFactory.openSession(true);
			int re = -1;
			re = session.insert("classOpen.insertClassTime", c);
			session.close();
			return re;
		}
		
		public static List<ScategoryVO> findScaName(String bcaname){
			SqlSession session = sqlSessionFactory.openSession();
			List<ScategoryVO> list = null;
			list = session.selectList("classOpen.findScaName", bcaname);
			session.close();
			return list;
		}

		public static int findScaNo(String scaname) {
			// TODO Auto-generated method stub
			SqlSession session = sqlSessionFactory.openSession();
			int re = -1;
			re = session.selectOne("classOpen.findScaNo", scaname);
			return re;
		}
		public static List<Confirm> findClassCondition(String aid) {
			SqlSession session = sqlSessionFactory.openSession();
			List<Confirm> list = null;
			list = session.selectList("account.findClassCondition", aid);
			session.close();
			return list;
		}
		
		
		public static int deleteAccount(String aid) {
		SqlSession session =  sqlSessionFactory.openSession();
		int re = session.delete("account.deleteAccount",aid);
		session.commit();
		session.close();
		return re;
	}
	public static List<Reason> findReason() {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Reason> r = session.selectList("account.findReason");
		return r;
	}
	public static int insertQuit(Quit q) {
		SqlSession session =  sqlSessionFactory.openSession();
		int re = session.insert("account.insertQuit", q);
		session.commit();
		return re;
	}
	
}