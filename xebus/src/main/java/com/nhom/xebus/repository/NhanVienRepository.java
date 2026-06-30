package com.nhom.xebus.repository;

import com.nhom.xebus.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {

    // Lấy mã nhân viên lớn nhất
    @Query("SELECT MAX(n.maNV) FROM NhanVien n")
    String findMaxMaNV();
}