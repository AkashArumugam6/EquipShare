package com.csye6220.equipshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csye6220.equipshare.dao.UserDAO;
import com.csye6220.equipshare.model.User;



@Service
public class UserService {
	
	
	private UserDAO userDAO;
	 
	@Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(User user) {
        userDAO.save(user);
    }

   
    public User getUserById(int id) {
        return userDAO.getById(id);
    }
    
    public User getUserByUsername(String username) {
        return userDAO.getByUsername(username);
    }

	   

}
