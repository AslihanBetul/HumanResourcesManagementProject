package com.java14.service;

import com.java14.dto.request.CompanyIdRequestDto;
import com.java14.dto.request.CompanyUpdateRequestDto;
import com.java14.dto.request.SaveCompanyRequestDto;
import com.java14.dto.response.CompanyByManagerResponseDto;
import com.java14.dto.response.CompanyResponseDto;
import com.java14.dto.response.SectorDto;
import com.java14.entity.Company;
import com.java14.exception.CompanyServiceException;
import com.java14.exception.ErrorType;
import com.java14.manager.EmployeeManager;
import com.java14.manager.ManagerManager;
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
    private final ManagerManager managerManager;
    private final EmployeeManager employeeManager;

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

    public CompanyResponseDto findByManagerId(String id) {
        Company company = companyRepository.findByManagerId(id).orElseThrow(() -> new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));
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

    public Boolean updateCompanyByManager(CompanyUpdateRequestDto dto) {
        String managerId   = managerManager.getManagerIdFindByToken(dto.getToken());

        String companyId = managerManager.getCompanyIdByToken(dto.getToken());
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));

        company.setAddress(dto.getAddress() != null ? dto.getAddress() : company.getAddress());
        company.setEmail(dto.getEmail() != null ? dto.getEmail() : company.getEmail());
        company.setLogo(dto.getLogo() != null ? dto.getLogo() : company.getLogo());
        company.setName(dto.getName() != null ? dto.getName() : company.getName());
        company.setPhone(dto.getPhone() != null ? dto.getPhone() : company.getPhone());
        company.setSector(dto.getSector() != null ? dto.getSector() : company.getSector());
        company.setWebsite(dto.getWebsite() != null ? dto.getWebsite() : company.getWebsite());
        company.setTitle(dto.getTitle() != null ? dto.getTitle() : company.getTitle());
        company.setDescription(dto.getDescription() != null ? dto.getDescription() : company.getDescription());
        company.setTaxNumber(dto.getTaxNumber() != null ? dto.getTaxNumber() : company.getTaxNumber());
        company.setTaxOffice(dto.getTaxOffice() != null ? dto.getTaxOffice() : company.getTaxOffice());
        company.setMersisNo(dto.getMersisNo() != null ? dto.getMersisNo() : company.getMersisNo());
        company.setVision(dto.getVision() != null ? dto.getVision() : company.getVision());
        company.setMission(dto.getMission() != null ? dto.getMission() : company.getMission());
        company.setCountry(dto.getCountry() != null ? dto.getCountry() : company.getCountry());
        company.setCity(dto.getCity() != null ? dto.getCity() : company.getCity());
        company.setEmployeeCount(employeeManager.getTotalEmployeeCount(dto.getToken()));
        company.setFounded(dto.getFounded() != null ? dto.getFounded() : company.getFounded());
        company.setFoundingYear(dto.getFoundingYear() != null ? dto.getFoundingYear() : company.getFoundingYear());
        company.setLinkedin(dto.getLinkedin() != null ? dto.getLinkedin() : company.getLinkedin());
        company.setManagerId(managerId);

        companyRepository.save(company);

        return true;
    }


    public CompanyByManagerResponseDto getCompanyByToken(String token) {
        String managerId = managerManager.getManagerIdFindByToken(token);
        String companyId = managerManager.getCompanyIdByToken(token);

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));

        Integer employeeCount = employeeManager.getTotalEmployeeCount(token);

        CompanyByManagerResponseDto responseDto = CompanyByManagerResponseDto.builder()
                .id(company.getId())
                .name(company.getName())
                .title(company.getTitle())
                .description(company.getDescription())
                .address(company.getAddress())
                .phone(company.getPhone())
                .email(company.getEmail())
                .website(company.getWebsite())
                .logo(company.getLogo())
                .sector(company.getSector())
                .taxNumber(company.getTaxNumber())
                .taxOffice(company.getTaxOffice())
                .mersisNo(company.getMersisNo())
                .vision(company.getVision())
                .mission(company.getMission())
                .country(company.getCountry())
                .city(company.getCity())
                .employeeCount(employeeCount)
                .founded(company.getFounded())
                .foundingYear(company.getFoundingYear())
                .linkedin(company.getLinkedin())
                .build();

        return responseDto;
    }

}
