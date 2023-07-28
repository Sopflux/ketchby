package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class FeedIMG {
	
	private int fno;
	private String aid;
	private String fcontent;
	private List<String> flist;
	
	public FeedIMG() {
		this.flist = new ArrayList<>();
	}

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getFcontent() {
		return fcontent;
	}

	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}

	public List<String> getFlist() {
		return flist;
	}

	public void setFlist(List<String> flist) {
		this.flist = flist;
	}

	
}