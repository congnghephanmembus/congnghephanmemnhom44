package com.nhom.xebus.repository;

import com.nhom.xebus.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, String> {

    // Lấy mã khách hàng lớn nhất
    @Query("SELECT MAX(k.maKH) FROM KhachHang k")
    String findMaxMaKH();
}