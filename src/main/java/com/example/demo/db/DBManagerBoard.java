package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comments;


public class DBManagerBoard {
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

	public static List<Board> findAll(HashMap<String, Object> map) {
		System.out.println("DBManager map 값: "+map);
	    SqlSession session = sqlSessionFactory.openSession();
	    List<Board> list = session.selectList("board.findAll", map);

	    session.close();
	    return list;
	}
	
	public static int getTotalRecord(HashMap<String, Object> map2) {
		int n=0;
		SqlSession session = sqlSessionFactory.openSession();
		n=session.selectOne("board.getTotalRecord", map2);
		session.close();
		return n;
	}
	
	public static int insert(Board b) {
		SqlSession session=sqlSessionFactory.openSession();
		int re=session.insert("board.insert",b);
		session.commit();
		session.close();
		return re;
	} 
	
	public static int getNextNo() {
		int no=0;
		SqlSession session=sqlSessionFactory.openSession();
		no=session.selectOne("board.getNextNo");
		session.close();
		return no;
	}
	public static int getNextCommentsNo() {
		int no=0;
		SqlSession session=sqlSessionFactory.openSession();
		no=session.selectOne("comments.getNextCommentsNo");
		session.close();
		return no;
	}
	
	public static Board findByNo(int bno) {
		SqlSession session=sqlSessionFactory.openSession();
		Board b=session.selectOne("board.findByNo", bno);
		session.close();
		return b;
	}
	
	public static List<Comments> findByComments(int bno){
		SqlSession session=sqlSessionFactory.openSession();
		List<Comments> list=session.selectList("comments.findByComments", bno);
		session.close();
		return list;
	}
	
	public static int insertComments(Comments c) {
		SqlSession session=sqlSessionFactory.openSession();
		int re=session.insert("comments.insert",c);
		session.commit();
		session.close();
		return re;
	}
	
	public static int deleteComments(int cbno) {
		int re=-1;
		SqlSession session = sqlSessionFactory.openSession();
		re=session.insert("comments.delete",cbno);
		session.commit();
		session.close();
		return re;
	}
	public static int deletecommentsByboard(int bno) {
		int re=-1;
		SqlSession session = sqlSessionFactory.openSession();
		re=session.insert("comments.deletecommentsByboard",bno);
		session.commit();
		session.close();
		return re;
	}
	
	public static int delete(int bno) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.update("board.delete", bno);
		session.commit();
		session.close();
		return re;
	}
	
	public static int update(Board b) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.update("board.update", b);
		session.commit();
		session.close();
		return re;
	}
}
