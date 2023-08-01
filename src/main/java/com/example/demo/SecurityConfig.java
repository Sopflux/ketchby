package com.example.demo;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.AccountService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity // 시큐리티 환경설정! 
public class SecurityConfig {
	
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests().requestMatchers("/join","/login","/","/nickCheck","/emailCheck","/joinOK","/kakaologin/**","/join2","/list","/duplicateEmail/**","/getID/**","/idCheck/**","/emailCheckWithEmail/**").permitAll()
		.requestMatchers("/admin/**").hasRole("admin")
		.anyRequest().authenticated()
		.and().csrf().ignoringRequestMatchers("/join");
		;
		
		
		http.formLogin().loginPage("/login").permitAll()
		.successHandler(successHandler);
		
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)
		.logoutSuccessUrl("/login");
		
		http.httpBasic();
		
		http.csrf().disable();
		return http.build();
	}
	
	 private AuthenticationSuccessHandler successHandler = new AuthenticationSuccessHandler() {
		
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			HttpSession session = request.getSession();
			AccountService as = new AccountService();
			String loginId = authentication.getName();
			System.out.println("로그인 아이디 : "+loginId);
			System.out.println("findByAid : "+as.findByAid(loginId));
			session.setAttribute("a", as.findByAid(loginId));
			response.sendRedirect("/list");
			
		}
	};
	
}