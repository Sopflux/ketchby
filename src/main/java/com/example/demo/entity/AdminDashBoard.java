package com.example.demo.entity;

import lombok.Data;

@Data
public class AdminDashBoard {
	// 총 사용자 및 신규 사용자 파악을 위한 vo
	private String aid;
	private String regdate;
	private int countaid;
	
	// 총 클래스 수, 신규 클래스 개설 수 및 카테고리별 클래스 수
	private int clno;
	private int scano;
	private String scaname;
	private int bcano;
	private String bcaname;
	private String clregdate;
	private int countclno;
	
	// 커뮤니티 게시물 수 및 신규 게시물 수
	private int bno;
	private String bdate;
	
	// 피드 수 및 신규 피드 수
	private int fno;
	private String fdate;
	
	// 결제 수 및 신규 결제 수
	private int payno;
	private String paydate;
	
	// 탈퇴 수 및 탈퇴 사유 비율
	private int qno;
	private int reno;
	private String rereason;
	public String getAid() {
		return aid;
	}
	
	
	public int getCountaid() {
		return countaid;
	}

	public void setCountaid(int countaid) {
		this.countaid = countaid;
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
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getFdate() {
		return fdate;
	}
	public void setFdate(String fdate) {
		this.fdate = fdate;
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
	
	
	
	
	
}
