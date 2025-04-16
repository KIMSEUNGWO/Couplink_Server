package com.coupink.jours.config;

import com.coupink.jours.security.CustomOidcUserService;
import com.coupink.jours.security.jwt.CustomJwtAuthenticationConverter;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecretKey secretKey(@Value("${jwt.secret}") String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, CustomOidcUserService customOidcUserService) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**", "/error").permitAll()
                .anyRequest().authenticated()
        );

        http
            .csrf(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2ResourceServer(server -> server
                    .jwt(jwt -> jwt
                            .jwtAuthenticationConverter(new CustomJwtAuthenticationConverter())
                    )
            );


        http.oauth2Login(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/user?social")
                .userInfoEndpoint(userInfo -> userInfo
                        .oidcUserService(customOidcUserService)
                )
                .failureHandler((request, response, exception) -> {
                    response.sendRedirect("/login?error");
                })
        );


        return http.build();
    }
}
