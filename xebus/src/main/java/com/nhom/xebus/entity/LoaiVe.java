package com.nhom.xebus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "LoaiVe", schema = "dbo")
@Data
public class LoaiVe {

    @Id
    @Column(name = "MaLoaiVe")
    private String maLoaiVe;

    @Column(name = "TenLoaiVe")
    private String tenLoaiVe;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "DonGia")
    private BigDecimal donGia;
}