package com.nhom.xebus.repository;

import com.nhom.xebus.entity.Ve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VeRepository extends JpaRepository<Ve, String> {

    // Lấy mã vé lớn nhất
    @Query("SELECT MAX(v.maVe) FROM Ve v")
    String findMaxMaVe();

    // TÍNH TỔNG DOANH THU TỪ BẢNG VÉ
    @Query("SELECT SUM(l.donGia) FROM Ve v JOIN LoaiVe l ON v.maLoaiVe = l.maLoaiVe")
    BigDecimal tinhTongDoanhThu();

    // TÍNH TỔNG DOANH THU THEO KHOẢNG THỜI GIAN
    @Query("SELECT SUM(l.donGia) FROM Ve v JOIN LoaiVe l ON v.maLoaiVe = l.maLoaiVe WHERE v.ngayMua BETWEEN :startDate AND :endDate")
    BigDecimal tinhTongDoanhThuTheoNgay(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

// Đếm tổng số vé
    @Query("SELECT COUNT(v) FROM Ve v")
    long demTongSoVe();

    // Tính doanh thu trong tuần (7 ngày gần nhất)
    @Query("SELECT SUM(l.donGia) FROM Ve v JOIN LoaiVe l ON v.maLoaiVe = l.maLoaiVe WHERE v.ngayMua >= :startDate")
    BigDecimal tinhTongDoanhThuTuan(@Param("startDate") LocalDateTime startDate);
}
