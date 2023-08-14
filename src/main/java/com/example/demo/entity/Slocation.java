package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="slocation")
public class Slocation {
	
	@Id
	private int slocno;
	
	private String slocname;
	private int blocno;
}
