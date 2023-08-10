package com.example.demo.entity;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
public class MainPage {
	
	//클래스용
	private int clno;
	private int scano;
	private String cltitle;
	private String claddr;
	private String climg;
	private String cllevel;
	private int clprice;
	private String scaname;
	private String bcaname;
	private String aid;
	private String level_;
	
	//커뮤니티용
	private int bno;
	private String btitle;
	private String bcontent;
	private String bdate;
	private String nick;
	private String img;

	
	
	//소모임용
	private int cbno;
	private String cbtitle;
	private int cbpeople;
	private String cbimg;
	private String cbclsdate;
	private int slocno;
	
}
