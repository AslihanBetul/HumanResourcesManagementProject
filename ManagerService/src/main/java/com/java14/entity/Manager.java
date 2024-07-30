package com.java14.entity;

import com.java14.util.enums.EGender;
import com.java14.util.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_manager")

public class Manager {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long authId;
        private String name;
        private String surname;
        private String avatar;
        private String birthDate;
        private String phone;
        private String identityNumber;
        private String taxNumber;
        private String email;
        private String address;
        private Long occupation;
        private String companyId;
        private EGender gender;
        private String jobStartDate;
        private Long createAt;
        private Long updateAt;
        private EStatus status;


}
