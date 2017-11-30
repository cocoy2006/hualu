package com.hualu.main.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.dm.DeviceComponent;
import com.hualu.main.java.dao.CompanyDao;
import com.hualu.main.java.dao.DeviceDao;
import com.hualu.main.java.dao.DevicePackageDao;
import com.hualu.main.java.dao.DeviceReceiveDao;
import com.hualu.main.java.entity.Company;
import com.hualu.main.java.entity.Device;
import com.hualu.main.java.entity.DevicePackage;
import com.hualu.main.java.entity.DeviceReceive;
import com.hualu.main.java.util.Status;

@Service
public class DeviceReceiveService {
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private DevicePackageDao packDao;
	
	@Autowired
	private DeviceReceiveDao dao;
	
	public List<DeviceComponent> findAll(int status) {
		List<DeviceComponent> dcs = new ArrayList<DeviceComponent>();
		List<DeviceReceive> drs = dao.findByStatus(status);
		if(drs != null && drs.size() > 0) {
			for(DeviceReceive dr : drs) {
				int deviceid = dr.getDeviceid();
				Device device = deviceDao.findById(deviceid);
				DeviceComponent dc = new DeviceComponent();
				dc.setDevice(device);
				DevicePackage dp = packDao.findById(device.getPackid());
				if(dp != null) {
					dc.setDevicePackage(dp);
					Company company = companyDao.findById(dp.getComid());
					if(company != null) {
						dc.setCompany(company);
					}
				}
				dcs.add(dc);
			}
			return dcs;
		}
		return null;
	}
	
	public int updateNurse(int deviceid, int nurseid) {
		DeviceReceive dr = dao.findByDeviceid(deviceid);
		if(dr != null) {
			dr.setNurseid(nurseid);
			dao.update(dr);
			return Status.SUCCESS;
		}
		return Status.ERROR;
	}

}
