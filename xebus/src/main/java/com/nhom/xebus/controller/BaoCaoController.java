package com.nhom.xebus.controller;

import com.nhom.xebus.repository.LoaiVeRepository;
import com.nhom.xebus.repository.VeRepository;
import com.nhom.xebus.service.ChuyenXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bao-cao")
public class BaoCaoController {

    @Autowired
    private VeRepository veRepository;

    @Autowired
    private LoaiVeRepository loaiVeRepository;

    @Autowired
    private ChuyenXeService chuyenXeService;

    @GetMapping
    public String baoCao(Model model) {

        // =========================================
        // 1. TỔNG SỐ VÉ
        // =========================================
        long tongVe = veRepository.count();
        model.addAttribute("tongVe", tongVe);

        // =========================================
        // 2. TỔNG DOANH THU (TỪ BẢNG VÉ)
        // =========================================
        BigDecimal doanhThu = veRepository.tinhTongDoanhThu();
        if (doanhThu == null) {
            doanhThu = BigDecimal.ZERO;
        }
        model.addAttribute("doanhThu", doanhThu);

        // =========================================
        // 3. CHI TIẾT DOANH THU THEO LOẠI VÉ
        // =========================================
        List<Object[]> results = loaiVeRepository.thongKeDoanhThuTheoLoaiVe();
        List<Map<String, Object>> chiTietDoanhThu = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> item = new HashMap<>();
            item.put("maLoaiVe", row[0]);
            item.put("tenLoaiVe", row[1]);
            item.put("donGia", row[2]);
            item.put("soLuong", row[3]);
            // Tính thành tiền
            BigDecimal donGia = (BigDecimal) row[2];
            Long soLuong = (Long) row[3];
            if (donGia != null && soLuong != null) {
                item.put("thanhTien", donGia.multiply(BigDecimal.valueOf(soLuong)));
            } else {
                item.put("thanhTien", BigDecimal.ZERO);
            }
            chiTietDoanhThu.add(item);
        }

        model.addAttribute("chiTietDoanhThu", chiTietDoanhThu);

        // =========================================
        // 4. TỔNG CHUYẾN HOẠT ĐỘNG
        // =========================================
        long tongChuyen = chuyenXeService.layTatCa().size();
        model.addAttribute("tongChuyen", tongChuyen);

        return "bao-cao/index";
    }
}