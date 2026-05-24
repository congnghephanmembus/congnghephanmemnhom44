package com.nhom.xebus.repository;

import com.nhom.xebus.entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThanhToanRepository
        extends JpaRepository<ThanhToan, String> {

    @Query("""
        SELECT SUM(t.soTien)
        FROM ThanhToan t
    """)
    Double tongDoanhThu();

}