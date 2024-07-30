package com.java14.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)

@Table(name = "tbl_admin")
public class Admin extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    private Long authId;
    private String name;
    private String surname;
    @Email
    private String email;
    private String address;
    @Size(min = 11, max = 15)
    private String phone;
    private String avatar;

}
