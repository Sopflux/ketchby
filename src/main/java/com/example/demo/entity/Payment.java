package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	private int clno;
	private String rsdate;
	private int clpeople;
	private String aid;
	private String aphone;
	private String resrvname;
	private String email;
	private String payresultnum;
	private int rsprice;
	private String paymethod;
}
