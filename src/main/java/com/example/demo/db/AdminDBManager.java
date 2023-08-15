package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.Account;
import com.example.demo.entity.AdminQnainsertVO;
import com.example.demo.entity.NoticeVO;
import com.example.demo.entity.QnainsertVO;

public class AdminDBManager {
	
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
	
	
	//Account
	public static List<Account> findAllAccount(HashMap<String, Object> map) {
		SqlSession session =  sqlSessionFactory.openSession();
		List<Account> list = null;
		list = session.selectList("adminAccount.findAll", map);
		session.close();
		return list;
	}
	
	public static int getTotalRecordAccount() {
		// TODO Auto-generated method stub
		int n = 0;
		SqlSession session = sqlSessionFactory.openSession();
		n = session.selectOne("adminAccount.getTotalUser");
		session.close();
		return n;
	}

	public static Account findAccount(String aid) {
		SqlSession session = sqlSessionFactory.openSession();
		Account a = null;
		a = session.selectOne("adminAccount.findAccount", aid);
		session.close();
		return a;
	}
	
	public static int getTotalForPageAccount(HashMap<String, Object> map) {
		SqlSession session = sqlSessionFactory.openSession();
		int m = 0;
		m = session.selectOne("adminAccount.getTotalForPage", map);
		session.close();
		System.out.println("검색어 : "+m);
		return m;
	}

	public static Account findByAid(String aid) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		Account a = null;
		a = session.selectOne("adminAccount.findByAid", aid);
		session.close();
		System.out.println("a: "+a);
		return a;
	}

	public static int deleteAccount(String aid) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = -1;
		re = session.delete("adminAccount.deleteAccount",aid);
		session.close();
		return re;
	}

	public static int updateAccount(Account a) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = -1;
		re = session.update("adminAccount.updateAccount", a);
		session.close();
		return re;
	}
	
	public static int insertAccount(Account a) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = -1;
		re = session.delete("adminAccount.insertAccount", a);
		session.close();
		return re;
	}
	
	
	//Notice
	public static int deleteNotice(int noticeno) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = -1;
		re = session.delete("adminNotice.deleteNotice", noticeno);
		session.close();
		return re;
	}
	
	public static int updateNotice(NoticeVO n) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = -1;
		re = session.update("adminNotice.updateNotice", n);
		session.close();
		return re;
	}
	
	public static int insertNotice(NoticeVO n) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = -1;
		re = session.insert("adminNotice.insertNotice", n);
		session.close();
		return re;
	}
	
	public static List<NoticeVO> findAllNotice(HashMap<String, Object> map){
		SqlSession session = sqlSessionFactory.openSession();
		List<NoticeVO> list = null;
		list = session.selectList("adminNotice.findAll", map);
		session.close();
		return list;
	}
	
	public static NoticeVO findNotice(int noticeno) {
		SqlSession session = sqlSessionFactory.openSession();
		NoticeVO n = null;
		n = session.selectOne("adminNotice.findNotice", noticeno);
		session.close();
		return n;
	}
	
	public static int getTotalForPageNotice(String keyword) {
		SqlSession session = sqlSessionFactory.openSession();
		int re = -1;
		re = session.selectOne("adminNotice.getTotalForPage", keyword);
		session.close();
		return re;
	}
	
	public static int getTotalNotice() {
		SqlSession session = sqlSessionFactory.openSession();
		int re = -1;
		re = session.selectOne("adminNotice.getTotalNotice");
		session.close();
		return re;
	}

	//qna
	
	//총 문의사항 수
	public static int getTotalRecordQna() {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		int re = -1;
		re = session.selectOne("adminQna.getTotalQna");
		session.close();
		return re;
	}

	public static int getTotalForPageQna(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		int re = 0;
		re = session.selectOne("adminQna.getTotalForPage", map);
		session.close();
		return re;
	}

	public static List<AdminQnainsertVO> findAllQna(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		List<AdminQnainsertVO> list = null;
		list = session.selectList("adminQna.findAll", map);
		session.close();
		return list;
		
	}

	public static AdminQnainsertVO findByQino(int qino) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		AdminQnainsertVO q = null;
		q = session.selectOne("adminQna.findQino", qino);
		session.close();
		return q;
	}
	
	public static int updateCondition(int qino) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = -1;
		re = session.update("adminQna.updateCondition", qino);
		session.close();
		return re;
	}

}