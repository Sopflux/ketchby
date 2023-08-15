package com.example.demo.controller;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters.StringToLocalDateTimeConverter;
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
import com.example.demo.dao.FavorDAO_mb;
import com.example.demo.dao.ImageDAO_mb;
import com.example.demo.dao.MypageDAO_mb;
import com.example.demo.entity.Account;
import com.example.demo.entity.Bcategory;
import com.example.demo.entity.Blocation;
import com.example.demo.entity.Favorite;
import com.example.demo.entity.Place;
import com.example.demo.entity.Quit;
import com.example.demo.entity.Reason;
import com.example.demo.entity.Review;
import com.example.demo.entity.Role;
import com.example.demo.entity.Scategory;
import com.example.demo.entity.Slocation;
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
	
	@Autowired
	private FavorDAO_mb favor_mb;

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
	public ModelAndView updateAccount(HttpSession session,Model model) {
		ModelAndView mav = new ModelAndView("/updateAccount");
		Account a = (Account)session.getAttribute("a");
		System.out.println("회원정보수정컨트롤러 작동함");
		List<Bcategory> list_bcategory = favor_mb.findBcategory();
		List<Blocation> list_blocation = favor_mb.findBlocation();
		List<Reason> list_reason = dao_mb.findReason();
		List<Favorite> f = favor_mb.findHobbyById(a.getAid());
		List<Place> pl = favor_mb.findPlaceById(a.getAid());

		model.addAttribute("list_bcategory", list_bcategory);
		model.addAttribute("list_blocation", list_blocation);
		model.addAttribute("list_reason", list_reason);
		
		
		
		System.out.println("updateAccount : "+a.toString());
		return mav;
	}

	
	
	@PostMapping("/updateAccount")
	public ModelAndView updateAccount(Account a,int scano1, int scano2, int slocno1, int slocno2,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/mypage2");
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
		
		List<Favorite> f = favor_mb.findHobbyById(a.getAid());
		List<Place> p = favor_mb.findPlaceById(a.getAid());
		
		Favorite f1 = new Favorite();
		Favorite f2 = new Favorite();
		
		if (scano1 != 0) {
		    f1.setAid(a.getAid());
		    f1.setScano(scano1);
		    int r1 = favor_mb.updateFavorite(scano1,f.get(0).getFno());
		}
		if (scano2 != 0) {
		    f2.setAid(a.getAid());
		    f2.setScano(scano2);
		    
		}
		
		Place p1 = new Place();
		Place p2 = new Place();
		if (slocno1 != 0) {
		    p1.setAid(a.getAid());
		    p1.setSlocno(slocno1);
		    int r3 = favor_mb.updatePlace(slocno1,p.get(0).getPno());
		}
		if (slocno2 != 0) {
		    p2.setAid(a.getAid());
		    p2.setSlocno(slocno2);
		    int r4 = favor_mb.updatePlace(slocno2,p.get(1).getPno());
		}
	
		return mav;
	}
	
	@GetMapping("/login")
	public ModelAndView loginform() {
		ModelAndView mav = new ModelAndView("/login");
		
		return mav;
	}
	
	@GetMapping("/join")
	public ModelAndView join(Model model) {

		List<Bcategory> list_bcategory = favor_mb.findBcategory();
		List<Blocation> list_blocation = favor_mb.findBlocation();

		model.addAttribute("list_bcategory", list_bcategory);
		model.addAttribute("list_blocation", list_blocation);
		ModelAndView mav = new ModelAndView("/join");
		return mav;
	}
	@GetMapping("/join/scategory")
	@ResponseBody
	public List<Scategory> scategory(int bcano, Model model) {
		System.out.println("bcano:"+bcano);
		List<Scategory> list_scategory = favor_mb.findScategory(bcano);
		return list_scategory;
		
	}
	@GetMapping("/join/slocation")
	@ResponseBody
	public List<Slocation> slocation(int blocno, Model model) {
		System.out.println("blocno:"+blocno);
		List<Slocation> list_slocation = favor_mb.findSlocation(blocno);
		return list_slocation;
		
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

	public ModelAndView join(Account a, int scano1, int scano2, int slocno1, int slocno2, HttpServletRequest request) {
//			System.out.println(a.getAid());
//			return a.getAid();

		ModelAndView mav = new ModelAndView("redirect:/join");
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
		
		System.out.println("소도시1:"+slocno1);
		System.out.println("소도시2:"+slocno2);
		System.out.println("취미1:"+scano1);
		System.out.println("취미2:"+scano2);
		Favorite f1 = new Favorite();
		Favorite f2 = new Favorite();
		
		if (scano1 != 0) {
		    f1.setAid(a.getAid());
		    f1.setScano(scano1);
		    int r1 = favor_mb.insertFavorite(f1);
		}
		if (scano2 != 0) {
		    f2.setAid(a.getAid());
		    f2.setScano(scano2);

			int r2 = favor_mb.insertFavorite(f2);
		}
		
		Place p1 = new Place();
		Place p2 = new Place();
		if (slocno1 != 0) {
		    p1.setAid(a.getAid());
		    p1.setSlocno(slocno1);
			int r3 = favor_mb.insertPlace(p1);
		}
		if (slocno2 != 0) {
		    p2.setAid(a.getAid());
		    p2.setSlocno(slocno2);
			int r4 = favor_mb.insertPlace(p2);
		}
	
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
	
	@PostMapping("/deleteAccount")
	public ModelAndView deleteAccount(Quit q, HttpSession session) {
		System.out.println("탈퇴컨트롤러 동작함!");
		ModelAndView mav = new ModelAndView("/login");
		Account a = (Account)session.getAttribute("a");
		System.out.println("reno:"+q.getReno());
		int r = dao_mb.insert(q);
		int re = dao_mb.delete(a.getAid());
		
		return mav;
	}

}
