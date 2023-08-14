package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.demo.entity.Account;

public class CustomIntegratedLogin implements OAuth2User, UserDetails {
	private Account user;
	private OAuthAttributes oAuthAttributes;

	public CustomIntegratedLogin(Account user) {
		this.user = user;
	}

	public CustomIntegratedLogin(Account user, OAuthAttributes oAuthAttributes) {
		this.user = user;
		this.oAuthAttributes = oAuthAttributes;

	}

	@Override
	public String getPassword() {
		return user.getPwd();
	}

	@Override
	public String getUsername() {
		return user.getAid();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	// ex) {sub=1231344534523565757, name=홍길동, given_name=길동, family_name=홍,
	// picture=https://xxx, email=xxx@gmail.com, email_verified=true, locale=ko}
	public Map<String, Object> getAttributes() {
		return oAuthAttributes.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList();
		collection.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return "USER";
			}
		});
		return collection;
	}

	@Override
	// ex) 1231344534523565757
	public String getName() {
		return oAuthAttributes.getUsername();
	}
}
