package com.example.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Setter
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	AccountService as;
	
	@Autowired
	CustomSuccessHandler handler;
	
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
	
	http.oauth2Login()
	.loginPage("/login")
	.successHandler(handler)
         .userInfoEndpoint()

		.userService(customOAuth2UserService); // 커스텀한 서비스에서 정보 처리
		
		
		http.authorizeHttpRequests().requestMatchers("/join","/login","/","/nickCheck","/emailCheck","/joinOK","/kakaologin","/join2","/list","/duplicateEmail/**","/getID/**","/idCheck/**","/emailCheckWithEmail/**","/image/**").permitAll()
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().csrf().ignoringRequestMatchers("/join");
		
		
 		
		http.formLogin().loginPage("/login").permitAll()
		.successHandler(successHandler);
		
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)
		.logoutSuccessUrl("/login");
		
		http.httpBasic();
		
		http.csrf().disable().headers().frameOptions().disable(); // post 가능하게 설정 
		return http.build();
	}
	
	 private AuthenticationSuccessHandler successHandler = new AuthenticationSuccessHandler() {
		
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			HttpSession session = request.getSession();
			System.out.println("이거 동작!");
			String loginId = authentication.getName();
			System.out.println("로그인 아이디 : "+loginId);
			System.out.println("findByAid : "+as.findByAid(loginId));
			
			session.setAttribute("a", as.findByAid(loginId));
		
			response.sendRedirect("/mypage2");
			
		}
	};
	
}