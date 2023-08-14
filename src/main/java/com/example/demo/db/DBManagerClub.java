package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.Club;
import com.example.demo.entity.ClubDetail;
import com.example.demo.entity.ClubMain;
import com.example.demo.entity.Clubapplication;
import com.example.demo.entity.Scategory;
import com.example.demo.entity.Slocation;

public class DBManagerClub {
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

	public static List<ClubMain> getClubList(HashMap<String, Object> map) {
		SqlSession session = sqlSessionFactory.openSession();
		List<ClubMain> list = session.selectList("clubmain.getClubList", map);
		session.close();
		return list;
	}

	public static int getNextCbNo() {
		SqlSession session = sqlSessionFactory.openSession();
		int no = session.selectOne("club.getNextCbNo");
		session.close();
		return no;
	}

	public static int insert(Club c) {
		SqlSession session = sqlSessionFactory.openSession();
		int re = session.insert("club.insert", c);
		session.commit();
		session.close();
		return re;
	}

	public static List<Scategory> findscategory(int bcano) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Scategory> list = session.selectList("scategory.findscategory", bcano);
		session.close();
		return list;
	}

	public static List<Slocation> findslocation(int blocno) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Slocation> list = session.selectList("slocation.findslocation", blocno);
		session.close();
		return list;
	}

	public static ClubDetail findClubInfo(int cbno) {
		SqlSession session=sqlSessionFactory.openSession();
		ClubDetail c=session.selectOne("clubdetail.findClubInfo", cbno);
		session.close();
		return c;
	}
	public static List<ClubDetail> findRecommandClub(HashMap<String, Object> map){
		SqlSession session=sqlSessionFactory.openSession();
		List<ClubDetail> list=session.selectList("clubdetail.findRecommandClub", map);
		session.close();
		return list;
	}
	public static int insert(Clubapplication ca) {
		SqlSession session=sqlSessionFactory.openSession();
		int re=session.insert("clubapplication.insert", ca);
		session.commit();
		session.close();
		return re;
	}
	
	public static int getNextCbapno() {
		SqlSession session=sqlSessionFactory.openSession();
		int c=session.selectOne("clubapplication.getNextCbapno");
		session.close();
		return c;
	}
	
	public static int findApplyCount(HashMap<String, Object> map) {
		SqlSession session=sqlSessionFactory.openSession();
		int c=session.selectOne("clubapplication.findApplyCount",map);
		session.close();
		return c;
	}
	
	public static int update(Club c) {
		SqlSession session=sqlSessionFactory.openSession();
		int re=session.update("club.update", c);
		session.commit();
		session.close();
		return re;
	}
	public static int delete(int cbno) {
		SqlSession session=sqlSessionFactory.openSession();
		int re=session.delete("club.delete", cbno);
		session.commit();
		session.close();
		return re;
	}
}
