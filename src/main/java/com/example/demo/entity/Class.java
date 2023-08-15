package com.example.demo.entity;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="class")
public class Class {
	
	@Id
	private int clno;
	
	private int scano;
	private int ano;
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
	private String bcaname;
	private String scaname;
	private int cnt;
	private int avg;
}
