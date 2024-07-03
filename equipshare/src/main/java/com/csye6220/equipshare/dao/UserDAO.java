package com.csye6220.equipshare.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csye6220.equipshare.model.User;



@Component
public class UserDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
		
	 public User getById(int id) 
		{
	       Session session = sessionFactory.openSession();
	       User user = session.get(User.class, id);
	       return user;
	     
	    }
	 
	    public User getByUsername(String username) {
	        try(Session session = sessionFactory.openSession()) {
	            String queryString = "FROM User u where u.username=\""+username+"\"";
	            Query query = session.createQuery(queryString, User.class);
	            List<User> users = query.list();
	            return users.size() == 1 ? users.get(0) : null;
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	 
	 public void save(User user) 
		{
	       Session session = sessionFactory.openSession();
	       Transaction transaction = session.getTransaction();
	       transaction.begin();
	       session.persist(user);
	       transaction.commit();
	     
	    }

}
