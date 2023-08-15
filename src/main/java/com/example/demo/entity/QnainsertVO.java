package com.example.demo.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qnainsert")
public class QnainsertVO {
	@Id
	private int qino;
	private int qno;
	private String qiname;
	private String qiemail;
	private String qititle;
	private String qicontent;
	private String qidate;
	private String qicondition;
}