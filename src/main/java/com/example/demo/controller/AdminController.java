package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.dao.AdminClassDAO;
import com.example.demo.dao.AdminDashBoardDAO;
import com.example.demo.entity.AdminClass;
import com.example.demo.entity.AdminDashBoard;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Setter
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminClassDAO adminClassDao;

	
	@Autowired
	private AdminDashBoardDAO adminDashBoardDao;
	
	
	/*
	 * adminClassPending 메소드
	 */
	
	@RequestMapping("/class")
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
	    
	    model.addAttribute("listClass", adminClassDao.findAll("adminClass", map));
	    model.addAttribute("totalPage", adminClassDao.totalPage);
	}

		
	
	@GetMapping("/detailClass")
	@ResponseBody
	public AdminClass detailClass(int clno) {
		return adminClassDao.findByClno("adminClass", clno);
	}

	
	@GetMapping("/deleteClass")
	public ModelAndView deleteClass(@RequestParam("clno") int clno) {
		ModelAndView mav = new ModelAndView("redirect:/admin/class");
		int re = adminClassDao.delete("adminClass", clno);
		if(re !=1) {
			mav.addObject("msg", "클래스 삭제에 실패했습니다");
		}
		return mav;
	}
	

	/*
	 * adminClassPending 메소드
	 */
	
	@RequestMapping("/classPending")
	public void listClassPending(Model model, 
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
	    
	    model.addAttribute("listClassPending", adminClassDao.findAll("adminClassPending", map));
	    model.addAttribute("totalPage", adminClassDao.totalPage);
	}
	
	
	@GetMapping("/detailClassPending")
	@ResponseBody 
	public AdminClass detailClassPending(int clno) {
		return adminClassDao.findByClno("adminClassPending", clno);
	}

	
	
	@GetMapping("/admin/updateClassPending")
	public ModelAndView updateClassPending(int clno, String cfcd) {
		ModelAndView mav = new ModelAndView("redirect:/admin/classPending");
		HashMap<String, Object> map = new HashMap<>();
		map.put("clno", clno);
		map.put("cfcd", cfcd);
		int re = adminClassDao.update(map);
		if (re != 1) {
	        mav.addObject("msg", "클래스 " + cfcd + "에 실패했습니다");
	    }
		return mav;
	}
	
	
	/*
	 * adminDashboard 메소드
	 */
	
	@GetMapping
	public String admin() {
		return "/admin/dashboard";
	}
	
	@GetMapping("/dashboard")
	public void listDashBoard(Model model){
		model.addAttribute("totalUsers", adminDashBoardDao.findTotalUsers());
	}
	
	
	@GetMapping("/totalUsers")
    @ResponseBody
    public int getTotalUsers() {
        return adminDashBoardDao.findTotalUsers();
    }

    @GetMapping("/dailyUsers")
    @ResponseBody
    public List<AdminDashBoard> getDailyUsers(@RequestParam String startDate,@RequestParam String endDate) {
    	
    	HashMap<String, Object> map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate); 
    	return adminDashBoardDao.findDailyUsers(map);
    }
	
	
    @GetMapping("/classByBca")
    @ResponseBody
    public List<AdminDashBoard> getClassByBca(){
    	return adminDashBoardDao.findClassByBca();
    }
 
    

    @GetMapping("/dailyClass")
    @ResponseBody
    public List<AdminDashBoard> getDailyClass(@RequestParam String startDate, @RequestParam String endDate, @RequestParam String bcaname) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("bcaname", bcaname); 
        
        return adminDashBoardDao.findDailyClass(map);
    }
 
}