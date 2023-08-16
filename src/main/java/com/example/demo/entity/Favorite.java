package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="favorite")
public class Favorite {
	
	@Id
	private int fno;
	
	private int scano;
	private String aid;
}
