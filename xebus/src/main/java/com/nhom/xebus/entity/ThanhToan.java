package com.nhom.xebus.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ThanhToan", schema = "dbo")
@Data
public class ThanhToan {

    @Id
    @Column(name = "MaThanhToan")
    private String maThanhToan;

    @Column(name = "MaVe")
    private String maVe;

    @Column(name = "NgayThanhToan")
    private LocalDateTime ngayThanhToan;

    @Column(name = "PhuongThuc")
    private String phuongThuc;

    @Column(name = "SoTien")
    private BigDecimal soTien;
}