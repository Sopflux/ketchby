package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassDetailVO {
	private int clno;
	private int scano;
	private String aid;
	private String cltitle;
	private String climg;
	private String clcontent;
	private String cllevel;
	private int clpeople;
	private int clprice;
	private String claddr;
	private String cldate;
	private String clsdate;
	private String cledate;
	private String cltype;
	private String scaname;
	private String bcaname;
}
