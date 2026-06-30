package com.nhom.xebus.repository;

import com.nhom.xebus.entity.LoaiVe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaiVeRepository extends JpaRepository<LoaiVe, String> {

    // THỐNG KÊ DOANH THU THEO LOẠI VÉ
    @Query("SELECT l.maLoaiVe, l.tenLoaiVe, l.donGia, COUNT(v) as soLuong " +
           "FROM LoaiVe l LEFT JOIN Ve v ON l.maLoaiVe = v.maLoaiVe " +
           "GROUP BY l.maLoaiVe, l.tenLoaiVe, l.donGia")
    List<Object[]> thongKeDoanhThuTheoLoaiVe();
}