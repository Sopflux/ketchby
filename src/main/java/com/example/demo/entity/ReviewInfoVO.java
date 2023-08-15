package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInfoVO {
	private String rcontent;
	private String rdate;
	private String nick;
	private double rstar;
}