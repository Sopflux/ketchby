package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;

import com.example.demo.entity.Account;
import com.example.demo.entity.TimeVO;
import com.example.demo.entity.ClassVO;
import com.example.demo.entity.ScategoryVO;

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
	
	//class
	public static int insertClass(ClassVO c) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = -1;
		re = session.insert("classOpen.insertClass", c);
		session.close();
		return re;
	}
	
	public static int insertClassTime(TimeVO c) {
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
}