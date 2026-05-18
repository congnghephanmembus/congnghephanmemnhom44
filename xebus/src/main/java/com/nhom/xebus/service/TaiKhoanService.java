package com.nhom.xebus.service;

import com.nhom.xebus.entity.TaiKhoan;
import com.nhom.xebus.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    public List<TaiKhoan> layTatCa() {
        return taiKhoanRepository.findAll();
    }

    public void luu(TaiKhoan taiKhoan) {
        taiKhoanRepository.save(taiKhoan);
    }

    public TaiKhoan timTheoMa(String ma) {
        return taiKhoanRepository.findById(ma).orElse(null);
    }

    public void xoa(String ma) {
        taiKhoanRepository.deleteById(ma);
    }
}