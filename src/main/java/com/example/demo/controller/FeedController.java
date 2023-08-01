package com.example.demo.controller;

import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.FeedDAO_mb;
import com.example.demo.dao.FeedImgDAO_mb;
import com.example.demo.entity.Account;
import com.example.demo.entity.Feed;
import com.example.demo.entity.Image;
import com.example.demo.service.AccountService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.Setter;

@Setter
@Controller
public class FeedController {
	
	@Autowired
	private FeedImgDAO_mb dao;
	
	@Autowired
	private AccountService as;
	
	@Autowired 
	private FeedDAO_mb daof;
	/*
	@RequestMapping("/feed/insert")
	@ResponseBody
	public String insert () {
		System.out.println("컨트롤러 동작함");
		return "ok";
	}*/
	
	
	@RequestMapping(value = "/feed/insert", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView insert(Feed f,Image im,@RequestParam("uploadFile") MultipartFile[] uploadFiles, HttpServletRequest request) {
		System.out.println("컨드롤러 감");
		
		int maxNo = daof.findFeedMax();
		f.setFno(maxNo);
		im.setFno(maxNo);
		String path = request.getServletContext().getRealPath("feed");
		System.out.println("path:"+path);
		ModelAndView mav = new ModelAndView("redirect:/feed/feed");
		String imgname = null;
		
		
		 try {
		        int re1 = daof.insertFeed(f);
		     // 파일 배열에 들어있는 파일들 개별적으로 인서트 수행
		        for (MultipartFile file : uploadFiles) {
		        	
		        	imgname = file.getOriginalFilename();
		        	im.setImgname(imgname);
		        	
		            int re2 = daof.insertFeedImg(im);
		            if(imgname != null && !imgname.equals("")) {
		            	try {
		            		byte []data=file.getBytes();
		            		FileOutputStream fos = new FileOutputStream(path+"/"+imgname);
		            		fos.write(data);
		            		fos.close();
		            		
		            	}catch(Exception e) {
		            		System.out.println("파일업로드중예외발생:"+e.getMessage());
		            	}
		            }
		        }
		        
		     
		    } catch (Exception e) {
		        System.err.println("피드 인서트 중 예외 발생: " + e.getMessage());
		    }
		 
		 return mav;
	}
	
	@GetMapping("/feed/feed")
	public void feed(Model model, HttpSession session, HttpServletRequest request){
		Account str2= (Account)request.getSession().getAttribute("a");
		//Account str = (Account)session.getAttribute("a");
		System.out.println(str2.getAid());
		System.out.println(str2);
		model.addAttribute("list", dao.findAllFeedIMg());
	}
	
}

