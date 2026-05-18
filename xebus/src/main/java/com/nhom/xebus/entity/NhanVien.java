package com.nhom.xebus.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "NhanVien", schema = "dbo")
@Data
public class NhanVien {

    @Id
    @Column(name = "MaNV")
    private String maNV;

    @Column(name = "HoTen")
    private String hoTen;

    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;

    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "ChucVu")
    private String chucVu;

    @Column(name = "Luong")
    private Double luong;

    @Column(name = "TrangThai")
    private String trangThai;
}