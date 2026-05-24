package com.nhom.xebus.repository;

import com.nhom.xebus.entity.LichChay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LichChayRepository
        extends JpaRepository<LichChay, String> {

    // Lấy danh sách lịch chạy theo mã tuyến
    List<LichChay> findByTuyenXe_MaTuyen(String maTuyen);

}