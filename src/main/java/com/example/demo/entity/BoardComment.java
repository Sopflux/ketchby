package com.example.demo.entity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardComment {
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
	private int cono;
	private String cocontent;
	private String codate;
	private int coref;
	private int colevel;
	private int costep;
	private List<Comments> colist;
}
