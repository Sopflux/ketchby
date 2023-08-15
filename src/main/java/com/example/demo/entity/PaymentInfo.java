package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfo {
	private String cltitle;
	private String nick;
	private String climg;
	private String bcaname;
	private String scaname;
	private int clno;
	private int clprice;
}
