package com.LeaderBoard.Real_timeLeader.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF (Must be disabled for POST requests to work)
                .csrf(csrf -> csrf.disable())

                // 2. Configure CORS manually to avoid 'Customizer' errors
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("*"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    return config;
                }))

                // 3. Authorization Rules (ORDER MATTERS)
                .authorizeHttpRequests(auth -> auth
                        // Allow static files and Auth APIs
                        .requestMatchers("/", "/login.html", "/register.html", "/dash.html", "/style.css", "/*.js").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()

                        // Allow Leaderboard APIs (This stops the 403 errors)
                        .requestMatchers("/api/leaderboard/**").permitAll()

                        // Allow H2 Console
                        .requestMatchers("/h2-console/**").permitAll()

                        // Catch-all
                        .anyRequest().authenticated()
                )

                // 4. Session & Header handling
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

