package com.nhom.xebus.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "TuyenXe", schema = "dbo")
@Data
public class TuyenXe {

    @Id
    @Column(name = "MaTuyen")
    private String maTuyen;

    @Column(name = "TenTuyen")
    private String tenTuyen;

    @Column(name = "DiemDau")
    private String diemDau;

    @Column(name = "DiemCuoi")
    private String diemCuoi;

    @Column(name = "CuLy")
    private BigDecimal cuLy;

    @Column(name = "TrangThai")
    private String trangThai;
}