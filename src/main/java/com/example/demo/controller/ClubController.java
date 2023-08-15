package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.dao.BoardHotDAO;
import com.example.demo.dao.ClubDAO;
import com.example.demo.entity.Club;
import com.example.demo.entity.ClubMain;
import com.example.demo.entity.Clubapplication;
import com.example.demo.entity.Scategory;
import com.example.demo.entity.Slocation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class ClubController {
	@Autowired
	private ClubDAO clubdao;

	@GetMapping("/club/clubmain")
	public void clubmain(Model model) {
		model.addAttribute("cbno", clubdao.getNextCbNo());
	}

	@ResponseBody
	@GetMapping("/club/clubmainajax")
	public List<ClubMain> list(Model model, String keyword, String sortColum, HttpSession session,
			HttpServletRequest request) {

		if (sortColum == null || sortColum.equals("")) {
			sortColum = "new";
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("sortColum", sortColum);
		return clubdao.getClubList(map);
	}

	@GetMapping("/club/club_open")
	public void form(Model model) {
		model.addAttribute("cbno", clubdao.getNextCbNo());
	}

	@GetMapping("/club/findscano")
	@ResponseBody
	public List<Scategory> findscategory(int bcano) {
		return clubdao.findscategory(bcano);
	}

	@GetMapping("/club/findslocno")
	@ResponseBody
	public List<Slocation> findslocation(int blocno) {
		return clubdao.findslocation(blocno);
	}

	@PostMapping("/club/club_open")
	public ModelAndView send(Club c, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/club");
		System.out.println("path: " + path);
		String fname = null;
		MultipartFile uploadFile = c.getUploadFile();
		fname = uploadFile.getOriginalFilename();
		
		if (fname != null && !fname.equals("")) {
			long n = System.currentTimeMillis();
			fname = fname.replace(".", ",");
			String[] str = fname.split(",");
			System.out.println("파일명: " + fname);
			fname = str[0] + n + "." + str[1];
			c.setCbimg(fname);
		} else {
			c.setCbimg(fname);
		}

		try {
			int re=clubdao.insert(c);
			if (re == 1) { //인서트 성공 시
				if (fname!=null&&!fname.equals("")) {
					System.out.println("이미 업로드 된 같은 이름의 파일이 있습니다");
					try {
						//파일의 내용 바이트로 갖고옴
						byte[] data=uploadFile.getBytes();
						FileOutputStream fos=new FileOutputStream(path+"/"+fname);
						fos.write(data);
						fos.close();
					} catch (Exception e) {
						System.out.println("예외 발생: "+e.getMessage());
					}
				} else {
					System.out.println("업로드 파일 없음");
				}
			}
		} catch (Exception e) {
			System.out.println("클럽 인서트 실패");
		}

		ModelAndView mav = new ModelAndView("redirect:/club/clubmain");
		return mav;
	}
	
	@GetMapping(value ={"/club/club_detail/{cbno}","/club/club_detail/{cbno}/{slocno}"})
	public ModelAndView detail(@PathVariable("cbno") int cbno,@PathVariable("slocno") int slocno ) {
		ModelAndView mav=new ModelAndView("/club/club_detail");
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("cbno", cbno);
		map.put("slocno", slocno);
		
		mav.addObject("c", clubdao.findClubInfo(cbno));
		mav.addObject("list", clubdao.findRecommandClub(map));
		
		mav.addObject("cbno", cbno);
		mav.addObject("slocno", slocno);
		mav.addObject("cbapno", clubdao.getNextCbapno());
		return mav;
	}
	
	@PostMapping("/club/club_detail")
	@ResponseBody
	public String send(Clubapplication ca, HttpSession session) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("cbno", ca.getCbno());
		map.put("aid", ca.getAid());		
		int cnt=clubdao.findApplyCount(map);
		System.out.println("신청 값: "+cnt);
		
		String r="error";
	    if(cnt==0) {
	    	clubdao.insert(ca);
	    	r="success";
	    }
	    System.out.println(r);
	    return r;
	}
	
	@GetMapping("/club/club_update/{cbno}")
	public ModelAndView send(@PathVariable("cbno") int cbno) {
		ModelAndView mav=new ModelAndView("club/club_update");
		mav.addObject("cbno", cbno);
		mav.addObject("c", clubdao.findClubInfo(cbno));
		System.out.println("club dao 값: "+ clubdao.findClubInfo(cbno));
		return mav;
	}
	
	@PostMapping("/club/club_update")
	public ModelAndView update(Club c,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/club/clubmain");
		String path = request.getServletContext().getRealPath("/club");
		
		String oldFname = c.getCbimg();
	    System.out.println("oldFname: "+oldFname);
	    
	    String fname = null;
	    MultipartFile uploadFile = c.getUploadFile();
	    fname = uploadFile.getOriginalFilename();
	    System.out.println("fname: "+fname); 
	    
	    boolean fileRemove=false;
	    if(oldFname.equals("null")) {
	    	System.out.println("원래 파일을 삭제합니다");
	    	c.setCbimg("");
	    	oldFname=(String)request.getParameter("cbimg2");
	    	fileRemove=true;
	    	//원래 파일명 갖고 와서 저장
	    }
	    
	    int re=-1;
	    try {
	        if (fname != null && !fname.equals("")) {
	            long n = System.currentTimeMillis();
	            fname = fname.replace(".", ",");
	            String[] str = fname.split(",");
	            System.out.println("파일명: " + fname);
	            fname = str[0] + n + "." + str[1];

	            // 파일 내용 갖고옴
	            byte[] data = uploadFile.getBytes();

	            // 파일을 저장하기 위한 스트림을 생성합니다.
	            // 우리는 어디에 어떤 이름으로 파일을 저장해야 합니까?
	            FileOutputStream fos = new FileOutputStream(path + "/" + fname);

	            // 파일로 내용을 출력합니다.
	            fos.write(data);
	            fos.close();

	            // 업로드한 파일이름을 vo에 저장합니다.
	            c.setCbimg(fname);

	        }
            re=clubdao.update(c);
	        System.out.println("-----------------------------------------------------------------");
	        System.out.println("re "+re);
	        System.out.println("fname "+fname);
	        System.out.println("fileRemove "+fileRemove);
	        System.out.println("-----------------------------------------------------------------");
	        if((re == 1 && fname != null && !fname.equals(""))||(re==1&& fileRemove==true)) {
	        	System.out.println("파일을 삭제합닌다");
	        	System.out.println("oldFname "+oldFname);
	        	File file=new File(path+"/"+oldFname);
	        	file.delete();
	        }
	        
	        
	    } catch (Exception e) {
	        System.out.println("update 실패: " + e.getMessage());
	    }
	    return mav;
		
	}
	
	@GetMapping("/club/delete/{cbno}")
	public ModelAndView delete(@PathVariable("cbno") int cbno, HttpServletRequest request ) {
		ModelAndView mav=new ModelAndView("redirect:/club/clubmain");
		String path=request.getServletContext().getRealPath("/club");
		String fname=clubdao.findClubInfo(cbno).getCbimg();
		try {
			int re=clubdao.delete(cbno);
			if(re==1) {
				File file=new File(path+"/"+fname);
				file.delete();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return mav;
	}

}
