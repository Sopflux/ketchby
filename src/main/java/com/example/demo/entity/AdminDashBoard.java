package com.example.demo.entity;

import lombok.Data;

@Data
public class AdminDashBoard {
	// 총 사용자 및 신규 사용자 파악을 위한 vo
	private String aid;
	private String regdate;
	private int countaid;
	
	// 레벨 별 사용자
	private String level_; 
	
	
	// 총 클래스 수, 신규 클래스 개설 수 및 카테고리별 클래스 수
	private int clno;
	private int scano;
	private String scaname;
	private int bcano;
	private String bcaname;
	private String clregdate;
	private int countclno;
	
	
	// 결제 수 및 예약수
	private int payno;
	private String paydate;
	private int countpayno;
	private int rsno;
	private String rsdate;
	private int countrsno;
	
	// 탈퇴 수 및 탈퇴 사유 비율
	private int qno;
	private String qdate;
	private int reno;
	private String rereason;
	private int countquit;
	
	
	
	
	public String getLevel_() {
		return level_;
	}
	public void setLevel(String level_) {
		this.level_ = level_;
	}
	public int getCountquit() {
		return countquit;
	}
	public void setCountquit(int countquit) {
		this.countquit = countquit;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getCountaid() {
		return countaid;
	}
	public void setCountaid(int countaid) {
		this.countaid = countaid;
	}
	public int getClno() {
		return clno;
	}
	public void setClno(int clno) {
		this.clno = clno;
	}
	public int getScano() {
		return scano;
	}
	public void setScano(int scano) {
		this.scano = scano;
	}
	public String getScaname() {
		return scaname;
	}
	public void setScaname(String scaname) {
		this.scaname = scaname;
	}
	public int getBcano() {
		return bcano;
	}
	public void setBcano(int bcano) {
		this.bcano = bcano;
	}
	public String getBcaname() {
		return bcaname;
	}
	public void setBcaname(String bcaname) {
		this.bcaname = bcaname;
	}
	public String getClregdate() {
		return clregdate;
	}
	public void setClregdate(String clregdate) {
		this.clregdate = clregdate;
	}
	public int getCountclno() {
		return countclno;
	}
	public void setCountclno(int countclno) {
		this.countclno = countclno;
	}

	public int getPayno() {
		return payno;
	}
	public void setPayno(int payno) {
		this.payno = payno;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public int getRsno() {
		return rsno;
	}
	public void setRsno(int rsno) {
		this.rsno = rsno;
	}
	public String getRsdate() {
		return rsdate;
	}
	public void setRsdate(String rsdate) {
		this.rsdate = rsdate;
	}
	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public int getReno() {
		return reno;
	}
	public void setReno(int reno) {
		this.reno = reno;
	}
	public String getRereason() {
		return rereason;
	}
	public void setRereason(String rereason) {
		this.rereason = rereason;
	}
	public int getCountpayno() {
		return countpayno;
	}
	public void setCountpayno(int countpayno) {
		this.countpayno = countpayno;
	}
	public int getCountrsno() {
		return countrsno;
	}
	public void setCountrsno(int countrsno) {
		this.countrsno = countrsno;
	}
	public String getQdate() {
		return qdate;
	}
	public void setQdate(String qdate) {
		this.qdate = qdate;
	}
	
}
