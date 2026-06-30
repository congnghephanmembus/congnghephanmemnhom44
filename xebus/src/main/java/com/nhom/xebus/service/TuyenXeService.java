package com.nhom.xebus.service;

import com.nhom.xebus.dto.PagingResponse;
import com.nhom.xebus.entity.TuyenXe;
import com.nhom.xebus.repository.TuyenXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TuyenXeService {

    @Autowired
    private TuyenXeRepository tuyenXeRepository;

    public List<TuyenXe> layTatCa() {
        return tuyenXeRepository.findAll();
    }

    public void luu(TuyenXe tuyenXe) {
        tuyenXeRepository.save(tuyenXe);
    }

    public void them(TuyenXe tuyenXe) {
        tuyenXeRepository.save(tuyenXe);
    }

    public TuyenXe timTheoMa(String maTuyen) {
        return tuyenXeRepository.findById(maTuyen).orElse(null);
    }

    public void xoa(String maTuyen) {
        tuyenXeRepository.deleteById(maTuyen);
    }

    // =========================
    // PHÂN TRANG
    // =========================
    public PagingResponse<TuyenXe> layPhanTrang(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("maTuyen").ascending());
        Page<TuyenXe> pageResult = tuyenXeRepository.findAll(pageable);

        return PagingResponse.of(
                pageResult.getContent(),
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements()
        );
    }

    // =========================
    // SINH MÃ TUYẾN TỰ ĐỘNG
    // =========================
    public String sinhMaTuyen() {

        // Lấy mã tuyến lớn nhất
        String maxMa = tuyenXeRepository.findMaxMaTuyen();

        if (maxMa == null || maxMa.isEmpty()) {
            // Nếu chưa có tuyến nào, bắt đầu từ T001
            return "T001";
        }

        // Tách phần số từ mã tuyến (VD: T012 -> 12)
        String soStr = maxMa.substring(1); // bỏ chữ T

        try {
            int so = Integer.parseInt(soStr);
            so++; // tăng lên 1
            return String.format("T%03d", so); // T001, T002, ..., T999
        } catch (NumberFormatException e) {
            // Nếu mã không đúng định dạng, trả về T001
            return "T001";
        }
    }
}