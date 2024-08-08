package com.java14.entity;

import com.java14.util.enums.EGender;
import com.java14.util.enums.EStatus;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)


public class Manager extends BaseEntity {
  @MongoId
        private String id;
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
        private EStatus status;


}
