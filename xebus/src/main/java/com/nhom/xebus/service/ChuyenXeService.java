package com.nhom.xebus.service;

import com.nhom.xebus.entity.ChuyenXe;
import com.nhom.xebus.repository.ChuyenXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChuyenXeService {

    @Autowired
    private ChuyenXeRepository chuyenXeRepository;

    public List<ChuyenXe> layTatCa() {
        return chuyenXeRepository.findAll();
    }

    public void luu(ChuyenXe chuyenXe) {
        chuyenXeRepository.save(chuyenXe);
    }

    public void them(ChuyenXe chuyenXe) {
        chuyenXeRepository.save(chuyenXe);
    }

    public ChuyenXe timTheoMa(String maChuyen) {
        return chuyenXeRepository.findById(maChuyen).orElse(null);
    }

    public void xoa(String maChuyen) {
        chuyenXeRepository.deleteById(maChuyen);
    }

    // =========================
    // SINH MÃ CHUYẾN TỰ ĐỘNG
    // =========================
    public String sinhMaChuyen() {

        // Lấy mã chuyến lớn nhất
        String maxMa = chuyenXeRepository.findMaxMaChuyen();

        if (maxMa == null || maxMa.isEmpty()) {
            // Nếu chưa có chuyến nào, bắt đầu từ C001
            return "C001";
        }

        // Tách phần số từ mã chuyến (VD: C012 -> 12)
        String soStr = maxMa.substring(1); // bỏ chữ C

        try {
            int so = Integer.parseInt(soStr);
            so++; // tăng lên 1
            return String.format("C%03d", so); // C001, C002, ..., C999
        } catch (NumberFormatException e) {
            // Nếu mã không đúng định dạng, trả về C001
            return "C001";
        }
    }
}