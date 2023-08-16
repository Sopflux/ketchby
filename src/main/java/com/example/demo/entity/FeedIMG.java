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
	private String fdate;
	private String nick;
	private String img;
	private int cntLike;
	private int cntComment;
	private int userLike;
	private List<Feedcommentary> fcolist;


	private List<String> flist;
	
	public FeedIMG() {
		this.flist = new ArrayList<>();
	}


}