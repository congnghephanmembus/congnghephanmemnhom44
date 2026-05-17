package com.nhom.xebus.repository;

import com.nhom.xebus.entity.TuyenXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuyenXeRepository extends JpaRepository<TuyenXe, String> {
}