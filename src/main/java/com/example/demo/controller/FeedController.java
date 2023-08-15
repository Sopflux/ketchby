package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.example.demo.entity.FeedIMG;
import com.example.demo.entity.Feedcommentary;
import com.example.demo.entity.Image;
import com.example.demo.entity.Likes;
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
	public String insert(Feed f,Image im,@RequestParam("uploadFile") MultipartFile[] uploadFiles, HttpServletRequest request) {
		System.out.println("컨드롤러 감");
		
		int maxNo = daof.findFeedMax();
		f.setFno(maxNo);
		im.setFno(maxNo);
		String path = request.getServletContext().getRealPath("feed");
		System.out.println("fcontent:"+f.getFcontent());
		System.out.println("aid:"+f.getAid());
		System.out.println("path:"+path);
		String imgname = null;
		
		
		 try {
		        int re1 = daof.insertFeed(f);
		     // 파일 배열에 들어있는 파일들 개별적으로 인서트 수행
		        for (MultipartFile file : uploadFiles) {
		        	
		        	imgname = file.getOriginalFilename();
		        	im.setImgname(imgname);
		        	System.out.println(imgname);
		        	
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
		 
		 return "ok";
	}
	@RequestMapping(value = "/feed/update", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView update(Feed f,Image im,@RequestParam("uploadFile") MultipartFile[] uploadFiles, HttpServletRequest request) {
		System.out.println("컨드롤러 감");
		
		List<String> old = daof.imgFindByFno(f.getFno());
		String path = request.getServletContext().getRealPath("feed");
		System.out.println("path:"+path);
		ModelAndView mav = new ModelAndView("redirect:/feed/feed");
		String imgname = null;
		
		
		try {
			int re1 = daof.updateFeed(f);
			// 파일 배열에 들어있는 파일들 개별적으로 인서트 수행
			for (MultipartFile file : uploadFiles) {
				
				int re2 = daof.deleteFeedImg(f.getFno());
				
				imgname = file.getOriginalFilename();
				im.setImgname(imgname);
				im.setFno(f.getFno());
				im.setAid(f.getAid());
				System.out.println("fno:"+f.getFno());
				daof.insertFeedImg(im);
				if(imgname != null && !imgname.equals("")) {
					try {
						byte []data=file.getBytes();
						FileOutputStream fos = new FileOutputStream(path+"/"+imgname);
						fos.write(data);
						fos.close();
						
					}catch(Exception e) {
						System.out.println("파일업로드중예외발생:"+e.getMessage());
					}
					
					for(String oldFname:old) {
						File ofile = new File(path+"/"+oldFname);
						ofile.delete();
					}
				}
			}
			
			
		} catch (Exception e) {
			System.err.println("피드 수정 중 예외 발생: " + e.getMessage());
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/feed/delete", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView delete(int fno,HttpServletRequest request) {
		System.out.println("컨드롤러 감");
		
		List<String> old = daof.imgFindByFno(fno);
		String path = request.getServletContext().getRealPath("feed");
		System.out.println("path:"+path);
		ModelAndView mav = new ModelAndView("redirect:/feed/feed");
		String imgname = null;
		
		
		try {
			int re = daof.deleteFeed(fno);
			for(String oldfname:old) {
				if(re==1) {
					if(oldfname!=null&&!oldfname.equals("")) {
						File file = new File(path+"/"+oldfname);
						file.delete();
					}
				}
			}
			
			
		} catch (Exception e) {
			System.err.println("피드삭제 중 예외 발생: " + e.getMessage());
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/feed/insertLike", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertLike(Likes l, Model model) {
		System.out.println("컨드롤러 감");
		System.out.println("fno:"+l.getFno());
		Map<String, Object> response = new HashMap<>();
		
		
		try {
			int re = daof.insertLike(l);
			
		}catch (Exception e) {
			System.out.println("피드 인서트 중 예외 발생: " + e.getMessage());
		}
		int cntLike = daof.cntLike(l.getFno());
		 response.put("cntLike", cntLike);
		 response.put("userLike", daof.userCntLike(l.getFno(), l.getAid()));
	     response.put("success", true); // 성공 여부를 추가
		model.addAttribute("list", dao.findAllFeedIMg());
		
		return response;
	}
	
	@RequestMapping(value = "/feed/deleteLike", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteLike(int fno, String aid, Model model) {
		System.out.println("컨드롤러 감");
		Map<String, Object> response = new HashMap<>();
		
		try {
			int re = daof.deleteLike(fno,aid);
			
		}catch (Exception e) {
			System.out.println("좋아요 취소 중 예외 발생: " + e.getMessage());
		}
		int cntLike = daof.cntLike(fno);
		response.put("cntLike", cntLike);
		response.put("userLike", daof.userCntLike(fno, aid));
		response.put("success", true); // 성공 여부를 추가
		model.addAttribute("list", dao.findAllFeedIMg());
		
		return response;
	}
	
	@RequestMapping(value = "/feed/insertComment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertComment(Feedcommentary fco,Model model) {
		System.out.println("컨드롤러 감");
		Map<String, Object> response = new HashMap<>();
		
		try {
			int re = daof.insertComment(fco);
			
		}catch (Exception e) {
			System.out.println("댓글 인서트 중 예외 발생: " + e.getMessage());
		}
		int cntComment = daof.cntComment(fco.getFno());
		List<Feedcommentary> list = daof.findComment(fco.getFno());
		 response.put("cntComment", cntComment);
		 response.put("list", list);
	     response.put("success", true); // 성공 여부를 추가
		model.addAttribute("list", dao.findAllFeedIMg());
		
		return response;
	}
	
	@GetMapping("/feed/feed")
	public void feed(Model model, HttpSession session, HttpServletRequest request){
		Account str= (Account)request.getSession().getAttribute("a");
		//Account str = (Account)session.getAttribute("a");
		
		List<FeedIMG> list = dao.findAllFeedIMg();
		for(FeedIMG f:list) {
			f.setUserLike(daof.userCntLike(f.getFno(), str.getAid()));
			System.out.println("cnt:"+daof.userCntLike(f.getFno(), str.getAid()));
		}
		
		model.addAttribute("list", list);
	}
	@GetMapping("/feed/myFeed")
	public String myFeed(Model model, HttpSession session, HttpServletRequest request){
		Account str= (Account)request.getSession().getAttribute("a");
		//Account str = (Account)session.getAttribute("a");
		System.out.println("마이피드 동작함");
		List<FeedIMG> list = dao.findMyFeedIMg(str.getAid());
		for(FeedIMG f:list) {
			f.setUserLike(daof.userCntLike(f.getFno(), str.getAid()));
			System.out.println("cnt:"+daof.userCntLike(f.getFno(), str.getAid()));
		}
		
		model.addAttribute("list",list);
		return "/feed/feed";
	}
	@GetMapping("/feed/followFeed")
	public String followFeed(Model model, HttpSession session, HttpServletRequest request){
		Account str= (Account)request.getSession().getAttribute("a");
		//Account str = (Account)session.getAttribute("a");
		System.out.println("마이피드 동작함");
		List<FeedIMG> list = dao.findFollowFeedIMg(str.getAid());
		for(FeedIMG f:list) {
			f.setUserLike(daof.userCntLike(f.getFno(), str.getAid()));
			System.out.println("cnt:"+daof.userCntLike(f.getFno(), str.getAid()));
		}
		
		model.addAttribute("list",list);
		return "/feed/feed";
	}
	
}

