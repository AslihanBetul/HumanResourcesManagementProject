package com.java14.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
@EnableMethodSecurity
public class AuthSecurityConfig {
    @Bean
    public JwtAuthFilter getJwtAuthFilter() {
        return new JwtAuthFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(req ->
                req.requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/manager/**").permitAll()
                        .requestMatchers("/api/v1/admin/**").permitAll()
                        .requestMatchers("/api/v1/employee/**").permitAll()
                        .requestMatchers("/api/v1/company/**").permitAll()
                        .requestMatchers("/api/v1/mail/**").permitAll()
                        .requestMatchers("/api/v1/leave/**").permitAll()
                        .requestMatchers("/api/v1/shift/**").permitAll()
                        .anyRequest()
                        .authenticated()
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(Customizer.withDefaults());


        log.info("**** Tüm istekler buradan geçecek *****");
        httpSecurity.addFilterBefore(getJwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
