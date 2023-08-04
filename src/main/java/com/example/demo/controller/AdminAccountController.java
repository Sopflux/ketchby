package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.dao.AdminAccountDAO;
import com.example.demo.entity.Account;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@Setter
public class AdminAccountController {
	@Autowired
	public AdminAccountDAO a_dao;
	
	@PostMapping("/admin/user/insert")
	@ResponseBody
	public String insertAccount(HttpServletRequest request) {
		String aid = request.getParameter("aid");
		String name_ = request.getParameter("name_");
		String level_ = request.getParameter("level_");
		String nick = request.getParameter("nick");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		MultipartFile uploadFile = ((MultipartHttpServletRequest) request).getFile("uploadFile");
		Account a = new Account();

		a.setAid(aid);
		a.setName_(name_);
		a.setLevel_(level_);
		a.setNick(nick);
		a.setEmail(email);
		a.setPwd(pwd);
		a.setUploadFile(uploadFile);
		
		String path = request.getServletContext().getRealPath("profile");
		
		//일단 파일명에 "" 설정
		String img = null;
		
		//업로드한 파일명을 img 변수에 저장
		img = uploadFile.getOriginalFilename();
		
		if(img != null && !img.equals("")) {
			//업로드한 파일이 있다면 그 파일명을 설정
			//중복된 파일 이름을 피하기 위해 임의의 난수를 발생하여 파일이름 뒤에 붙여준다.
			long n = System.currentTimeMillis();
			
			String fname1 = img.substring(0, img.lastIndexOf("."));
			String fname2 = img.substring(img.lastIndexOf("."));
			
			img = fname1 + n + fname2;
			System.out.println("변환 후 img: "+img);
		}
		
		a.setImg(img);
		
		//비밀번호 인코딩하기
		a.setPwd(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(a.getPwd()));
		
		try {
			int re = a_dao.insertAccount(a);
			System.out.println("re: "+re);
			//db에 insert 성공했을 시 파일을 복사
			if(re == 1) {
				//만약 업로드 파일이 존재한다면
				if(img != null && !img.equals("")) {
					
					System.out.println("업로드 파일 있음");
					try {
						//파일의 내용을 바이트로 가지고 옴
						byte[] data = uploadFile.getBytes();
						
						//파일을 출력하기 위한 스트림을 생성
						FileOutputStream fos = new FileOutputStream(path+"/"+img);
						
						//파일에 출력
						fos.write(data);
						fos.close();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("insertAccount first error: "+e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertAccount second error: "+ e.getMessage());
		}
		return "추가에 성공하였습니다.";
	}
	
	@PostMapping("/admin/user/update")
	@ResponseBody
	public String updateAccount(HttpServletRequest request) {
		
		String str = "수정 성공했습니다.";
		
		Account a = a_dao.findByAid(request.getParameter("aid"));
		
		String aid = request.getParameter("aid");
		String name_ = request.getParameter("name_");
		String level_ = request.getParameter("level_");
		String email = request.getParameter("email");
		String nick = request.getParameter("nick");
		MultipartFile uploadFile = ((MultipartHttpServletRequest) request).getFile("uploadFile");

		System.out.println("uploadFile: "+uploadFile);
		
		a.setAid(aid);
		a.setName_(name_);
		a.setLevel_(level_);
		a.setNick(nick);
		a.setEmail(email);
		a.setUploadFile(uploadFile);
		

		String path = request.getServletContext().getRealPath("profile");
		System.out.println("path: "+path);
		
		//원래 사진 이름 저장할 변수
		String oldImg = a.getImg();
		System.out.println("oldImg: "+oldImg);
		
		//업로드한 파일 객체 담아오기
		uploadFile = a.getUploadFile();
		
		//업로드한 파일명을 담기 위한 변수
		String img = null;
		
		//업로드한 파일 이름 저장
		img = uploadFile.getOriginalFilename();
		
		if(img != null && !img.equals("")) {
			//중복된 파일명을 피하기 위해 임의의 난수를 발행하여 파일명 뒤에 붙여줌
			long n = System.currentTimeMillis();
			
			String fname1 = img.substring(0, img.lastIndexOf("."));
			String fname2 = img.substring(img.lastIndexOf("."));
			
			img = fname1 + n + fname2;
			a.setImg(img);
			System.out.println("img: "+img);
			
			//파일의 내용을 가지고 옴
			try {
				int re = a_dao.updateAccount(a);
				
				if(re==1)
					if(img!=null && !img.equals("")) {
					try {
						byte[] data = uploadFile.getBytes();
						
						//파일을 저장하기 위한 스트림 생성
						FileOutputStream fos = new FileOutputStream(path+"/"+img);
						
						//파일로 내용을 출력
						fos.write(data);
						fos.close();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("updateAccount first error: "+e.getMessage());
					}
				}
				File file = new File(path+"/"+oldImg);
				System.out.println("path: "+path+"/"+oldImg);
				file.delete();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("updateAccount second error: "+e.getMessage());
				str = "수정 실패하였습니다.";
			}
		}
		return str;
	}
	
	@PostMapping("admin/user/delete")
	@ResponseBody
	public String deleteAccount(@RequestParam(value="aid")String aid, HttpServletRequest request) {
		String str = "삭제 성공했습니다.";
		//이미지 실제 경로 가져오기
		String path = request.getServletContext().getRealPath("profile");
		
		//삭제할 이미지 파일명 저장
		String fname = a_dao.findByAid(aid).getImg();
		
		try {
			int re = a_dao.deleteAccount(aid);
			if(re == 1) {
				File file = new File(path+"/"+fname);
				file.delete();
			}
		} catch (Exception e) {
			// TODO: handle exception
			str = "삭제에 실패하였습니다.";
		}
		
		return str;
	}
	
	@RequestMapping("/admin/user/detail")
	@ResponseBody
	public Account detailAccount(String aid) {
		Account a = a_dao.findByAid(aid);
		return a;
	}
	
	@GetMapping("/admin/user")
	public String list(Model model, @RequestParam(required=false, defaultValue = "1") int pageNum
			, @RequestParam(required = false, defaultValue = "all") String column
			, @RequestParam(required = false, defaultValue = "") String keyword) {
		
		int start = (pageNum-1)*a_dao.userPageSize+1;
		int end = start + a_dao.userPageSize-1;
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("column", column);
		map.put("keyword", keyword);
		map.put("pageNum", pageNum);
		
		//검색어, 검색 칼럼 상태 유지
		model.addAttribute("column", column);
		model.addAttribute("keyword", keyword);
		model.addAttribute("list", a_dao.findAll(map));
		model.addAttribute("totalPage", a_dao.userTotalPage);
		model.addAttribute("member", a_dao.userTotalRecord);
		return "/admin/user";
	}
	
}
