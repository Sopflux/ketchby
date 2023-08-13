package com.example.demo.entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bdate;
	private String bfname;
	private String aid;
	private String nick;
	private int comment_count;
	private int n;
	private MultipartFile uploadFile;
	private String img;

	
}
