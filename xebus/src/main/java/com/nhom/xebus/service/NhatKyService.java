package com.nhom.xebus.service;

import com.nhom.xebus.entity.NhatKyHeThong;
import com.nhom.xebus.entity.TaiKhoan;
import com.nhom.xebus.repository.NhatKyHeThongRepository;
import com.nhom.xebus.repository.TaiKhoanRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NhatKyService {

    private final NhatKyHeThongRepository repository;
    private final TaiKhoanRepository taiKhoanRepository;

    public NhatKyService(
            NhatKyHeThongRepository repository,
            TaiKhoanRepository taiKhoanRepository
    ) {
        this.repository = repository;
        this.taiKhoanRepository = taiKhoanRepository;
    }

    public void ghiLog(
            String chucNang,
            String doiTuong,
            String maDoiTuong,
            String noiDung
    ) {

        try {
            Authentication auth =
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication();

            System.out.println("===== NHAT KY - auth: " + auth);
            System.out.println("===== NHAT KY - auth.getName(): " + (auth != null ? auth.getName() : "null"));

            if (auth == null || !auth.isAuthenticated()) {
                System.out.println("===== NHAT KY - Chua xac thuc, bo qua ghi log");
                return;
            }

            String username = auth.getName();
            System.out.println("===== NHAT KY - Username: '" + username + "'");

            TaiKhoan taiKhoan = null;

            // Chỉ tìm khi username không phải anonymousUser
            if (username != null && !username.equals("anonymousUser")) {
                taiKhoan = taiKhoanRepository
                        .findByTenDangNhap(username)
                        .orElse(null);
                System.out.println("===== NHAT KY - TaiKhoan tim thay: " + (taiKhoan != null ? taiKhoan.getMaTaiKhoan() : "null"));
            } else {
                System.out.println("===== NHAT KY - Username la anonymous, bo qua");
            }

            NhatKyHeThong log = new NhatKyHeThong();
            log.setThoiGian(LocalDateTime.now());

            if (taiKhoan != null) {
                log.setMaTaiKhoan(taiKhoan.getMaTaiKhoan());
                System.out.println("===== NHAT KY - Da set MaTaiKhoan: " + taiKhoan.getMaTaiKhoan());
            } else {
                log.setMaTaiKhoan(null);
                System.out.println("===== NHAT KY - MaTaiKhoan = null");
            }

            log.setChucNang(chucNang);
            log.setDoiTuongTacDong(doiTuong);
            log.setMaDoiTuong(maDoiTuong);
            log.setNoiDungChiTiet(noiDung);
            log.setKetQua("Thành công");

            repository.save(log);
            System.out.println("===== NHAT KY - Da luu log thanh cong");

} catch (Exception e) {
            System.out.println("===== NHAT KY - LOI: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Lấy các hoạt động gần đây cho trang chủ
    public List<NhatKyHeThong> layHoatDongGanDay() {
        return repository.layHoatDongGanDay();
    }
}
