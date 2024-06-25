package com.rca.myspringsecurity.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.rca.myspringsecurity.service.UserDataService;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;
    // User Creation
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDataService();
    }
    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth.requestMatchers(
                            "/users/welcome",
                            "/users/addNewUser",
                            "/users/generateToken",
                            "/academics/**",
                            "/laptops/registration",
                            "/swagger-resources/**",
                            "/configuration/ui",
                            "/configuration/security",
                            "/swagger-ui/**",
                            "/webjars/**",
                            "/swagger-ui.html",
                            "/v3/api-docs/**"
                            ).permitAll())
                    .authorizeHttpRequests(auth -> auth.requestMatchers("/users/user/**").authenticated())
                    .authorizeHttpRequests(auth -> auth.requestMatchers("/users/admin/**").authenticated())
                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        }
        // Password Encoding
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
        @Bean
        public AuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userDetailsService());
            authenticationProvider.setPasswordEncoder(passwordEncoder());
            return authenticationProvider;
        }
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }
    }
