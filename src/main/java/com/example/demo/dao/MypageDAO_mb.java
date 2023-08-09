package com.example.demo.dao;

import java.util.List;import org.apache.tomcat.util.http.parser.AcceptLanguage;
import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.entity.Accclub;
import com.example.demo.entity.Acclike;
import com.example.demo.entity.Reclass;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Review;

@Repository
public class MypageDAO_mb {

	public List<Reservation> findReservation(String aid) {
		return DBManager.findReservation(aid);
	}

	public List<Review> findReview(String aid) {
		return DBManager.findReview(aid);
	}

	public List<Accclub> findClub(String aid) {
		return DBManager.findClub(aid);
	}

	public List<Acclike> findLike(String aid) {
		return DBManager.findLike(aid);
	}

	public int insertReview(Review r) {
		return DBManager.insertReview(r);
		
	}

	public Reclass findClassInfo(int clno) {
		return DBManager.findClassInfo(clno);
	}
	
}
