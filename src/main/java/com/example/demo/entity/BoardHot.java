package com.example.demo.entity;

import lombok.Data;

@Data
public class BoardHot {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bdate;
	private String bfname;
	private String nick;
	private int comment_count;
}
