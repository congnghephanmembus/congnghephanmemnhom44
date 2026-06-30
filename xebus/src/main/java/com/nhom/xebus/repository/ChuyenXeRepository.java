package com.nhom.xebus.repository;

import com.nhom.xebus.entity.ChuyenXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuyenXeRepository extends JpaRepository<ChuyenXe, String> {

    // Lấy mã chuyến lớn nhất
    @Query("SELECT MAX(c.maChuyen) FROM ChuyenXe c")
    String findMaxMaChuyen();
}