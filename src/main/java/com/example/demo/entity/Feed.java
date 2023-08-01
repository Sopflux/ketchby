package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="feed")
public class Feed {
	
	@Id
	private int fno;
	
	private String aid;
	private String fcontent;
	private Date fdate;
}
