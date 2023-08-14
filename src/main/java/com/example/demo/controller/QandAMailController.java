package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.QandADAO;
import com.example.demo.entity.QnainsertVO;

@Component
@Controller
public class QandAMailController {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private QandADAO qandadao;

    @GetMapping("/qanda/qanda_open")
    public void Form(Model model) {
    	model.addAttribute("qino", qandadao.getNextQino());
    }

    @GetMapping("/qanda/qanda_open_ajax")
    @ResponseBody
    public ModelAndView send(int qino, int qno, String qiname, String qiemail, String qititle, String qicontent) {
        mailSender.send(mimeMessage -> {
            MimeMessageHelper message1 = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message1.setFrom("ketchby0821@gmail.com");
            message1.setTo("ketchby0821@gmail.com");
            message1.setSubject("[" + qno + "] " + qititle);
            message1.setText("답장 받을 이메일: " + qiemail + ", / 이름: " + qiname + " / 문의 내용:" + qicontent, true);
        });
        
        QnainsertVO q=new QnainsertVO();
        q.setQno(qno);
        q.setQino(qino);
        q.setQiname(qiname);
        q.setQiemail(qiemail);
        q.setQititle(qititle);
        q.setQicontent(qicontent);
        
        qandadao.insert(q);
        System.out.println("qnainsert 완료");
        
        ModelAndView mav = new ModelAndView("redirect:/qanda/qandamain");
        return mav;
    }
}
