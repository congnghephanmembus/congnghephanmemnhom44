package com.nhom.xebus.repository;

import com.nhom.xebus.entity.XeBuyt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface XeBuytRepository extends JpaRepository<XeBuyt, String> {

    // Lấy mã xe lớn nhất
    @Query("SELECT MAX(x.maXe) FROM XeBuyt x")
    String findMaxMaXe();
}