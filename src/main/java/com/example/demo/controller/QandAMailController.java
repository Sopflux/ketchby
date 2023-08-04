package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Component
@Controller
public class QandAMailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/qanda/qanda_open")
    public void Form() {

    }

    @GetMapping("/qanda/qanda_open_ajax")
    @ResponseBody
    public ModelAndView send(String category, String name, String email, String title, String message) {
        mailSender.send(mimeMessage -> {
            MimeMessageHelper message1 = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message1.setFrom("ketchby0821@gmail.com");
            message1.setTo("ketchby0821@gmail.com");
            message1.setSubject("[" + category + "] " + title);
            message1.setText("답장 받을 이메일: " + email + ", / 이름: " + name + " / 문의 내용:" + message, true);
        });

        ModelAndView mav = new ModelAndView("redirect:/qanda/qandamain");
        return mav;
    }
}
