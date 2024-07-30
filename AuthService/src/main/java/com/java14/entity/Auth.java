package com.java14.entity;

import com.java14.enums.ERole;
import com.java14.enums.EStatus;
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

@Table(name = "tbl_auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    @Email
    @Column(unique = true)
    private String email;
    @Size(min = 8,max = 64, message = "Sifre en az 8 karakterden oluşmalıdır.")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private ERole role;
    @Builder.Default
    private EStatus status=EStatus.PENDING;


}
