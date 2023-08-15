package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClDate {
	private int clno;
	private String clsdate;
	private String cledate;
	private int clprice;
	private int clpeople;

}
