package com.csye6220.equipshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csye6220.equipshare.model.HardwareRequest;
import com.csye6220.equipshare.model.Hardware;
import com.csye6220.equipshare.model.User;
import com.csye6220.equipshare.service.HardwareRequestService;
import com.csye6220.equipshare.service.HardwareService;
import com.csye6220.equipshare.service.UserService;

@Controller
public class HardwareRequestController {

    
    private final HardwareRequestService hardwareRequestService;
       

	private final HardwareService hardwareService;

    
    private final UserService userService;
    
    @Autowired
    public HardwareRequestController(HardwareRequestService hardwareRequestService, HardwareService hardwareService,
			UserService userService) {
		this.hardwareRequestService = hardwareRequestService;
		this.hardwareService = hardwareService;
		this.userService = userService;
	}
    
    @PostMapping("/submitLendRequest")
    public String submitRequest(@RequestParam("username") String username,
                                @RequestParam("email") String email,
                                @RequestParam("hardwareId") Long hardwareId,
                                @RequestParam("store") String store,
                                Model model) {

        Hardware hardware = hardwareService.getHardwareById(hardwareId);
        User user = userService.getUserByUsername(username); 
        


        HardwareRequest request = new HardwareRequest();
        request.setUser(user);
        request.setHardware(hardware);
        request.setStore(store);
        

        hardwareRequestService.addHardwareRequest(request);
        
        model.addAttribute("hardwareName", hardware.getName());
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("store", store);

        return "requestSuccess";
        
    }
}

