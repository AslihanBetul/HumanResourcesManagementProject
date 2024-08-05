package com.java14.rabbit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ManagerSendMailModel {
    private String email;
    private String name;
    private String password;

}
