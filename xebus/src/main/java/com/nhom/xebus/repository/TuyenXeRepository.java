package com.nhom.xebus.repository;

import com.nhom.xebus.entity.TuyenXe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TuyenXeRepository extends JpaRepository<TuyenXe, String> {

    // Lấy mã tuyến lớn nhất
    @Query("SELECT MAX(t.maTuyen) FROM TuyenXe t")
    String findMaxMaTuyen();

    // Phân trang với Spring Data JPA
    Page<TuyenXe> findAll(Pageable pageable);
}
