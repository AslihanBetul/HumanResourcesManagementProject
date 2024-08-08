package com.java14.service;

import com.java14.dto.request.CompanyIdRequestDto;
import com.java14.dto.request.SaveCompanyRequestDto;
import com.java14.entity.Company;
import com.java14.exception.CompanyServiceException;
import com.java14.exception.ErrorType;
import com.java14.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Boolean saveCompany(SaveCompanyRequestDto dto) {
        Optional<Company> optionalCompany = companyRepository.findByNameIgnoreCase(dto.getName());

        if (optionalCompany.isPresent()) {
            throw new CompanyServiceException(ErrorType.COMPANY_ALREADY_EXISTS);
        }
        Company company = Company.builder()
                .name(dto.getName())
                .build();
        Company savedCompany = companyRepository.save(company); // Kaydedilen şirketi alıyoruz
        return savedCompany != null;
    }




    public String listCompanyId(String name) {
        Optional<Company> company = companyRepository.findByNameIgnoreCase(name);
        if (company.isPresent()) {
            System.out.println("Company ID: " + company.get().getId());
            return company.get().getId();
        } else {
            throw new RuntimeException("ID not found");
        }
    }


}
