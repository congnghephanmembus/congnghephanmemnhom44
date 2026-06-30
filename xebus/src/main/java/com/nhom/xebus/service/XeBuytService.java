package com.nhom.xebus.service;

import com.nhom.xebus.entity.XeBuyt;
import com.nhom.xebus.repository.XeBuytRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XeBuytService {

    @Autowired
    private XeBuytRepository xeBuytRepository;

    public List<XeBuyt> layTatCa() {
        return xeBuytRepository.findAll();
    }

    public XeBuyt timTheoMa(String maXe) {
        return xeBuytRepository.findById(maXe).orElse(null);
    }

    public void luu(XeBuyt xe) {
        xeBuytRepository.save(xe);
    }

    public void xoa(String maXe) {
        xeBuytRepository.deleteById(maXe);
    }

    // =========================
    // SINH MÃ XE TỰ ĐỘNG
    // =========================
    public String sinhMaXe() {

        String maxMa = xeBuytRepository.findMaxMaXe();

        if (maxMa == null || maxMa.isEmpty()) {
            return "XE001";
        }

        // Tách phần số từ mã (VD: XE012 -> 12)
        String soStr = maxMa.substring(2); // bỏ chữ "XE"

        try {
            int so = Integer.parseInt(soStr);
            so++;
            return String.format("XE%03d", so);
        } catch (NumberFormatException e) {
            return "XE001";
        }
    }
}