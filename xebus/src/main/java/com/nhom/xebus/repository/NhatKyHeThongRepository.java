package com.nhom.xebus.repository;

import com.nhom.xebus.entity.NhatKyHeThong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NhatKyHeThongRepository
        extends JpaRepository<NhatKyHeThong, Integer> {

    // Lấy các hoạt động gần đây (10 bản ghi mới nhất)
    @Query("SELECT n FROM NhatKyHeThong n ORDER BY n.thoiGian DESC")
    List<NhatKyHeThong> layHoatDongGanDay();
}
