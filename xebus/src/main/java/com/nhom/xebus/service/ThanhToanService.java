package com.nhom.xebus.service;

import com.nhom.xebus.entity.ThanhToan;
import com.nhom.xebus.repository.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThanhToanService {

    @Autowired
    private ThanhToanRepository thanhToanRepository;

    public List<ThanhToan> layTatCa() {
        return thanhToanRepository.findAll();
    }

    public void luu(ThanhToan thanhToan) {
        thanhToanRepository.save(thanhToan);
    }

    public ThanhToan timTheoMa(String ma) {
        return thanhToanRepository.findById(ma).orElse(null);
    }

    public void xoa(String ma) {
        thanhToanRepository.deleteById(ma);
    }
}