package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.dao.ClassOpenDAO;
import com.example.demo.entity.Time;
import com.example.demo.entity.Account;
import com.example.demo.entity.Class;
import com.example.demo.entity.ScategoryVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Setter
@Controller
public class ClassOpenController {
	@Autowired
	private ClassOpenDAO dao;
	
	@GetMapping("/classopen")
	public void classOpen() {
		
	}
	
	@GetMapping("/classopen/findSca")
	@ResponseBody
	public List<ScategoryVO> findSca(String bcaname){
		List<ScategoryVO> list = dao.findScaName(bcaname);
		return list;
	}
	
	@PostMapping("/classopen/insert")
	@ResponseBody
	public String open(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Account a = (Account)session.getAttribute("a");
		String aid = a.getAid();
		String str = "클래스 개설 신청되었습니다.";
		
		
		String cltitle = request.getParameter("cltitle");
		String cllevel = request.getParameter("cllevel");
		String clcontent = request.getParameter("clcontent").replace("\n", "<br>");
		String claddr1 = request.getParameter("claddr1");
		String claddr2 = request.getParameter("claddr2");
		String claddr = claddr1+", "+claddr2;
		String clsdate = request.getParameter("clsdate");
		String cledate = request.getParameter("cledate");
		String cltype = "정규";
		
		if(request.getParameter("onedayCheck") != null) {
			cltype="원데이";
		}
		String scaname = request.getParameter("scaname");
		
		int scano = dao.findScaNo(scaname);
		int clpeople = Integer.parseInt(request.getParameter("clpeople"));
		int clprice = 0;
		if(request.getParameter("clprice") != null) {
			clprice = Integer.parseInt(request.getParameter("clprice"));
		}
		
		MultipartFile uploadFile = ((MultipartHttpServletRequest) request).getFile("input_file");
		
		String tetime = request.getParameter("tetime");
		String tstime = request.getParameter("tstime");
		
		Class c = new Class();
		Time ct = new Time();
		
		List<String> list = new ArrayList();
		list.add(request.getParameter("clday_mon"));
		list.add(request.getParameter("clday_tue"));
		list.add(request.getParameter("clday_wed"));
		list.add(request.getParameter("clday_thu"));
		list.add(request.getParameter("clday_fri"));
		list.add(request.getParameter("clday_sat"));
		list.add(request.getParameter("clday_sun"));
		
		ct.setTetime(tetime);
		ct.setTstime(tstime);
		
		c.setAid(aid);
		c.setCladdr(claddr);
		c.setCltitle(cltitle);
		c.setCllevel(cllevel);
		c.setClpeople(clpeople);
		c.setClprice(clprice);
		c.setClsdate(clsdate);
		c.setCledate(cledate);
		c.setCltype(cltype);
		c.setUploadFile(uploadFile);
		c.setScano(scano);
		c.setClcontent(clcontent);
		
		System.out.println("c: "+c);
		String path = request.getServletContext().getRealPath("class");
		String img = null;
		
		img = uploadFile.getOriginalFilename();
		
		System.out.println(img);
		if(img != null && !img.equals("")) {
			
			long n = System.currentTimeMillis();
			String fname1 = img.substring(0, img.lastIndexOf("."));
			String fname2 = img.substring(img.lastIndexOf("."));
			img = fname1 + n + fname2;
			
			System.out.println("img: "+img);
		}else {
			img = "Classdefault.png";
		}
		c.setClimg(img);
		
		try {
			int re = dao.insertClass(c);
			if(re == 1) {
				if(img != null && !img.equals("")) {
					try {
						byte[] data = uploadFile.getBytes();
						FileOutputStream fos = new FileOutputStream(path+"/"+img);
						fos.write(data);
						fos.close();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("insertClass error: "+e.getMessage());
						str = "개설 신청에 실패하였습니다.";
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertClass error2: "+e.getMessage());
			str = "개설 신청에 실패하였습니다.";
		}
		
		for(String tday:list) {
			if(tday != null) {
				try {
					ct.setTday(tday);
					System.out.println("ct: "+ct);
					dao.insertClassTime(ct);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("insertClassTime error: "+e.getMessage());
					str = "개설 신청에 실패했습니다. insertClassTime";
				}
			}
		}
		
		return str;
	}

}