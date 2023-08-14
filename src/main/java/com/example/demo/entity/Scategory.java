package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="scategory")
public class Scategory {
	
	@Id
	private int scano;
	
	private int bcano;
	private String scaname;
}
