package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AccountDAO;
import com.example.demo.dao.AccountDAO_mb;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;

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

	@GetMapping("/kakaologin/{name}/{email}")
	public ModelAndView kakaologin(@PathVariable String name, @PathVariable String email, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/list");
		System.out.println("카카오 로그인 작동!");
		System.out.println("name:" + name);
		System.out.println("email:" + email);
		session.setAttribute("name", name);
		session.setAttribute("email", email);
		if (as.findByEmail(email) == null) {
			mav.setViewName("redirect:/join");
		}
		return mav;
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

	@GetMapping("/login")
	public void login() {
//			dao.save(new Account(6013, PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234"), "99/07/09","qlqlql8448@naver.com","김덕수","킹덕수","010-5004-8448","ahyeon.jpg","lv.1","23/07/05"));
	}

	@GetMapping("/mypage2")
	public void mypage() {
//			dao.save(new Account(6013, PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234"), "99/07/09","qlqlql8448@naver.com","김덕수","킹덕수","010-5004-8448","ahyeon.jpg","lv.1","23/07/05"));
	}

}
