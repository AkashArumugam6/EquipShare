package com.csye6220.equipshare.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

import com.csye6220.equipshare.model.Hardware;
import com.csye6220.equipshare.model.HardwareRequest;
import com.csye6220.equipshare.model.User;
import com.csye6220.equipshare.service.HardwareRequestService;
import com.csye6220.equipshare.service.HardwareService;



@Controller
public class UserHomeController {
	
	private final HardwareService hardwareService;
    
    private final HardwareRequestService hardwareRequestService;
	
	@Autowired
    public UserHomeController(HardwareService hardwareService, HardwareRequestService hardwareRequestService) {
		this.hardwareService = hardwareService;
		this.hardwareRequestService = hardwareRequestService;
    }
	
	@GetMapping("/userhome")
    public String home(HttpServletRequest request, Model model) throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("username", user.getUsername());
		List<Hardware> hardwareList = hardwareService.getAllHardware();
        model.addAttribute("hardwareList", hardwareList);
        List<HardwareRequest> hardwareRequestList = hardwareRequestService.getAllHardwareRequests();
        model.addAttribute("hardwareRequestList",hardwareRequestService.findAllByUser(user));
        return "userhome";
    }
	
	
}
