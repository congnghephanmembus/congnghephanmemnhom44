package com.nhom.xebus.service;

import com.nhom.xebus.entity.TaiKhoan;
import com.nhom.xebus.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    public List<TaiKhoan> layTatCa() {
        return taiKhoanRepository.findAll();
    }

    public TaiKhoan timTheoMa(String maTaiKhoan) {
        return taiKhoanRepository.findById(maTaiKhoan).orElse(null);
    }

    public TaiKhoan timTheoTenDangNhap(String tenDangNhap) {
        return taiKhoanRepository.findAll().stream()
                .filter(t -> t.getTenDangNhap().equals(tenDangNhap))
                .findFirst()
                .orElse(null);
    }

    public void luu(TaiKhoan taiKhoan) {
        // Nếu là tạo mới (chưa có mã), set ngày tạo
        if (taiKhoan.getMaTaiKhoan() == null || taiKhoan.getMaTaiKhoan().isEmpty()) {
            taiKhoan.setMaTaiKhoan(sinhMaTaiKhoan());
            taiKhoan.setNgayTao(LocalDateTime.now());  // THÊM DÒNG NÀY
        }
        
        // NẾU ĐANG SỬA, KHÔNG SET LẠI NGÀY TẠO
        // Nếu ngayTao vẫn null (trường hợp tạo mới mà chưa set), set luôn
        if (taiKhoan.getNgayTao() == null) {
            taiKhoan.setNgayTao(LocalDateTime.now());
        }
        
        taiKhoanRepository.save(taiKhoan);
    }

    public void xoa(String maTaiKhoan) {
        taiKhoanRepository.deleteById(maTaiKhoan);
    }

    // =========================
    // SINH MÃ TÀI KHOẢN TỰ ĐỘNG
    // =========================
    public String sinhMaTaiKhoan() {

        String maxMa = taiKhoanRepository.findMaxMaTaiKhoan();

        if (maxMa == null || maxMa.isEmpty()) {
            return "TK001";
        }

        String soStr = maxMa.substring(2);

        try {
            int so = Integer.parseInt(soStr);
            so++;
            return String.format("TK%03d", so);
        } catch (NumberFormatException e) {
            return "TK001";
        }
    }
}