package com.csye6220.equipshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csye6220.equipshare.model.Hardware;
import com.csye6220.equipshare.model.User;
import com.csye6220.equipshare.service.HardwareService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HardwareController {

    private final HardwareService hardwareService;

    @Autowired
    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping("/hardware/detail/{id}")
    public String showHardwareDetail(@PathVariable("id") Long hardwareId, Model model, HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("username", user.getUsername());
		model.addAttribute("email", user.getEmail());
        Hardware hardware = hardwareService.getHardwareById(hardwareId);
        if (hardware != null) {
            model.addAttribute("hardware", hardware);
            return "hardwareDetail";
        } else {
            return "redirect:/errorPage";
        }
        
    }

    
}
