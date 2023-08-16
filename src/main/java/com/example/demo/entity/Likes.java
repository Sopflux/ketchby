package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="likes")
public class Likes {
	
	@Id
	private int lno;
	
	private int clno;
	private int bno;
	private String aid;
	private int fno;
}
