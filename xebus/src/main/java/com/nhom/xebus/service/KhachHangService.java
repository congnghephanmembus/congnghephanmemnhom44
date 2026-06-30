package com.nhom.xebus.service;

import com.nhom.xebus.entity.KhachHang;
import com.nhom.xebus.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<KhachHang> layTatCa() {
        return khachHangRepository.findAll();
    }

    public KhachHang timTheoMa(String maKH) {
        return khachHangRepository.findById(maKH).orElse(null);
    }

    public void luu(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
    }

    public void xoa(String maKH) {
        khachHangRepository.deleteById(maKH);
    }

    // =========================
    // SINH MÃ KHÁCH HÀNG TỰ ĐỘNG
    // =========================
    public String sinhMaKH() {

        // Lấy mã khách hàng lớn nhất
        String maxMa = khachHangRepository.findMaxMaKH();

        if (maxMa == null || maxMa.isEmpty()) {
            // Nếu chưa có khách hàng nào, bắt đầu từ KH001
            return "KH001";
        }

        // Tách phần số từ mã (VD: KH012 -> 12)
        String soStr = maxMa.substring(2); // bỏ chữ "KH"

        try {
            int so = Integer.parseInt(soStr);
            so++; // tăng lên 1
            return String.format("KH%03d", so); // KH001, KH002, ..., KH999
        } catch (NumberFormatException e) {
            return "KH001";
        }
    }
}