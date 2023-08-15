package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminQnainsertVO {
	private int qino;
	private int qno;
	private String qca;
	private String qiname;
	private String qiemail;
	private String qititle;
	private String qicontent;
	private String qidate;
	private String qicondition;
}