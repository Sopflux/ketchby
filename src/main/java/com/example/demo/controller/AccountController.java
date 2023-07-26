package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AccountDAO;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Setter
@Controller
public class AccountController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AccountService as;

	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list", as.findAll());
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
