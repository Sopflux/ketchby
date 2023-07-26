package com.example.demo.entity;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="qanda")
public class QandA {
	@Id
	private int qano;
	private int ano;
	private String qatitle;
	private String qacontent;

}
