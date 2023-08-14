package com.example.demo;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import static com.example.demo.JwtConstants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	AccountService as;
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		 System.out.println("kakao success handler");
         try {
            System.out.println("id : "+authentication.getName());
            String aid = authentication.getName();
            HttpSession session = request.getSession();
            Account a = as.findByAid(aid);
            if (a.getNick() == null) {
            	a.setNick("");
            	session.setAttribute("a", a);
            	response.sendRedirect("/updateAccount");
            }else {
            	a.setPwd(null);
            	session.setAttribute("a", a);
            	response.sendRedirect("/mypage2");
            	
            }
         } catch (Exception e) {
            System.out.println("error : "+e.getMessage());
         
      }
   }
    }


