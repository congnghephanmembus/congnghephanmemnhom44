package com.nhom.xebus.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "LichChay")
public class LichChay {

    // =========================
    // PRIMARY KEY
    // =========================

    @Id
    @Column(name = "MaLich")
    private String maLich;

    // =========================
    // THÔNG TIN LỊCH CHẠY
    // =========================

    @Column(name = "GioBatDau")
    private LocalTime gioBatDau;

    @Column(name = "GioKetThuc")
    private LocalTime gioKetThuc;

    @Column(name = "TanSuatPhut")
    private Integer tanSuatPhut;

    // =========================
    // LIÊN KẾT TUYẾN XE
    // =========================

    @ManyToOne
    @JoinColumn(name = "MaTuyen")
    private TuyenXe tuyenXe;

    // =========================
    // CONSTRUCTOR
    // =========================

    public LichChay() {
    }

    // =========================
    // GETTER & SETTER
    // =========================

    public String getMaLich() {
        return maLich;
    }

    public void setMaLich(String maLich) {
        this.maLich = maLich;
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

    public TuyenXe getTuyenXe() {
        return tuyenXe;
    }

    public void setTuyenXe(TuyenXe tuyenXe) {
        this.tuyenXe = tuyenXe;
    }

    // =========================
    // HELPER
    // =========================

    public String getMaTuyen() {

        if (tuyenXe == null) {
            return "";
        }

        return tuyenXe.getMaTuyen();
    }
}