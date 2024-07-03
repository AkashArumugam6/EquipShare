package com.csye6220.equipshare.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csye6220.equipshare.dao.HardwareDAO;
import com.csye6220.equipshare.model.Hardware;

@Service
public class HardwareService {
	
	private HardwareDAO hardwareDAO;
	
	@Autowired
    public HardwareService(HardwareDAO hardwareDAO) {
        this.hardwareDAO = hardwareDAO;
    }

	public void addHardware(Hardware hardware) throws IOException {
	    hardwareDAO.saveOrUpdate(hardware);
	}

    public Hardware getHardwareById(Long id) {
        return hardwareDAO.getById(id);
    }
    
    public Hardware getHardwareByName(String name) {
        return hardwareDAO.getByName(name);
    }

    public void deleteHardware(Hardware hardware) {
        hardwareDAO.softDelete(hardware);
    }

    public void updateHardware(Hardware hardware) {
        hardwareDAO.saveOrUpdate(hardware);
    }
    
    public void updateAvailableCount(Long id, int count) {
        hardwareDAO.updateAvailableCount(id, count);
    }

    public List<Hardware> getAllHardware() {
        return hardwareDAO.getAll();
    }
    
    
}
	
	
	

