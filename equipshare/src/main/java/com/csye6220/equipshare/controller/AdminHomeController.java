package com.csye6220.equipshare.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csye6220.equipshare.model.Hardware;
import com.csye6220.equipshare.service.EmailService;
import com.csye6220.equipshare.service.HardwareRequestService;
import com.csye6220.equipshare.service.HardwareService;
import com.csye6220.equipshare.model.HardwareRequest;
import com.csye6220.equipshare.model.RequestStatus;

@Controller
public class AdminHomeController {

    private final HardwareService hardwareService;
    
    private final HardwareRequestService hardwareRequestService;
    
    @Autowired
    private EmailService emailService;

    
    public AdminHomeController(HardwareService hardwareService, HardwareRequestService hardwareRequestService) {
		this.hardwareService = hardwareService;
		this.hardwareRequestService = hardwareRequestService;
	}

	@GetMapping("/adminhome")
    public String home(Model model) throws IOException {
        List<Hardware> hardwareList = hardwareService.getAllHardware();
        List<HardwareRequest> hardwareRequestList = hardwareRequestService.getAllHardwareRequests();
        model.addAttribute("hardwareList", hardwareList); 
        model.addAttribute("hardwareRequestList",hardwareRequestList);
        model.addAttribute("hardware", new Hardware()); 
        return "adminhome";
    }

	@PostMapping("/addHardware")
	public String addHardware(@ModelAttribute Hardware hardware, Model model) {
	    try {
	    	if (!hardware.getImage().isEmpty()) {
	            hardware.setImageData(hardware.getImage().getBytes());
	        }
	        hardwareService.addHardware(hardware);
	        return "redirect:/adminhome";
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "Error saving hardware: " + e.getMessage());
	        return "error";  
	    }
	}

    @PostMapping("/updateHardwareCount")
    public String updateHardwareCount(@RequestParam("hardwareId") Long hardwareId, @RequestParam("count") int count) {
        hardwareService.updateAvailableCount(hardwareId, count);
        return "redirect:/adminhome";
    }

    @GetMapping("/deleteHardware")
    public String deleteHardware(@RequestParam Long hardwareId, Model model) {
        Hardware hardware = hardwareService.getHardwareById(hardwareId);
        if (hardware != null) {
            hardwareService.deleteHardware(hardware);
        }
        return "redirect:/adminhome";
    }
    
    @PostMapping("/approveRequest")
    public String approveRequest(@RequestParam("requestId") Long requestId, RedirectAttributes redirectAttributes) {
        HardwareRequest request = hardwareRequestService.getHardwareRequestById(requestId);
        Hardware hardware = request.getHardware();
        
        if (hardware.getCount() > 0 && request.getStatus() == RequestStatus.PENDING) {
            hardware.setCount(hardware.getCount() - 1);

            hardwareService.updateHardware(hardware);
            
            request.setStatus(RequestStatus.APPROVED);  
            hardwareRequestService.updateHardwareRequest(request);
            
            String subject = "Hardware Lending Request Approved";
            String message = String.format("Dear %s,\n\nYour request to lend the hardware item '%s' has been approved.\n\nPick-up Store: %s . \n\nThank you for using Equipshare. \n\nBest Regards,\nEquipShare Team.",
                    request.getUser().getUsername(), request.getHardware().getName(), request.getStore());

            emailService.sendSimpleMessage(request.getUser().getEmail(), subject, message);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Request cannot be approved. Hardware is out of stock.");
        }
        return "redirect:/adminhome"; 
    }
}

