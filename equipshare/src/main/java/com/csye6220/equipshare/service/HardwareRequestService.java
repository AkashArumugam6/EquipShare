package com.csye6220.equipshare.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csye6220.equipshare.dao.HardwareRequestDAO;
import com.csye6220.equipshare.model.HardwareRequest;
import com.csye6220.equipshare.model.User;

@Service
public class HardwareRequestService {
    
    private final HardwareRequestDAO hardwareRequestDAO;
    
    @Autowired
    public HardwareRequestService(HardwareRequestDAO hardwareRequestDAO) {
        this.hardwareRequestDAO = hardwareRequestDAO;
    }
    
    public void addHardwareRequest(HardwareRequest hardwareRequest) {
        hardwareRequestDAO.saveOrUpdate(hardwareRequest);
    }
    
    public void updateHardwareRequest(HardwareRequest hardwareRequest) {
        hardwareRequestDAO.saveOrUpdate(hardwareRequest);
    }
    
    public HardwareRequest getHardwareRequestById(Long requestId) {
        return hardwareRequestDAO.getById(requestId);
    }
    
    public void deleteHardwareRequest(HardwareRequest hardwareRequest) {
        hardwareRequestDAO.delete(hardwareRequest);
    }
    
    
    public List<HardwareRequest> getAllHardwareRequests() {
        return hardwareRequestDAO.getAll();
    }
    
    public List<HardwareRequest> findAllByUser(User user) {
        return hardwareRequestDAO.findByUser(user);
    }
}

