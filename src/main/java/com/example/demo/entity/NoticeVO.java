package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "notice")
public class NoticeVO {
	@Id
	private int noticeno;
	private String noticedate;
	private String noticewriter;
	private String noticetitle;
	private String noticecontent;
}
