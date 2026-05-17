package com.nhom.xebus.repository;

import com.nhom.xebus.entity.Ve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeRepository extends JpaRepository<Ve, String> {
}