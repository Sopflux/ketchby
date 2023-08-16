package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="reason")
public class Quit {
	
	@Id
	private int qno;
	private int reno;
	private String qdate;
}
