package com.authO.jwt.controller;

import com.authO.jwt.entity.Company;
import com.authO.jwt.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ComponentScan("com.authO.jwt")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @PostMapping("/addCompany")
    public List<Company> addCompany(@RequestBody List<Company> company) {
        return companyService.addCompany(company);
    }

}
