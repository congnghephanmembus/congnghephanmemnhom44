package com.nhom.xebus.repository;

import com.nhom.xebus.entity.ChuyenXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuyenXeRepository extends JpaRepository<ChuyenXe, String> {
}