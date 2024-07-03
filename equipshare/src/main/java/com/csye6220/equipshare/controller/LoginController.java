package com.csye6220.equipshare.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csye6220.equipshare.model.User;
import com.csye6220.equipshare.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {


	@Autowired
    private UserService userService;
  
    
    @GetMapping("/login")
    public ModelAndView redirectToLoginPage(){
        return new ModelAndView("login");
    }
    
    @GetMapping("/adminlogin")
    public ModelAndView redirectToAdminLoginPage(){
        return new ModelAndView("adminlogin");
    }
    
    @PostMapping("/perform-login")
    public ModelAndView checkValidation(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView();
        
        boolean loginSuccess = false;

        User user = userService.getUserByUsername(username);


        if(user == null){
            
            modelAndView.addObject("loginError", "Invalid Username");
            
            String referer = request.getHeader("Referer");
            if(referer != null && referer.contains("/adminlogin")){
                modelAndView.setViewName("adminlogin");
            } else {
                modelAndView.setViewName("login");
            }
        }
        else{
            if((password).equals(user.getPassword())){
            	
            	loginSuccess = false;
            	
            	if("admin".equalsIgnoreCase(user.getRole())){
                    modelAndView.setViewName("redirect:/adminhome");
                } else {
                    modelAndView.setViewName("redirect:/userhome");
                }

                request.getSession().setAttribute("user", user);
            }
            else {
            	modelAndView.addObject("loginError", "Username or password is incorrect.");
            	if("admin".equalsIgnoreCase(user.getRole())){
                    modelAndView.setViewName("/adminlogin");
                } else {
                    modelAndView.setViewName("/login");
                }
                
            }
        }
        return modelAndView;
    }
}