package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="bcategory")
public class Bcategory {
	
	@Id
	private int bcano;

	private String bcaname;
}
