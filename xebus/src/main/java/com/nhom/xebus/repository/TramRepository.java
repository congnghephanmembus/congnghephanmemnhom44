package com.nhom.xebus.repository;

import com.nhom.xebus.entity.Tram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TramRepository
        extends JpaRepository<Tram, String> {
}