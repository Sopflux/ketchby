package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;

import com.example.demo.entity.Account;
import com.example.demo.entity.Feed;
import com.example.demo.entity.FeedIMG;
import com.example.demo.entity.Image;



public class FeedDBManager {

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
	
	public static int insertFeed(Feed f) {
		int re1=-1;
		SqlSession session= sqlSessionFactory.openSession();
		re1 = session.insert("feed.insertFeed", f);
		session.commit(); 
		session.close();
		return re1;
	}
	public static int insertFeedImg(Image im) {
		int re2=-1;
		SqlSession session= sqlSessionFactory.openSession();
		re2 = session.insert("feed.insertFeedImg", im);
		session.commit(); 
		session.close();
		return re2;
	}
	
	
	
	public static int findFeedMax() {
		SqlSession session= sqlSessionFactory.openSession();
		int maxNo = session.selectOne("feed.maxFeedNo");
		session.close();
		return maxNo;
	}

	public static List<Feed> findAllFeed(){
		List<Feed> list = null;
		SqlSession session= sqlSessionFactory.openSession();
		list = session.selectList("feed.findAll");
		session.close();
		return list;
	}
	public static List<String> imgFindByFno(int fno){
		List<String> list = null;
		SqlSession session= sqlSessionFactory.openSession();
		list = session.selectList("feed.imgFindByFno",fno);
		session.close();
		return list;
	}
	public static Account findAccount(String aid){
		Account a = null;
		SqlSession session= sqlSessionFactory.openSession();
		a = session.selectOne("feed.findAccount",aid);
		session.close();
		return a;
	}

	
}