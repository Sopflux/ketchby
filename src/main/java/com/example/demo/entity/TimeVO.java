package com.example.demo.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "time")
public class TimeVO {
	@Id
	private int tno;
	private int clno;
	private int cbno;
	private String tday;
	private String tstime;
	private String tetime;
}