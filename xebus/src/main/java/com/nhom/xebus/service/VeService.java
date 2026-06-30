package com.nhom.xebus.service;

import com.nhom.xebus.entity.Ve;
import com.nhom.xebus.repository.VeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VeService {

    @Autowired
    private VeRepository veRepository;

    public List<Ve> layTatCa() {
        return veRepository.findAll();
    }

    public Ve timTheoMa(String maVe) {
        return veRepository.findById(maVe).orElse(null);
    }

    public void luu(Ve ve) {
        veRepository.save(ve);
    }

    public void xoa(String maVe) {
        veRepository.deleteById(maVe);
    }

// =========================
    // SINH MÃ VÉ TỰ ĐỘNG
    // =========================
    public String sinhMaVe() {

        String maxMa = veRepository.findMaxMaVe();

        if (maxMa == null || maxMa.isEmpty()) {
            return "V001";
        }

        String soStr = maxMa.substring(1); // bỏ chữ "V"

        try {
            int so = Integer.parseInt(soStr);
            so++;
            return String.format("V%03d", so);
        } catch (NumberFormatException e) {
            return "V001";
        }
    }

// Đếm tổng số vé
    public long demTongSoVe() {
        return veRepository.demTongSoVe();
    }

// Tính tổng doanh thu
    public BigDecimal tinhTongDoanhThu() {
        return veRepository.tinhTongDoanhThu();
    }

    // Tính doanh thu tuần (7 ngày gần nhất)
    public BigDecimal tinhTongDoanhThuTuan() {
        return veRepository.tinhTongDoanhThuTuan(java.time.LocalDateTime.now().minusDays(7));
    }
}
