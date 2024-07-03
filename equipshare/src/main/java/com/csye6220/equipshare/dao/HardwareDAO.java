package com.csye6220.equipshare.dao;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.csye6220.equipshare.model.Hardware;

@Component
public class HardwareDAO {

	@Autowired
    private SessionFactory sessionFactory;
    
    
    public void saveOrUpdate(Hardware hardware) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(hardware);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public void updateAvailableCount(Long id, int count) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            
            Hardware hardware = session.get(Hardware.class, id);
            if (hardware != null) {
                hardware.setCount(count);
                session.merge(hardware); 
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
        
    public void softDelete(Hardware hardware) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            hardware.setDeleted(true);
            session.merge(hardware);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public Hardware getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Hardware h WHERE h.id = :id AND h.isDeleted = false", Hardware.class)
                          .setParameter("id", id)
                          .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Hardware> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Hardware h WHERE h.isDeleted = false", Hardware.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Hardware getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Hardware h WHERE h.name = :name AND h.isDeleted = false", Hardware.class)
                          .setParameter("name", name)
                          .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    


}

