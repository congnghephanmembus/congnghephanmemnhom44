package com.nhom.xebus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "ChuyenXe", schema = "dbo")
@Data
public class ChuyenXe {

    @Id
    @Column(name = "MaChuyen", length = 10, nullable = false)
    private String maChuyen;

    @Column(name = "MaTuyen", length = 10, nullable = false)
    private String maTuyen;

    @Column(name = "MaXe", length = 10, nullable = false)
    private String maXe;

    @Column(name = "MaLich", length = 10)
    private String maLich;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "GioKhoiHanh", nullable = false)
    private LocalDateTime gioKhoiHanh;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "GioDenDuKien")
    private LocalDateTime gioDenDuKien;

    @Column(name = "TrangThaiChuyen", length = 50)
    private String trangThaiChuyen;
}