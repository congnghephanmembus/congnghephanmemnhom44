package com.nhom.xebus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalTime;

@Entity
@Table(name = "LichChay")
public class LichChay {

    @Id
    @Column(name = "MaLich")
    private String maLich;

    @Column(name = "MaTuyen")
    private String maTuyen;

    @Column(name = "GioBatDau")
    private LocalTime gioBatDau;

    @Column(name = "GioKetThuc")
    private LocalTime gioKetThuc;

    @Column(name = "TanSuatPhut")
    private Integer tanSuatPhut;

    // Constructor rỗng
    public LichChay() {
    }

    // Getter và Setter

    public String getMaLich() {
        return maLich;
    }

    public void setMaLich(String maLich) {
        this.maLich = maLich;
    }

    public String getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(String maTuyen) {
        this.maTuyen = maTuyen;
    }

    public LocalTime getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(LocalTime gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public LocalTime getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(LocalTime gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public Integer getTanSuatPhut() {
        return tanSuatPhut;
    }

    public void setTanSuatPhut(Integer tanSuatPhut) {
        this.tanSuatPhut = tanSuatPhut;
    }
}