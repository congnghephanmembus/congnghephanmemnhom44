package com.nhom.xebus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "XeBuyt")
public class XeBuyt {

    @Id
    @Column(name = "MaXe")
    private String maXe;

    @Column(name = "BienSo")
    private String bienSo;

    @Column(name = "SucChua")
    private Integer sucChua;

    @Column(name = "LoaiXe")
    private String loaiXe;

    @Column(name = "TrangThai")
    private String trangThai;

    public XeBuyt() {
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public Integer getSucChua() {
        return sucChua;
    }

    public void setSucChua(Integer sucChua) {
        this.sucChua = sucChua;
    }

    public String getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}