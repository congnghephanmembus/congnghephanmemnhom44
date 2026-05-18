package com.nhom.xebus.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TaiKhoan", schema = "dbo")
@Data
public class TaiKhoan {

    @Id
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
}