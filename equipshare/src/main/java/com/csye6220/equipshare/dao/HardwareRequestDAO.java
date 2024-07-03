package com.csye6220.equipshare.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csye6220.equipshare.model.HardwareRequest;
import com.csye6220.equipshare.model.User;


@Component
public class HardwareRequestDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
    public void saveOrUpdate(HardwareRequest hardwareRequest) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(hardwareRequest);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public HardwareRequest getById(Long requestId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(HardwareRequest.class, requestId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void delete(HardwareRequest hardwareRequest) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(hardwareRequest);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    
    public List<HardwareRequest> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM HardwareRequest", HardwareRequest.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<HardwareRequest> findByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM HardwareRequest hr WHERE hr.user = :user";
            Query<HardwareRequest> query = session.createQuery(hql, HardwareRequest.class);
            query.setParameter("user", user);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

