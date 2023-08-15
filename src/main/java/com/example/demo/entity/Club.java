package com.example.demo.entity;

import org.springframework.web.multipart.MultipartFile;


import lombok.Data;

@Data
public class Club {
	private int cbno;
	private int scano;
	private String cbtitle;
	private String cbimg;
	private String cbcontent;
	private int slocno;
	private int cbpeople;
	private String cbopdate;
	private String cbclsdate;
	private String aid;
	private MultipartFile uploadFile;
}
