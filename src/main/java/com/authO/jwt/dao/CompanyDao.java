package com.authO.jwt.dao;

import com.authO.jwt.entity.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyDao extends CrudRepository<Company,String> {
}
