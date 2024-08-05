package com.java14.entity;

import com.java14.enums.ERole;
import com.java14.enums.EStatus;
import com.java14.utility.enums.EEmailVerify;
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
    @Email

    @Size(min = 4,max = 32, message = "Sifre en az 4 karakterden oluşmalıdır.")
    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EStatus status=EStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private EEmailVerify emailVerify=EEmailVerify.INACTIVE;


}
