package com.csye6220.equipshare.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csye6220.equipshare.model.User;
import com.csye6220.equipshare.service.UserService;



@Controller
public class RegistrationController {
	
	@Autowired
    UserService userService;
	
	 
	@GetMapping("/")
	public String landingPage(Model model) throws IOException {
	    
        return "landing";
    }
	
	 @GetMapping("/signup")
	    public String redirectToRegistrationPage(Model model) {
	        model.addAttribute("user", new User());
	        return "signup";
	    }
	 
	 @PostMapping("/register")
	    public ModelAndView registerUser(
	            @RequestParam(name = "email") String email,
	            @RequestParam(name = "username")  String username,
	           @RequestParam(name = "password") String password,
	           @RequestParam(name = "role") String role, RedirectAttributes redirectAttributes
	    ){

	        ModelAndView modelAndView = new ModelAndView();

	        User existingUser = userService.getUserByUsername(username);

	        if(existingUser != null){
	            modelAndView.setViewName("redirect:/signup");
	            redirectAttributes.addFlashAttribute("error", "User with this username already exist");
	            return modelAndView;
	        }

	       
	        User user = new User(username, password, email, role);

	        userService.addUser(user);
	        
	        
	        redirectAttributes.addFlashAttribute("success", "Registration successful");
	        modelAndView.setViewName("redirect:/login");
	        return modelAndView;
	    }

}
