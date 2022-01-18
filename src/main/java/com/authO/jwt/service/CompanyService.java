package com.authO.jwt.service;


import com.authO.jwt.dao.CompanyDao;
import com.authO.jwt.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
     @Autowired
     CompanyDao companyDao;

    public List<Company> addCompany(List<Company> company) {
        return (List<Company>) companyDao.saveAll(company);
    }
}
