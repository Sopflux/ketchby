package com.example.demo.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccReview {
	private int clno;
	private String rcontent;
	private int aid;
	private double rstar;
	private Date rdate;
	private String cltitle;
}
