package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.entity.QandA;
import com.example.demo.entity.QnainsertVO;

public class DBManagerQandA {
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

	public static List<QandA> findAll(){
		List<QandA> list=null;
		SqlSession session=sqlSessionFactory.openSession();
		list=session.selectList("qanda.findAll");
		session.close();
		return list;
	}
	
	public static List<QandA> findByQno(int qno){
		SqlSession session=sqlSessionFactory.openSession();
		List<QandA> list=session.selectList("qanda.findByQno", qno);
		session.close();
		return list;
	}
	
	public static int getNextQino() {
		SqlSession session=sqlSessionFactory.openSession();
		int re=session.selectOne("qnainsert.getNextQino");
		session.close();
		return re;
	}
	public static int insert(QnainsertVO q) {
		SqlSession session=sqlSessionFactory.openSession();
		int re=session.insert("qnainsert.insert", q);
		session.commit();
		session.close();
		return re;
	}
}
