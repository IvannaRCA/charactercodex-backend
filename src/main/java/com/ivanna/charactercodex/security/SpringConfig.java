package com.ivanna.charactercodex.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import com.ivanna.charactercodex.security.filter.JWTAuthentication;
import com.ivanna.charactercodex.security.filter.JWTAuthorization;

@Configuration
public class SpringConfig {

    private final CustomAuthenticationManager customAuthenticationManager;
    private final CorsConfigurationSource corsConfigurationSource;

    @Value("${JWT_SECRET}")
    private String secret;

    public SpringConfig(CustomAuthenticationManager customAuthenticationManager, CorsConfigurationSource corsConfigurationSource) {
        this.customAuthenticationManager = customAuthenticationManager;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JWTAuthentication jwtAuthentication = new JWTAuthentication(customAuthenticationManager, secret);
        jwtAuthentication.setFilterProcessesUrl("/api/v1/auth/login");
        
        http
        .cors(cors -> cors.configurationSource(corsConfigurationSource))
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
        .authorizeHttpRequests(request -> request
            .requestMatchers("/error").permitAll()
            .requestMatchers("/h2/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()
            .anyRequest().authenticated()
        )
        .addFilter(jwtAuthentication)
        .addFilterAfter(new JWTAuthorization(secret), JWTAuthentication.class)
        .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
