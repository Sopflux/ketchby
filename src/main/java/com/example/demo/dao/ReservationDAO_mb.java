package com.example.demo.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.entity.ClDate;
import com.example.demo.entity.Payment;
import com.example.demo.entity.PaymentInfo;
import com.example.demo.entity.Time;

@Repository
public class ReservationDAO_mb {
	public List<Time> findClTime(int clno){
		return DBManager.findClTime(clno);
	}

	public ClDate findClDate(int clno) {
		return DBManager.findClDate(clno);
	}

	public PaymentInfo findPaymentInfo(int clno) {
		return DBManager.findPaymentInfo(clno);
	}

	public int findClPeople(Map<String, Object> param) {
		return DBManager.findClPeople(param);
	}

	public int findRePeople(Map<String, Object> param) {
		return DBManager.findRePeople(param);
	}

	public com.example.demo.entity.Class findByClno(int clno) {
		return DBManager.findByClno(clno);
	}

	public void insertReservation(Payment p) {
		DBManager.insertReservation(p);
		
	}
}
