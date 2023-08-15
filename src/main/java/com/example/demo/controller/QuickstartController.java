package com.example.demo.controller;

import com.example.demo.service.QuickstartService;
import com.example.demo.service.TimeOnPageData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class QuickstartController {

    private final QuickstartService quickstartService;

    @Autowired
    public QuickstartController(QuickstartService quickstartService) {
        this.quickstartService = quickstartService;
    }

    @GetMapping("/time-on-page")
    public List<TimeOnPageData> getTimeOnPageData() {
        try {
            String propertyId = "397498531";
            return quickstartService.getTimeOnPageData(propertyId);
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
}
