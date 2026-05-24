package com.nhom.xebus.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "NhatKyHeThong")
public class NhatKyHeThong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNhatKy")
    private Integer maNhatKy;

    @Column(name = "ThoiGian")
    private LocalDateTime thoiGian;

    @Column(name = "MaTaiKhoan")
    private String maTaiKhoan;

    @Column(name = "ChucNang")
    private String chucNang;

    @Column(name = "DoiTuongTacDong")
    private String doiTuongTacDong;

    @Column(name = "MaDoiTuong")
    private String maDoiTuong;

    @Column(name = "NoiDungChiTiet")
    private String noiDungChiTiet;

    @Column(name = "KetQua")
    private String ketQua;

    @Column(name = "GhiChu")
    private String ghiChu;

    public Integer getMaNhatKy() {
        return maNhatKy;
    }

    public void setMaNhatKy(Integer maNhatKy) {
        this.maNhatKy = maNhatKy;
    }

    public LocalDateTime getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(LocalDateTime thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getChucNang() {
        return chucNang;
    }

    public void setChucNang(String chucNang) {
        this.chucNang = chucNang;
    }

    public String getDoiTuongTacDong() {
        return doiTuongTacDong;
    }

    public void setDoiTuongTacDong(String doiTuongTacDong) {
        this.doiTuongTacDong = doiTuongTacDong;
    }

    public String getMaDoiTuong() {
        return maDoiTuong;
    }

    public void setMaDoiTuong(String maDoiTuong) {
        this.maDoiTuong = maDoiTuong;
    }

    public String getNoiDungChiTiet() {
        return noiDungChiTiet;
    }

    public void setNoiDungChiTiet(String noiDungChiTiet) {
        this.noiDungChiTiet = noiDungChiTiet;
    }

    public String getKetQua() {
        return ketQua;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    // getter setter
    

}