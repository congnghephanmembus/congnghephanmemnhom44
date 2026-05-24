package com.nhom.xebus.service;

import com.nhom.xebus.entity.NhatKyHeThong;
import com.nhom.xebus.entity.TaiKhoan;
import com.nhom.xebus.repository.NhatKyHeThongRepository;
import com.nhom.xebus.repository.TaiKhoanRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

        Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = auth.getName();

        TaiKhoan taiKhoan =
                taiKhoanRepository
                        .findByTenDangNhap(username)
                        .orElse(null);

        NhatKyHeThong log =
                new NhatKyHeThong();

        log.setThoiGian(LocalDateTime.now());

        // TẠM KHÔNG GHI MA TAI KHOAN
        // vì entity đang dùng TenDangNhap làm @Id
        log.setMaTaiKhoan(null);

        log.setChucNang(chucNang);

        log.setDoiTuongTacDong(doiTuong);

        log.setMaDoiTuong(maDoiTuong);

        log.setNoiDungChiTiet(noiDung);

        log.setKetQua("Thành công");

        repository.save(log);
    }
}