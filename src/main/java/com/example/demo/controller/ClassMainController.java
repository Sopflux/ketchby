package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.dao.ClassMainDAO;
import com.example.demo.dao.MainPageDAO;
import com.example.demo.entity.MainPage;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class ClassMainController {
   
   @Autowired
   public ClassMainDAO classmaindao;
   
   @GetMapping("/classmain")
   public void classMain(
      @RequestParam(name = "bcaname", required=false) String bcaname,
      @RequestParam(name = "cllevel", required = false) String cllevel,
      @RequestParam(name = "cltype", required = false) String cltype,
      @RequestParam(name = "claddr", required = false) String claddr,
      @RequestParam(name = "keyword", required = false) String keyword,
      Model model, HttpSession session){
      
            
       HashMap<String, Object> map = new HashMap<>();
       map.put("keyword", keyword);
      map.put("bcaname", bcaname);
      map.put("cltype", cltype);
      map.put("cllevel", cllevel);
      map.put("claddr", claddr);
     
      
      // bcaname, cltype, cllevel 값이 null이 아닌 경우에만 세션에 저장
       if (bcaname != null) {
           session.setAttribute("bcaname", bcaname);
       }
       if (cltype != null) {
           session.setAttribute("cltype", cltype);
       }
       if (cllevel != null) {
           session.setAttribute("cllevel", cllevel);
       }
       if (claddr != null) {
           session.setAttribute("claddr", claddr);
       }
       // 검색용
       if (keyword != null && !keyword.trim().isEmpty()) {
           session.setAttribute("keyword", keyword);
       }
       
      System.out.println("bcaname: " + bcaname);
      System.out.println("cllevel: " + cllevel);
      System.out.println("cltype: " + cltype);
      System.out.println("claddr: " + claddr);
      System.out.println("keyword: " + keyword);
      
      model.addAttribute("classMain", classmaindao.findAllClass(map));
   }
   
}