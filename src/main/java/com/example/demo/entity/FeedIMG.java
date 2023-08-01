package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class FeedIMG {
	
	private int fno;
	private String aid;
	private String fcontent;
	private Date fdate;
	private String nick;
	private String img;
	private List<String> flist;
	
	
	public FeedIMG() {
		this.flist = new ArrayList<>();
	}

}