package com.hualu.main.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.dao.DevicePackageDao;
import com.hualu.main.java.entity.DevicePackage;

@Service
public class PackageService {
	
	@Autowired
	private DevicePackageDao devicePackageDao;
	
	public void saveOrUpdate(DevicePackage devicePackage) {
		devicePackageDao.saveOrUpdate(devicePackage);
	}
	
	public List<DevicePackage> findAll() {
		return devicePackageDao.findAll();
	}
	
	public List<DevicePackage> findByComid(int comid) {
		return devicePackageDao.findByComid(comid);
	}
	
	public DevicePackage findById(int id) {
		return devicePackageDao.findById(id);
	}
}
