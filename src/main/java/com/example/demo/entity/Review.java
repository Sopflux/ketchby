package com.example.demo.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	private int clno;
	private String rcontent;
	private String aid;
	private double rstar;
	private String rdate;
	private String cltitle;
	private String nick;
}
