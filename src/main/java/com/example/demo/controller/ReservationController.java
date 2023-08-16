package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.ReservationDAO_mb;
import com.example.demo.entity.ClDate;
import com.example.demo.entity.Payment;
import com.example.demo.entity.PaymentInfo;
import com.example.demo.entity.Time;

@Controller
public class ReservationController {
	@Autowired
	ReservationDAO_mb dao;
	
	@GetMapping("/pay/classpayment")
	public void classpayment(int clno,String rsdate,int clpeople,Model model) {
		PaymentInfo info = dao.findPaymentInfo(clno);
		info.setClprice(info.getClprice()*clpeople);
		
		model.addAttribute("i", info);
		model.addAttribute("clpeople", clpeople);
		
		model.addAttribute("rsdate",rsdate);
	}
	
	@PostMapping("/pay/classpayment")
	public ModelAndView classpayment(Payment p) {
		ModelAndView mav = new ModelAndView("redirect:/account/mypage");
		System.out.println("p : "+p.toString());
		String paymethod = p.getPaymethod();
		
		dao.insertReservation(p);
		return mav;
	}
	@GetMapping("/pay/reservation_popup")
	public ModelAndView reservation_popup(int clno, Model model) {
		ModelAndView mav = new ModelAndView("/pay/reservation_popup");
		if(dao.findByClno(clno).getCltype().equals("정규")) {
			mav.setViewName("/pay/classpayment?clno="+clno);
			mav.addObject("clno", clno);
			model.addAttribute("i", dao.findPaymentInfo(clno));
			return mav;
		}
		List<Time> list = dao.findClTime(clno);
		ArrayList daylist = new ArrayList();
		String stime = "";
		String etime = "";
		
		for (Time t : list) {
		    String day = t.getTday();
		    System.out.println("day"+day);
		    int dayNumber;
		    stime = t.getTstime();
		    etime = t.getTetime();
		    System.out.println("for in s_time:"+stime);
		    switch (day) {
		        case "일":
		            dayNumber = 0;
		            break;
		        case "월":
		            dayNumber = 1;
		            break;
		        case "화":
		            dayNumber = 2;
		            break;
		        case "수":
		            dayNumber = 3;
		            break;
		        case "목":
		            dayNumber = 4;
		            break;
		        case "금":
		            dayNumber = 5;
		            break;
		        case "토":
		            dayNumber = 6;
		            break;
		        default:
		            dayNumber = -1; // 처리할 수 없는 요일이라면 -1로 설정
		            break;
		    }

		    if (dayNumber != -1) {
		        daylist.add(dayNumber);
		        System.out.println(dayNumber);
		    }
		}
		
		ClDate d = dao.findClDate(clno);
		model.addAttribute("d", d);
		model.addAttribute("daylist", daylist);
		model.addAttribute("clno", clno);
		model.addAttribute("stime", stime);
		model.addAttribute("etime", etime);
		return mav;
	}
}
