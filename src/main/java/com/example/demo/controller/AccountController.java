package com.example.demo.controller;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AccountDAO;
import com.example.demo.dao.AccountDAO_mb;
import com.example.demo.dao.ImageDAO_mb;
import com.example.demo.dao.MypageDAO_mb;
import com.example.demo.entity.Account;
import com.example.demo.entity.Follow;
import com.example.demo.entity.Review;
import com.example.demo.entity.Role;
import com.example.demo.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Setter
@Controller

public class AccountController {
	@Autowired
	private AccountDAO dao;
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
	
	
	@GetMapping("/account/userpage")
	public ModelAndView userpage(String aid,HttpSession session) {
		ModelAndView mav = new ModelAndView("/account/userpage");
		Account a = (Account)session.getAttribute("a");
		String userid = a.getAid(); // 로그인한 회원의 아이디
		if(userid.equals(aid)) {
			mav.setViewName("/account/mypage");
			return mav;
		}
		HashMap<String, String> map = new HashMap<>();
		map.put("aid", userid);
		map.put("afollowaid", aid);
		
		// 팔로우 하고 있는 상태면 true, 아니면 false 반환 
		boolean follow = dao_mb.checkFollow(map);
		
		// 기본적으로 팔로우 안했다고 가정하고 팔로우한 상태면 following 반환
		mav.addObject("follow_con","notfollow");
		
		if (follow) {
			mav.addObject("follow_con","following");
		}
		
		
		
		// 페이지 주인 정보 불러오기 
		mav.addObject("user", dao.findByAid(aid));
		mav.addObject("image", image_mb.findFeedImage(aid)); // 피드 게시물 
		mav.addObject("cl", dao_mb.findOpenClass(aid));
		mav.addObject("clist", dao_mb.findOpenClub(aid));
		mav.addObject("rlist", dao_mb.findAReview(aid));
		List<Follow> follower_list = dao_mb.findFollow(aid);
		List<Follow> following_list = dao_mb.findFollowing(aid);
		mav.addObject("following_cnt", following_list.size());
		mav.addObject("follower_cnt", follower_list.size());
		mav.addObject("following",  following_list);
		mav.addObject("follower",  follower_list);
		
		return mav;
	}
	
	
	@GetMapping("/account/updateAccount")
	public ModelAndView updateAccount(HttpSession session) {
		ModelAndView mav = new ModelAndView("/account/updateAccount");
		Account a = (Account)session.getAttribute("a");
		// System.out.println("updateAccount : "+a.toString());
		return mav;
	}

	
	@PostMapping("/account/updateAccount")
	public ModelAndView updateAccount(Account a,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/account/updateKakaoAccount");
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
	//	System.out.println("controller A : "+a.toString());
		int r = as.update(a);
	//	System.out.println("update email : "+a.getEmail());
		return mav;
	}
	
	@GetMapping("/account/login")
	public ModelAndView loginform() {
		ModelAndView mav = new ModelAndView("/account/login");
		
		return mav;
	}
	
	@GetMapping("/account/join")
	public ModelAndView join() {
		ModelAndView mav = new ModelAndView("/account/join");

		return mav;
	}

	@RequestMapping("/account/joinOK")
	@ResponseBody
	public String joinOK() {
		return "OK";
	}


	private UserDetails createUserDetails(String id) {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	   //  System.out.println("롤부여했니 ?");
	    return new User(id, as.findByAid(id).getPwd(), authorities);
	}

	@PostMapping("/account/join")

	public ModelAndView join(Account a, HttpServletRequest request) {
//			System.out.println(a.getAid());
//			return a.getAid();

		ModelAndView mav = new ModelAndView("redirect:/account/join");
		String path = request.getServletContext().getRealPath("/image");
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
		LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
        String regdate = today.format(formatter);
		a.setRole(Role.USER);
		a.setRegdate(regdate);
		a.setLevel_("Lv.1");
		a.setImg(fname);
		a.setPwd(passwordEncoder.encode(a.getPwd()));
		as.save(a);
		mav.setViewName("redirect:/account/login");
		return mav;

	}
	@GetMapping("/account/review")
	public void review(int clno, Model model) {
		model.addAttribute("rc", my_mb.findClassInfo(clno));
		System.out.println(my_mb.findClassInfo(clno));
		System.out.println("cltitle:"+my_mb.findClassInfo(clno).getCltitle());
		System.out.println("cltitle:"+my_mb.findClassInfo(clno).getCltitle());
	}
	@PostMapping("/account/review")
	public ModelAndView insertReview(Review r) {
		ModelAndView mav = new ModelAndView("redirect:/account/mypage");
		my_mb.insertReview(r);
		return mav;
				
	}
	

	@GetMapping("/account/mypage")
	public void mypage(HttpSession session,Model model) {
		Account a = (Account)session.getAttribute("a");
		String aid = a.getAid();
		List<Follow> follower_list = dao_mb.findFollow(aid);
		List<Follow> following_list = dao_mb.findFollowing(aid);
		model.addAttribute("following_cnt", following_list.size());
		model.addAttribute("follower_cnt", follower_list.size());
		model.addAttribute("following",  following_list);
		model.addAttribute("follower",  follower_list);
		model.addAttribute("image", image_mb.findFeedImage(aid));
		model.addAttribute("cd",my_mb.findReservation(aid));
		model.addAttribute("rlist",my_mb.findReview(aid));
		model.addAttribute("clist",my_mb.findClub(aid));
		model.addAttribute("llist",my_mb.findLike(aid));
		model.addAttribute("confirm",my_mb.findConfirm(aid));
		
		
	}

}
