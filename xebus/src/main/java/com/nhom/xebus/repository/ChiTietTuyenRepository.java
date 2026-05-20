package com.nhom.xebus.repository;

import com.nhom.xebus.entity.ChiTietTuyen;
import com.nhom.xebus.entity.ChiTietTuyenId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChiTietTuyenRepository
        extends JpaRepository<ChiTietTuyen, ChiTietTuyenId> {

    List<ChiTietTuyen>
    findByMaTuyenOrderByThuTuTram(String maTuyen);
}