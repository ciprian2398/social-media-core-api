package com.socialmedia.coreapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()
                )


        http
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter)

        http
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)

        return http.build();
    }
}