package com.nhom.xebus.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "ChuyenXe", schema = "dbo")
@Data
public class ChuyenXe {

    @Id
    @Column(name = "MaChuyen")
    private String maChuyen;

    @Column(name = "MaTuyen")
    private String maTuyen;

    @Column(name = "MaXe")
    private String maXe;

    @Column(name = "MaLich")
    private String maLich;

    @Column(name = "GioKhoiHanh")
    private LocalDateTime gioKhoiHanh;

    @Column(name = "GioDenDuKien")
    private LocalDateTime gioDenDuKien;

    @Column(name = "TrangThaiChuyen")
    private String trangThaiChuyen;
}