package com.hualu.main.java.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.dao.CompanyDao;
import com.hualu.main.java.entity.Company;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyDao companyDao;
	
	public void saveOrUpdate(Company company) {
		if(company.getDate() == null) {
			company.setDate(new Date());
		}
		companyDao.saveOrUpdate(company);
	}
	
	public void saveOrUpdate(int id, String name, String code, String address, String spec, Timestamp date) {
		Company company;
		if(id == 0) {
			company = new Company();
		} else {
			company = companyDao.findById(id);
		}
		company.setName(name);
		company.setCode(code);
		company.setAddress(address);
		company.setSpec(spec);
		company.setDate(date);
		companyDao.saveOrUpdate(company);
	}
	
	public Company findById(int id) {
		return companyDao.findById(id);
	}
	
	public List<Company> findAll() {
		return companyDao.findAll();
	}
}
