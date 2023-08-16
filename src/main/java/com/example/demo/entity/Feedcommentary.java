package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
public class Feedcommentary {
	
	@Id
	private int fcono;
	
	
	private int fno;
	private String aid;
	private String fcocomment;
	private String fcodate;
	private String nick;
	private String img;
}
