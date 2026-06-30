package com.nhom.xebus.service;

import com.nhom.xebus.entity.NhanVien;
import com.nhom.xebus.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> layTatCa() {
        return nhanVienRepository.findAll();
    }

    public NhanVien timTheoMa(String maNV) {
        return nhanVienRepository.findById(maNV).orElse(null);
    }

    public void luu(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

    public void xoa(String maNV) {
        nhanVienRepository.deleteById(maNV);
    }

    // =========================
    // SINH MÃ NHÂN VIÊN TỰ ĐỘNG
    // =========================
    public String sinhMaNV() {

        String maxMa = nhanVienRepository.findMaxMaNV();

        if (maxMa == null || maxMa.isEmpty()) {
            return "NV001";
        }

        // Tách phần số từ mã (VD: NV012 -> 12)
        String soStr = maxMa.substring(2); // bỏ chữ "NV"

        try {
            int so = Integer.parseInt(soStr);
            so++;
            return String.format("NV%03d", so);
        } catch (NumberFormatException e) {
            return "NV001";
        }
    }
}