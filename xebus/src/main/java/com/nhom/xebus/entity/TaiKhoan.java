package com.nhom.xebus.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "TaiKhoan", schema = "dbo")
@Data
public class TaiKhoan {

    @Id
    @Column(name = "MaTaiKhoan")
    private String maTaiKhoan;

    @Column(name = "TenDangNhap")
    private String tenDangNhap;

    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "VaiTro")
    private String vaiTro;

    @Column(name = "MaNV")
    private String maNV;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao;

    @Column(name = "NguoiCap")
    private String nguoiCap;
}