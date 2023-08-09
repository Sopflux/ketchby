package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AccountDAO;
import com.example.demo.dao.AccountDAO_mb;
import com.example.demo.dao.ImageDAO_mb;
import com.example.demo.dao.MypageDAO_mb;
import com.example.demo.entity.Account;
import com.example.demo.entity.Review;
import com.example.demo.entity.Role;
import com.example.demo.service.AccountService;
import com.example.demo.service.KakaoService;
import com.google.api.client.http.HttpRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Setter
@Controller

public class AccountController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AccountService as;
	
	@Autowired
	private ImageDAO_mb image_mb;
	
	@Autowired
	private MypageDAO_mb my_mb;
	
	@Autowired
	private AccountDAO_mb dao_mb;

	@GetMapping("/list")
	public void list(Model model, HttpSession session) {
		model.addAttribute("list", as.findAll());
		/*
		// 로그인한 회원의 정보를 가져오기 위해 authentication 객체 생성
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// authenticatio 객체를 통해서 로그인한 회원의 정보를 가져옴
		User user = (User) authentication.getPrincipal();

		// user를 통해서 로그인한 회원의 아이디를 가져옴
		String loginId = user.getUsername();

		session.setAttribute("a", as.findByAid(loginId));
		*/
	}
	
	@GetMapping("/updateAccount")
	public ModelAndView updateAccount(HttpSession session) {
		ModelAndView mav = new ModelAndView("/updateAccount");
		Account a = (Account)session.getAttribute("a");
		System.out.println("updateAccount : "+a.toString());
		return mav;
	}

	
	@PostMapping("/updateAccount")
	public ModelAndView updateAccount(Account a,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/updateKakaoAccount");
		String path = request.getServletContext().getRealPath("/image");
		System.out.println("path:" + path);
		String fname = null;
		MultipartFile uploadFile = a.getUploadFile();
		fname = uploadFile.getOriginalFilename()+System.currentTimeMillis();

		if (fname != null && !fname.equals("")) {
			try {
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
			} catch (Exception e) {
				System.out.println("예외발생:" + e.getMessage());
			}
		} else {
			fname = "";
		}
		a.setImg(fname);
		a.setPwd(passwordEncoder.encode(a.getPwd()));
		a.setRole(Role.USER);
		System.out.println("controller A : "+a.toString());
		int r = as.update(a);
		System.out.println("update email : "+a.getEmail());
		return mav;
	}
	
	@GetMapping("/login")
	public ModelAndView loginform() {
		ModelAndView mav = new ModelAndView("/login");
		
		return mav;
	}
	
	@GetMapping("/join")
	public ModelAndView join() {
		ModelAndView mav = new ModelAndView("/join");

		return mav;
	}

	@RequestMapping("/joinOK")
	@ResponseBody
	public String joinOK() {
		return "OK";
	}


	private UserDetails createUserDetails(String id) {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	    System.out.println("롤부여했니 ?");
	    return new User(id, as.findByAid(id).getPwd(), authorities);
	}

	@PostMapping("/join")

	public ModelAndView join(Account a, HttpServletRequest request) {
//			System.out.println(a.getAid());
//			return a.getAid();

		ModelAndView mav = new ModelAndView("redirect:/join");
		String path = request.getServletContext().getRealPath("/images");
		System.out.println("path:" + path);
		String fname = null;
		MultipartFile uploadFile = a.getUploadFile();
		fname = uploadFile.getOriginalFilename();

		if (fname != null && !fname.equals("")) {
			try {
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				FileCopyUtils.copy(uploadFile.getBytes(), fos);
				fos.close();
			} catch (Exception e) {
				System.out.println("예외발생:" + e.getMessage());
			}
		} else {
			fname = "";
		}
		a.setImg(fname);
		a.setPwd(passwordEncoder.encode(a.getPwd()));
		as.save(a);
		mav.setViewName("redirect:/login");
		return mav;

	}
	@GetMapping("/review")
	public void review(int clno, Model model) {
		model.addAttribute("rc", my_mb.findClassInfo(clno));
	}
	@PostMapping("/review")
	public ModelAndView insertReview(Review r) {
		ModelAndView mav = new ModelAndView("redirect:/mypage");
		my_mb.insertReview(r);
		return mav;
				
	}
	
	@GetMapping("/reviewOK")
	public int reviewOK(int star, String content, int clno, String aid) {
		int result = 0;
		System.out.println("여기왔다@! ");
		Review r = new Review(clno, content, aid, star, null, aid);
		result = my_mb.insertReview(r);
		return result;
	}

	@GetMapping("/mypage2")
	public void mypage(HttpSession session,Model model) {
		Account a = (Account)session.getAttribute("a");
		String email = a.getEmail();
		String aid = a.getAid();
		model.addAttribute("following",  dao_mb.findFollowing(email));
		model.addAttribute("follow",  dao_mb.findFollow(email));
		model.addAttribute("image", image_mb.findFeedImage(aid));
		model.addAttribute("cd",my_mb.findReservation(aid));
		model.addAttribute("rlist",my_mb.findReview(aid));
		model.addAttribute("clist",my_mb.findClub(aid));
		model.addAttribute("llist",my_mb.findLike(aid));
		
		
	}

}
