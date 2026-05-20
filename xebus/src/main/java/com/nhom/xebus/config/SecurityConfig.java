package com.nhom.xebus.config;

import com.nhom.xebus.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .userDetailsService(customUserDetailsService)

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/login",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // ADMIN
                        .requestMatchers(
                                "/taikhoan/**",
                                "/nhatky/**"
                        ).hasRole("QuanTriVien")

                        // MANAGER + ADMIN
                        .requestMatchers(
                                "/tuyen/**",
                                "/xe/**",
                                "/chuyen/**",
                                "/lich/**",
                                "/nhanvien/**",
                                "/baocao/**"
                        ).hasAnyRole("QuanLy", "QuanTriVien")

                        // SELLER + MANAGER + ADMIN
                        .requestMatchers(
                                "/ve/**",
                                "/khachhang/**",
                                "/thanhtoan/**"
                        ).hasAnyRole(
                                "NhanVienBanVe",
                                "QuanLy",
                                "QuanTriVien"
                        )

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(authenticationSuccessHandler())
                        .failureUrl("/login?error")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                .csrf(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {

        return (request, response, authentication) -> {

            var authorities = authentication.getAuthorities();

            // ADMIN
            if (authorities.stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_QuanTriVien"))) {

                response.sendRedirect("/");
                return;
            }

            // MANAGER
            if (authorities.stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_QuanLy"))) {

                response.sendRedirect("/");
                return;
            }

            // SELLER
            response.sendRedirect("/");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}