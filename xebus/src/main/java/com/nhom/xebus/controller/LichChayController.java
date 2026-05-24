package com.nhom.xebus.controller;

import com.nhom.xebus.entity.LichChay;
import com.nhom.xebus.repository.LichChayRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lich")
public class LichChayController {

    @Autowired
    private LichChayRepository lichChayRepository;

    // =========================
    // TẤT CẢ LỊCH CHẠY
    // =========================

    @GetMapping
    public String tatCaLich(Model model) {

        List<LichChay> danhSach =
                lichChayRepository.findAll();

        model.addAttribute(
                "danhSach",
                danhSach
        );

        return "lich/danh-sach";
    }

    // =========================
    // LỊCH CHẠY THEO TUYẾN
    // =========================

    @GetMapping("/tuyen/{ma}")
    public String xemLichTheoTuyen(
            @PathVariable("ma") String ma,
            Model model
    ) {

        List<LichChay> danhSach =
                lichChayRepository.findByTuyenXe_MaTuyen(ma);

        model.addAttribute(
                "danhSach",
                danhSach
        );

        model.addAttribute(
                "maTuyen",
                ma
        );

        return "lich/danh-sach";
    }

    // =========================
    // CHI TIẾT
    // =========================

    @GetMapping("/chi-tiet/{ma}")
    public String chiTiet(
            @PathVariable("ma") String ma,
            Model model
    ) {

        LichChay lich =
                lichChayRepository
                        .findById(ma)
                        .orElse(null);

        if (lich == null) {

            return "redirect:/lich";
        }

        model.addAttribute(
                "lich",
                lich
        );

        return "lich/chi-tiet";
    }
}