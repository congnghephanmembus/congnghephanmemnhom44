package com.nhom.xebus.service;

import com.nhom.xebus.entity.TaiKhoan;
import com.nhom.xebus.repository.TaiKhoanRepository;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TaiKhoanRepository taiKhoanRepository;

    public CustomUserDetailsService(TaiKhoanRepository taiKhoanRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTenDangNhap(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản: " + username));

        if (taiKhoan.getTrangThai() != null && taiKhoan.getTrangThai().equalsIgnoreCase("Bị khoá")) {
            throw new DisabledException("Tài khoản đã bị khóa");
        }

        String role = normalizeRole(taiKhoan.getVaiTro());

        return User.withUsername(taiKhoan.getTenDangNhap())
                .password(taiKhoan.getMatKhau())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_" + role)))
                .build();
    }

    private String normalizeRole(String rawRole) {
        if (rawRole == null) {
            return "NhanVienBanVe";
        }
        return rawRole.trim();
    }
}
