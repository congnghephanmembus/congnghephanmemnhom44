package com.nhom.xebus.repository;

import com.nhom.xebus.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaiKhoanRepository
        extends JpaRepository<TaiKhoan, String> {

    Optional<TaiKhoan> findByTenDangNhap(String tenDangNhap);
    // Lấy mã tài khoản lớn nhất
    @Query("SELECT MAX(t.maTaiKhoan) FROM TaiKhoan t")
    String findMaxMaTaiKhoan();
}