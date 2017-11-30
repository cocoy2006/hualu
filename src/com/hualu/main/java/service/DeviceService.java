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
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.UserDao;
import com.hualu.main.java.entity.Company;
import com.hualu.main.java.entity.Device;
import com.hualu.main.java.entity.DevicePackage;
import com.hualu.main.java.entity.DeviceReceive;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.User;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;

@Service
public class DeviceService {
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private DeviceDao dao;
	
	@Autowired
	private DevicePackageDao packageDao;
	
	@Autowired
	private DeviceReceiveDao drDao;
	
	@Autowired
	private OperatorDao operatorDao;
	
	@Autowired
	private UserDao userDao;
	
	public List<Device> findAll() {
		return dao.findAll();
	}
	
	public int save(int packageId, String dsn) {
		Device device = new Device();
		device.setDsn(dsn);
		device.setDstatus(Status.DEVICE_DEFAULT);
		device.setPackid(packageId);
		device.setDdatetime(Hualu.getStandardTimestamp());
		return dao.save(device);
	}
	
	public List<DeviceComponent> loadAllDevices(String filterDmodel, String filterDsn, 
			String filterDname, int filterComid, int filterDstatus) {
		List<DeviceComponent> dcs = new ArrayList<DeviceComponent>();
		List<Device> devices = dao.findAll();
		if(devices != null && devices.size() > 0) {
			for(Device device : devices) {
				DeviceComponent dc = new DeviceComponent();
				if(filterDsn != null && !filterDsn.equalsIgnoreCase(device.getDsn())) {
					continue;
				}
				if(filterDstatus != 0 && filterDstatus != device.getDstatus()) {
					continue;
				}
				dc.setDevice(device);
				DevicePackage dp = packageDao.findById(device.getPackid());
				if(dp != null) {
					if(filterDmodel != null && !filterDmodel.equals(dp.getDmodel())) {
						continue;
					}
					if(filterDname != null && !filterDname.equals(dp.getDname())) {
						continue;
					}
					dc.setDevicePackage(dp);
					int comid = dp.getComid();
					if(filterComid != 0 && filterComid != comid) {
						continue;
					}
					Company company = companyDao.findById(comid);
					if(company != null) {
						dc.setCompany(company);
					}
				}
				DeviceReceive dr = drDao.findByDeviceid(device.getDid());
				if(dr != null) {
					Integer nurseid = dr.getNurseid();
					if(nurseid != null) {
						Operator nurse = operatorDao.findById(dr.getNurseid());
						if(nurse != null) {
							dc.setNurse(nurse);
						}
					}
					Integer userid = dr.getUserid();
					if(userid != null) {
						User user = userDao.findById(dr.getUserid());
						if(user != null) {
							dc.setUser(user);
						}
					}
				}
				dcs.add(dc);
			}
			return dcs;
		}
		return null;
	}

}
