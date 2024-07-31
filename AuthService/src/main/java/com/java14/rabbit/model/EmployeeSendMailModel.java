package com.java14.rabbit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeSendMailModel {
    private String personelEmail;
    private String name;
    private String password;
    private String businessEmail;
    private String companyName;

}
