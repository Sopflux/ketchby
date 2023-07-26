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
	private int clNo;
	
	private int scaNo;
	private int aNo;
	private String clTitle;
	private String clImg;
	private String clContent;
	private String clLevel;
	private int clPeople;
	private int clPrice;
	private String clAddr;
	private String clDate;
	private String clSDate;
	private String clEDate;
	private String clType;
	private String bcaName;
	private String scaName;
}
