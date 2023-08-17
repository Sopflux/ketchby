package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.dao.AccountDAO_mb;
import com.example.demo.dao.MypageDAO_mb;
import com.example.demo.dao.ReservationDAO_mb;
import com.example.demo.entity.Account;
import com.example.demo.entity.Review;

import com.example.demo.entity.Account;

import com.example.demo.service.AccountService;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;
@Setter
@RestController
public class AjaxController {
	@Autowired
	private AccountDAO_mb dao_mb;

	@Autowired
	private MypageDAO_mb my_mb;
	
	@Autowired
	private ReservationDAO_mb re_mb;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AccountService as; 
	
	@GetMapping("/ajax/unfollow")
	public void unfollow(String user,String condition, HttpSession session){
		Account a = (Account)session.getAttribute("a");
		String aid = a.getAid();
		int result = 0;
		HashMap<String, String> map = new HashMap<>();
		if(condition.equals("follower")) {
			map.put("afollowaid",aid);
			map.put("aid",user);
			dao_mb.unfollow(map);
		}else {
			map.put("afollowaid",user);
			map.put("aid",aid);
			dao_mb.unfollow(map);
			
		}
		
	}
@GetMapping("/ajax/resetpwd")
public void resetpwd(String pwd, String aid) {
	HashMap< String, Object> map = new HashMap<>();
	pwd = passwordEncoder.encode(pwd);
	map.put("aid", aid);
	map.put("pwd", pwd);
	dao_mb.resetpwd(map);
}
	@GetMapping("/ajax/follow")
	public int follow(String userid,String condition ,HttpSession session) {
		// 세션에 저장된 로그인한 회원정보 가져오기
		Account a = (Account)session.getAttribute("a");
		String aid = a.getAid();
		
		HashMap<String, String> map = new HashMap<>();
		map.put("aid", aid);
		map.put("afollowaid", userid);
	
		if(condition.equals("following")) {
			// delete
			dao_mb.unfollow(map);
			
			 // 팔로워 수  
			return dao_mb.findFollow(userid).size();
		}else {
			// insert
			dao_mb.follow(map);
			return dao_mb.findFollow(userid).size();
		}
	}

	
	@GetMapping("/ajax/duplicateEmail")
	public String duplicateEmail(String email) {
		// System.out.println("duplicate Check email : "+email);
		if(as.findByEmail(email)==null) {
			return "";
		}
		return as.findByEmail(email).getEmail();
	}
	@GetMapping("/ajax/reviewOK")
	public void reviewOK(int star, String content, int clno, String aid) {
		int result = 1;
	//	System.out.println("여기왔다@! ");
		Review r = new Review(clno, content, aid, star, null, aid,null);
		my_mb.insertReview(r);
	}

	@GetMapping("/ajax/emailCheckWithEmail")
	public String emailCheckWithEmail(String email, String aid) {
		Account a = as.emailCheckWithEmail(email, aid);
		if (a == null) {
			return "";
		}
		return a.getEmail();
	}

	@GetMapping("/ajax/idCheck")
	public String idCHeck(String id) {
		if (as.findByAid(id) != null) {
			return as.findByAid(id).getAid();
		}
		return "";
	}
	

	@GetMapping("/ajax/confirmCheck")
	public String confirmCheck(HttpSession session) {
		Account a = (Account)session.getAttribute("a");
		
		return "";
	}
	
	@GetMapping("/ajax/getID")
	public String getID(String email) {
		return as.findByEmail(email).getAid();
	}
	
	@GetMapping("/ajax/getClassPeople")
	public int getClassPeople(int clno,String selectday) {
		System.out.println("get Class People동작함!");
		Map<String, Object> param = new HashMap<>();
		param.put("rsdate", selectday);
		param.put("clno", clno);

		int total = re_mb.findClPeople(param);
		int reserv = re_mb.findRePeople(param);
		
		System.out.println("total :"+total);
		System.out.println("reserv :"+reserv);
		int result = total-reserv;
		System.out.println("result: "+result);
		System.out.println("selectday: "+selectday);
		return total-reserv;
	}
	
	
	@GetMapping("/ajax/nickCheck")
	public String nickCheck(String nick) {
		String msg = "사용 가능한 닉네임입니다!";
		String check = as.findByNick(nick);
		if (check != null && !check.equals("")) {
			msg = "사용불가능한 닉네임입니다!";
		}
		System.out.println("msg:" + msg);
		return msg;
	}

}
