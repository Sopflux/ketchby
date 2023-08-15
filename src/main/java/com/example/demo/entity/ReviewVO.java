package com.example.demo.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewVO {
private int clNo;
private String rContent;
private int aNo;
private int rStar;
private Date rDate;
}