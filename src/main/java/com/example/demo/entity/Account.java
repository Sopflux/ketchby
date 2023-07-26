package com.example.demo.entity;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="account")
public class Account {
	
	@Id
	private String aid;
	private String pwd;
	private String email;
	private String name_;
	private String nick;
	private String img;

	@Transient
	private MultipartFile uploadFile;
	
	private String level_;
	private String regdate;
}
