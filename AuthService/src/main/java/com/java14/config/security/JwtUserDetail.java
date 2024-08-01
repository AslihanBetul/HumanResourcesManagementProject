package com.java14.config.security;

import com.java14.entity.Auth;
import com.java14.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetail implements UserDetailsService {
    @Autowired
    private AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public UserDetails getAuthById(Long authId){
        Optional<Auth> authUser=authRepository.findOptionalById(authId);
        if (authUser.isEmpty()) return null;

        List<GrantedAuthority> authorizedList=new ArrayList<>();
        authorizedList.add(new SimpleGrantedAuthority("EMPLOYEE")); //employee
        authorizedList.add(new SimpleGrantedAuthority("MANAGER")); //manager
        authorizedList.add(new SimpleGrantedAuthority("ADMIN")); //site yöneticisi



        return org.springframework.security.core.userdetails.User.builder()
                .username(authUser.get().getPersonalEmail())
                .password("")
                .accountLocked(false)
                .accountExpired(false)
                .authorities(authorizedList)
                .build();
    }


}
