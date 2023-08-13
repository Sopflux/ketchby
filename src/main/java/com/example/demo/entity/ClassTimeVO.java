package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClassTimeVO {
	private String tday;
	private String tstime;
	private String tetime;
	private int clno;
}