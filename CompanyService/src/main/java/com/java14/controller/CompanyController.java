package com.java14.controller;

import com.java14.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.java14.constant.EndPoints.COMPANY;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANY)
public class CompanyController {

    private final CompanyService companyService;
    //fghj
    //hgjffk
}
