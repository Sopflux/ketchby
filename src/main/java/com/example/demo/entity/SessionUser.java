package com.example.demo.entity;

import java.io.Serializable;

public class SessionUser implements Serializable {
	private String name;
	private String email;
	
	public SessionUser(Account user) {
		this.name = user.getAid();
		this.email = user.getEmail();
	}
}
