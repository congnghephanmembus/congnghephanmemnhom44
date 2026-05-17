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

    public void luu(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
    }

    public KhachHang timTheoMa(String ma) {
        return khachHangRepository.findById(ma).orElse(null);
    }

    public void xoa(String ma) {
        khachHangRepository.deleteById(ma);
    }
}