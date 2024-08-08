package com.java14.entity;


import com.java14.utilty.enums.ERole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)


public class Admin extends BaseEntity{
   @MongoId
   private String id;
    private Long authId;
    private String name;
    private String surname;
    @Email
    private String email;
    private String address;
    @Size(min = 11, max = 15)
    private String phone;
    private String avatar;
    @Builder.Default
    private ERole role = ERole.ADMIN;

}
