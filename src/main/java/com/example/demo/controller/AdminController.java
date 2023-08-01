package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.dao.AdminClassDAO;
import com.example.demo.dao.AdminDashBoardDAO;
import com.example.demo.entity.AdminClass;
import com.google.api.client.json.Json;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Setter
@Controller
public class AdminController {
	
	@Autowired
	private AdminClassDAO adminClassDao;
	
	@Autowired
	private AdminDashBoardDAO adminDashBoardDao;
	
	@GetMapping("/admin")
	public String admin() {
		return "/admin/dashboard";
	}
	
	
	@GetMapping("/delete/class")
	public ModelAndView delete(int clno) {
		ModelAndView mav = new ModelAndView("redirect:/admin/class");
		int re = adminClassDao.delete(clno);
		if(re !=1) {
			mav.addObject("msg", "클래스 삭제에 실패했습니다");
		}
		return mav;
	}
	
	@RequestMapping("/admin/class")
	public void listClass(Model model, 
			@RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "searchColumn", required = false) String searchColumn,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @SessionAttribute(value = "keyword", required = false) String sessionKeyword,
            @SessionAttribute(value = "searchColumn", required = false) String sessionSearchColumn, HttpSession session) {
	    
		System.out.println("pageNo:" + pageNo);
	    int start = (pageNo - 1) * AdminClassDAO.pageSize + 1;
	    int end = start + AdminClassDAO.pageSize - 1;

	    HashMap<String, Object> map = new HashMap<>();
	    // 페이징용
	    map.put("start", start);
	    map.put("end", end);

	    // 검색용
	    if (keyword != null && !keyword.trim().isEmpty()) {
	        map.put("keyword", keyword);
	        map.put("searchColumn", searchColumn);
	     
	        session.setAttribute("keyword", keyword);
	        session.setAttribute("searchColumn", searchColumn);
	    }
	    
	    model.addAttribute("listClass", adminClassDao.findAll(map));
	    model.addAttribute("totalPage", adminClassDao.totalPage);
	}

	
	@GetMapping("/admin/dashboard")
	public void listDashBoard(Model model){
		model.addAttribute("totalUsers", adminDashBoardDao.getTotalUsers());
		model.addAttribute("dailyUsers", adminDashBoardDao.getDailyUsers());
	}

	
	
	
	
}
