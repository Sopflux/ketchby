package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	AMDIN("ADMIN","손님"),
	USER("USER","사용자");
	
	private final String key;
	private final String title;
}




















