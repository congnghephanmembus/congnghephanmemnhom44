package com.nhom.xebus.service;

import com.nhom.xebus.entity.LichChay;
import com.nhom.xebus.repository.LichChayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LichChayService {

    @Autowired
    private LichChayRepository lichChayRepository;

    public List<LichChay> layTatCa() {
        return lichChayRepository.findAll();
    }

    public LichChay timTheoMa(String maLich) {
        return lichChayRepository.findById(maLich).orElse(null);
    }

    public void luu(LichChay lichChay) {
        lichChayRepository.save(lichChay);
    }

    public void xoa(String maLich) {
        lichChayRepository.deleteById(maLich);
    }

    public List<LichChay> layTheoMaTuyen(String maTuyen) {
        return lichChayRepository.findByTuyenXe_MaTuyen(maTuyen);
    }

    // =========================
// SINH MÃ LỊCH TỰ ĐỘNG
// =========================
public String sinhMaLich() {

    String maxMa = lichChayRepository.findMaxMaLich();

    if (maxMa == null || maxMa.isEmpty()) {
        return "L001";
    }

    String soStr = maxMa.substring(1);

    try {
        int so = Integer.parseInt(soStr);
        so++;
        return String.format("L%03d", so);
    } catch (NumberFormatException e) {
        return "L001";
    }
}
}