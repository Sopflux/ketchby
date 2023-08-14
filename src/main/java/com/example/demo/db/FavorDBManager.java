package com.example.demo.db;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;

import com.example.demo.entity.Accclub;
import com.example.demo.entity.Acclike;
import com.example.demo.entity.Account;
import com.example.demo.entity.Bcategory;
import com.example.demo.entity.Blocation;
import com.example.demo.entity.Favorite;
import com.example.demo.entity.Feed;
import com.example.demo.entity.Place;
import com.example.demo.entity.Reclass;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Review;
import com.example.demo.entity.Scategory;
import com.example.demo.entity.Slocation;



public class FavorDBManager {
	
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
	

	public static List<Slocation> findSlocation(int blocno) {
		List<Slocation> list = null;
		SqlSession session= sqlSessionFactory.openSession();
		list = session.selectList("favor.findSlocation",blocno);
		session.close();
		return list;
	}
	public static List<Scategory> findScategory(int bcano) {
		List<Scategory> list = null;
		SqlSession session= sqlSessionFactory.openSession();
		list = session.selectList("favor.findScategory",bcano);
		session.close();
		return list;
	}
	public static List<Blocation> findBlocation() {
		List<Blocation> list = null;
		SqlSession session= sqlSessionFactory.openSession();
		list = session.selectList("favor.findBlocation");
		session.close();
		return list;
	}
	public static List<Bcategory> findBcategory() {
		List<Bcategory> list = null;
		SqlSession session= sqlSessionFactory.openSession();
		list = session.selectList("favor.findBcategory");
		session.close();
		return list;
	}
	
	public static int insertPlace(Place p) {
		int re1=-1;
		SqlSession session= sqlSessionFactory.openSession();
		re1 = session.insert("favor.insertPlace", p);
		session.commit(); 
		session.close();
		return re1;
	}
	public static int insertFavorite(Favorite f) {
		int re1=-1;
		SqlSession session= sqlSessionFactory.openSession();
		re1 = session.insert("favor.insertFavorite", f);
		session.commit(); 
		session.close();
		return re1;
	}
	public static List<Favorite> findHobbyById(String aid){
		List<Favorite> list=null;
		SqlSession session= sqlSessionFactory.openSession();
		list = session.selectList("favor.findHobbyById",aid);
		session.close();
		return list;
	}
	public static List<Place> findPlaceById(String aid){
		List<Place> list=null;
		SqlSession session= sqlSessionFactory.openSession();
		list = session.selectList("favor.findPlaceById",aid);
		session.close();
		return list;
	}
	public static int updateFavorite(int scano, int fno) {
		SqlSession session= sqlSessionFactory.openSession();
		Map<String, Object> map = new HashMap<>();
	    map.put("fno", fno);
	    map.put("scano", scano);
		int n=session.update("favor.updateFavorite",map);
		session.commit();
		session.close();
		return n;
	}
	public static int updatePlace(int slocno, int pno) {
		SqlSession session= sqlSessionFactory.openSession();
		Map<String, Object> map = new HashMap<>();
		map.put("pno", pno);
		map.put("slocno", slocno);
		int n=session.update("favor.updatePlace",map);
		session.commit();
		session.close();
		return n;
	}

	
}