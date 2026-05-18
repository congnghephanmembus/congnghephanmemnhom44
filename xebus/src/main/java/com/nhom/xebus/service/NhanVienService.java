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

    public void luu(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

    public void them(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

    public NhanVien timTheoMa(String ma) {
        return nhanVienRepository.findById(ma).orElse(null);
    }

    public void xoa(String ma) {
        nhanVienRepository.deleteById(ma);
    }
}