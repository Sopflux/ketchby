  package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dao.AccountDAO;
import com.example.demo.entity.Account;

@SpringBootApplication
public class KetchbyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KetchbyApplication.class, args);
	}

	
	@SpringBootApplication
	public class JpaBoard3Application {
		@Bean
		public PasswordEncoder passwordEncoder() {
			return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}

	}
}
