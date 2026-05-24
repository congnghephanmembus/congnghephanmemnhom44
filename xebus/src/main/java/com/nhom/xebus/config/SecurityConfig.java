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

                        // =========================
                        // PUBLIC
                        // =========================

                        .requestMatchers(
                                "/login",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // =========================
                        // ADMIN ONLY
                        // =========================

                        .requestMatchers(
                                "/tai-khoan/**",
                                "/nhat-ky/**"
                        ).hasRole("QuanTriVien")

                        // =========================
                        // BAO CAO
                        // ADMIN + MANAGER
                        // =========================

                        .requestMatchers(
                                "/bao-cao/**"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy"
                        )

                        // =========================
                        // TUYEN XE
                        // ADMIN + MANAGER + STAFF(XEM)
                        // =========================

                        .requestMatchers(
                                "/tuyen",
                                "/tuyen/",
                                "/tuyen/danh-sach",
                                "/tuyen/chi-tiet/**"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy",
                                "NhanVienBanVe"
                        )

                        .requestMatchers(
                                "/tuyen/them",
                                "/tuyen/luu",
                                "/tuyen/sua/**"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy"
                        )

                        .requestMatchers(
                                "/tuyen/xoa/**"
                        ).hasRole("QuanTriVien")

                        // =========================
                        // XE BUYT
                        // ADMIN + MANAGER
                        // =========================

                        .requestMatchers(
                                "/xe",
                                "/xe/",
                                "/xe/danh-sach"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy"
                        )

                        .requestMatchers(
                                "/xe/them",
                                "/xe/luu",
                                "/xe/sua/**"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy"
                        )

                        .requestMatchers(
                                "/xe/xoa/**"
                        ).hasRole("QuanTriVien")

                        // =========================
                        // CHUYEN XE
                        // STAFF chỉ xem
                        // =========================

                        .requestMatchers(
                                "/chuyen",
                                "/chuyen/",
                                "/chuyen/danh-sach",
                                "/chuyen/chi-tiet/**"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy",
                                "NhanVienBanVe"
                        )

                        .requestMatchers(
                                "/chuyen/them",
                                "/chuyen/luu",
                                "/chuyen/sua/**"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy"
                        )

                        // Manager được xóa chuyến
                        .requestMatchers(
                                "/chuyen/xoa/**"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy"
                        )

                        // =========================
                        // LICH CHAY
                        // ADMIN + MANAGER
                        // =========================

                        .requestMatchers(
                                "/lich",
                                "/lich/",
                                "/lich/danh-sach",
                                "/lich/**"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy"
                        )

                        .requestMatchers(
                                "/lich/them",
                                "/lich/luu",
                                "/lich/sua/**"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy"
                        )

                        .requestMatchers(
                                "/lich/xoa/**"
                        ).hasRole("QuanTriVien")

                        // =========================
                        // NHAN VIEN
                        // ADMIN + MANAGER
                        // =========================

                        .requestMatchers(
                                "/nhan-vien/**"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy"
                        )

                        // =========================
                        // KHACH HANG
                        // STAFF được thêm/sửa
                        // =========================

                        .requestMatchers(
                                "/khach-hang",
                                "/khach-hang/",
                                "/khach-hang/danh-sach"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy",
                                "NhanVienBanVe"
                        )

                        .requestMatchers(
                                "/khach-hang/them",
                                "/khach-hang/luu",
                                "/khach-hang/sua/**"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "NhanVienBanVe"
                        )

                        .requestMatchers(
                                "/khach-hang/xoa/**"
                        ).hasRole("QuanTriVien")

                        // =========================
                        // VE
                        // STAFF được thêm/sửa
                        // =========================

                        .requestMatchers(
                                "/ve",
                                "/ve/",
                                "/ve/danh-sach"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy",
                                "NhanVienBanVe"
                        )

                        .requestMatchers(
                                "/ve/them",
                                "/ve/luu",
                                "/ve/sua/**"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "NhanVienBanVe"
                        )

                        .requestMatchers(
                                "/ve/xoa/**"
                        ).hasRole("QuanTriVien")

                        // =========================
                        // THANH TOAN
                        // STAFF chỉ thêm
                        // =========================

                        .requestMatchers(
                                "/thanh-toan",
                                "/thanh-toan/",
                                "/thanh-toan/danh-sach"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "QuanLy",
                                "NhanVienBanVe"
                        )

                        .requestMatchers(
                                "/thanh-toan/them",
                                "/thanh-toan/luu"
                        ).hasAnyRole(
                                "QuanTriVien",
                                "NhanVienBanVe"
                        )

                        .requestMatchers(
                                "/thanh-toan/sua/**",
                                "/thanh-toan/xoa/**"
                        ).hasRole("QuanTriVien")

                        // =========================
                        // ALL
                        // =========================

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

            response.sendRedirect("/");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}