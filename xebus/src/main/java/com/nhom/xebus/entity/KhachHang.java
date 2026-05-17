package com.nhom.xebus.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "KhachHang", schema = "dbo")
@Data
public class KhachHang {

    @Id
    @Column(name = "MaKH")
    private String maKH;

    @Column(name = "HoTen")
    private String hoTen;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "Email")
    private String email;

    @Column(name = "DiaChi")
    private String diaChi;
}