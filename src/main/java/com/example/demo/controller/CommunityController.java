package com.example.demo.controller;

import java.io.File;

import java.io.FileOutputStream;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AccountDAO_mb;
import com.example.demo.dao.BoardDAO;
import com.example.demo.dao.BoardHotDAO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comments;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class CommunityController {
	public int pageSIZE = 20;
	public int totalRecord;
	public int totalPage;

	@Autowired
	private BoardHotDAO boardhotdao;

	@Autowired
	private BoardDAO boarddao;

	@GetMapping(value = { "/community/community/{pageNum}", "/community/community/{pageNum}/{searchColumn}/",
			"/community/community/{pageNum}/{searchColumn}/{keyword}" })
	public String hotlist(Model model, @PathVariable("pageNum") int pageNum,
			@PathVariable(required = false) String searchColumn, @PathVariable(required = false) String keyword,
			HttpSession session, HttpServletRequest request)
	{
		if (keyword == null && session.getAttribute("keyword") != null) {
			keyword = (String) session.getAttribute("keyword");
			searchColumn = (String) session.getAttribute("searchColumn");
		}
		if (keyword == null) {
			keyword = "";
			searchColumn = "btitle";
		}
		HashMap<String, Object> map = new HashMap<>();

		map.put("searchColumn", searchColumn);
		map.put("keyword", keyword);

		totalRecord = boarddao.getTotalRecord(map);
		totalPage = (int) Math.ceil(totalRecord / (double) pageSIZE);

		int start = (pageNum - 1) * pageSIZE + 1;
		int end = start + pageSIZE - 1;

		map.put("start", start);
		map.put("end", end);


		model.addAttribute("hotlist", boardhotdao.findHotAll());
		model.addAttribute("list", boarddao.findAll(map));

		model.addAttribute("totalPage", totalPage); // 수정: 페이징 처리를 위해 전체 페이지 수도 전달

		session.setAttribute("keyword", keyword);
		session.setAttribute("searchColumn", searchColumn);

		return "/community/community";
	}

	@GetMapping("/community/community_open")
	public void form(Model model) {
		model.addAttribute("bno", boarddao.getNextNo());
	}

	@PostMapping("/community/community_open")
	public ModelAndView send(Board b, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/board");
		System.out.println("path:" + path);
		String fname = null;
		MultipartFile uploadFile = b.getUploadFile();
		fname = uploadFile.getOriginalFilename();

		if (fname != null && !fname.equals("")) {
			long n = System.currentTimeMillis();
			fname = fname.replace(".", ",");
			String[] str = fname.split(",");
			System.out.println("파일명: " + fname);
			fname = str[0] + n + "." + str[1];
			b.setBfname(fname);
		}else {
			b.setBfname(fname);
		}
		
		try {
			System.out.println("인서트 완료");
			int re = boarddao.insert(b);
			// db에 insert를 성공했을 때 파일을 복사
			if (re == 1) {
				// 만약 업로드파일이 있다면
				if (fname != null && !fname.equals("")) {
					System.out.println("업로드 파일이 있어요!");
					try {
						// 파일의 내용을 바이트로 갖고 옵니다.
						byte[] data = uploadFile.getBytes();

						// 파일을 출력하기 위한 스트림을 생성합니다.
						FileOutputStream fos = new FileOutputStream(path + "/" + fname);

						// 파일에 출력합니다
						fos.write(data);
						fos.close();

					} catch (Exception e) {
						System.out.println("예외발생:" + e.getMessage());
					}
				} else {
					System.out.println("업로드 파일이 없어요!");
				}
			}

		} catch (Exception e) {
			System.out.println("실패");
		}

		ModelAndView mav = new ModelAndView("redirect:/community/community/1");
		return mav;
	}

	@GetMapping("/community/community_detail/{bno}")
	public ModelAndView detail(@PathVariable("bno") int bno) {
		System.out.println("글번호:" + bno);
		ModelAndView mav = new ModelAndView("/community/community_detail");
		mav.addObject("b", boarddao.findByNo(bno));
		mav.addObject("list", boarddao.findByComments(bno));
		mav.addObject("cono", boarddao.getNextCommentsNo());
		mav.addObject("bno", bno);
		return mav;
	}

	@GetMapping("/community/community_detail/listcomment")
	@ResponseBody
	public List<Comments> listCommnets(int bno) {
		System.out.println(boarddao.findByComments(bno));
		return boarddao.findByComments(bno);

	}

	@GetMapping("/community/community_detail/ajax")
	@ResponseBody
	public String insertComments(String cocontent, int cono, String aid, int bno) {
		Comments c = new Comments();
		c.setCocontent(cocontent);
		c.setCono(cono);
		c.setAid(aid);
		c.setBno(bno);
		int re = boarddao.insertComments(c);
		System.out.println("댓글 insert 완료");
		return re + "";
	}

	@GetMapping("/community/delete/{bno}")
	public ModelAndView delete(@PathVariable("bno") int bno, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/community/community/1");
		String path = request.getServletContext().getRealPath("/board");
		String fname = boarddao.findByNo(bno).getBfname();
		try {
			int re = boarddao.delete(bno);
			boarddao.deletecommentsByboard(bno);
			if (re == 1) {
				File file = new File(path + "/" + fname);
				file.delete();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return mav;
	}

	@GetMapping("/community/community_update/{bno}")
	public ModelAndView update(@PathVariable int bno, Model model) {
		ModelAndView mav = new ModelAndView("/community/community_update");
		mav.addObject("b", boarddao.findByNo(bno));
		mav.addObject("bno",bno);
		return mav;
	}

	@PostMapping("/community/community_update")
	public ModelAndView updatesubmit(Board b, HttpServletRequest request) {
	    ModelAndView mav = new ModelAndView("redirect:/community/community/1");
	    String path = request.getServletContext().getRealPath("/board");
	    
	    
	    String oldFname = b.getBfname();
	    System.out.println("oldFname: "+oldFname);
	    
	    String fname = null;
	    MultipartFile uploadFile = b.getUploadFile();
	    fname = uploadFile.getOriginalFilename();
	    System.out.println("fname: "+fname);
	    
	    
	    boolean fileRemove=false;
	    if(oldFname.equals("null")) {
	    	System.out.println("원래 파일을 삭제합니다");
	    	b.setBfname("");
	    	oldFname=(String)request.getParameter("bfname2");
	    	fileRemove=true;
	    	//원래 파일명 갖고 와서 저장
	    }

	    int re=-1;
	    
	    try {
	        // 원래 파일 삭제
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
	            b.setBfname(fname);

	        }
            re=boarddao.update(b);
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
	
	@GetMapping("/community/community_detail/deleteComments")
	@ResponseBody
	public String deletecomments(int cono) {
		String msg=null;
		int re=boarddao.deleteComments(cono);
		if(re==1) {
			msg="삭제";
		}else {
			msg="실패";
		}
		
		return msg;
	}

}