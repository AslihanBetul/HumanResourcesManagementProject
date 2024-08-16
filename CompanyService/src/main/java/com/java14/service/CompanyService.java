package com.java14.service;

import com.java14.dto.request.CompanyIdRequestDto;
import com.java14.dto.request.SaveCompanyRequestDto;
import com.java14.dto.response.CompanyResponseDto;
import com.java14.dto.response.SectorDto;
import com.java14.entity.Company;
import com.java14.exception.CompanyServiceException;
import com.java14.exception.ErrorType;
import com.java14.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
            return null;
        }
    }


    public List<CompanyResponseDto> getAllCompany() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyResponseDto> responseDtoList = new ArrayList<>();
        companies.stream().forEach(company -> {
            CompanyResponseDto dto = CompanyResponseDto.builder()
                    .id(company.getId())
                    .name(company.getName())
                    .logo(company.getLogo())
                    .website(company.getWebsite())
                    .email(company.getEmail())
                    .phone(company.getPhone())
                    .sector(company.getSector())
                    .address(company.getAddress())
                    .build();
            responseDtoList.add(dto);
        });
        return responseDtoList;

    }

    public List<SectorDto> getSectors() {

        List<Company> companies = companyRepository.findAll();

        List<String> sectors = new ArrayList<>();
        companies.stream().forEach(company -> {     String sector = company.getSector();     if (sector != null && !sector.isEmpty()) { sectors.add(sector); } });
        Map<String, Long> sectorCountMap = sectors.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        List<SectorDto> sectorList = sectorCountMap.entrySet().stream()
                .map(entry -> new SectorDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return sectorList;
    }

    public Boolean saveCompan2y(Company dto) {
        Company savedCompany = companyRepository.save(dto);
        return savedCompany != null;
    }

    public CompanyResponseDto findById(String id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));
        CompanyResponseDto responseDto = CompanyResponseDto.builder()
                .id(company.getId())
                .name(company.getName())
                .address(company.getAddress())
                .phone(company.getPhone())
                .email(company.getEmail())
                .logo(company.getLogo())
                .website(company.getWebsite())
                .sector(company.getSector())
                .build();
        return responseDto;
    }
}
