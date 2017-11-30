package com.hualu.main.java.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.dm.DeviceComponent;
import com.hualu.main.java.component.dm.PackageComponent;
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
public class DMService {
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private DevicePackageDao packageDao;
	
	@Autowired
	private DeviceReceiveDao drDao;
	
	public List<PackageComponent> loadAllPackages(int filterComid) {
		List<PackageComponent> pcs = new ArrayList<PackageComponent>();
		List<DevicePackage> packages;
		if(filterComid == 0) {
			packages = packageDao.findAll();
		} else {
			packages = packageDao.findByComid(filterComid);
		}
		if(packages != null && packages.size() > 0) {
			for(DevicePackage devicePackage : packages) {
				PackageComponent pc = new PackageComponent();
				pc.setDevicePackage(devicePackage);
				int comid = devicePackage.getComid();
				Company company = companyDao.findById(comid);
				if(company != null) {
					pc.setCompany(company);
				}
				pcs.add(pc);
			}
			return pcs;
		}
		return null;
	}
	
	public List<DeviceComponent> loadAllDevices(int filterPackageId) {
		List<DeviceComponent> dcs = new ArrayList<DeviceComponent>();
		List<Device> devices = deviceDao.findAll();
		if(devices != null && devices.size() > 0) {
			for(Device device : devices) {
				DeviceComponent dc = new DeviceComponent();
				dc.setDevice(device);
				int packageId = device.getPackid();
				if(filterPackageId != 0 && filterPackageId != packageId) {
					continue;
				}
				DevicePackage devicePackage = packageDao.findById(packageId);
				if(devicePackage != null) {
					dc.setDevicePackage(devicePackage);
					int comid = devicePackage.getComid();
					Company company = companyDao.findById(comid);
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
	
	public void checkout(String[] deviceids, int nurseid, 
			Timestamp occupationtime, String address, String memo) {
		for(String deviceidString : deviceids) {
			int deviceid = Integer.parseInt(deviceidString);
			checkout(deviceid, nurseid, occupationtime, address, memo);
		}
	}
	
	public void checkout(int deviceid, int nurseid, 
			Timestamp occupationtime, String address, String memo) {
		DeviceReceive dr = new DeviceReceive();
		dr.setDeviceid(deviceid);
		dr.setNurseid(nurseid);
		dr.setOccupationtime(occupationtime);
		dr.setAddress(address);
		dr.setMemo(memo);
		dr.setStatus(Status.DEVICE_DEFAULT);
		drDao.save(dr);
		Device device = deviceDao.findById(deviceid);
		device.setDstatus(Status.DEVICE_OCCUPATION);
		deviceDao.update(device);
	}

}
