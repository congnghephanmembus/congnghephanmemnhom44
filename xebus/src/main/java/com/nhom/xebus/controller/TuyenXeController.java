package com.nhom.xebus.controller;

import com.nhom.xebus.entity.ChiTietTuyen;
import com.nhom.xebus.entity.Tram;
import com.nhom.xebus.entity.TuyenXe;
import com.nhom.xebus.repository.ChiTietTuyenRepository;
import com.nhom.xebus.repository.TramRepository;
import com.nhom.xebus.service.NhatKyService;
import com.nhom.xebus.service.TuyenXeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tuyen")
public class TuyenXeController {

    @Autowired
    private TuyenXeService tuyenXeService;

    @Autowired
    private ChiTietTuyenRepository chiTietTuyenRepository;

    @Autowired
    private TramRepository tramRepository;

    @Autowired
    private NhatKyService nhatKyService;

    // =========================
    // DANH SÁCH TUYẾN
    // =========================

    @GetMapping
    public String danhSach(Model model) {

        model.addAttribute(
                "danhSach",
                tuyenXeService.layTatCa()
        );

        return "tuyen/danh-sach";
    }

    // =========================
    // FORM THÊM
    // =========================

    @GetMapping("/them")
    public String formThem(Model model) {

        model.addAttribute(
                "tuyenXe",
                new TuyenXe()
        );

        return "tuyen/form";
    }

    // =========================
    // LƯU
    // =========================

    @PostMapping("/luu")
    public String luu(
            @ModelAttribute("tuyenXe")
            TuyenXe tuyenXe
    ) {

        tuyenXeService.luu(tuyenXe);

        nhatKyService.ghiLog(
                "Quản lý tuyến xe",
                "Tuyến xe",
                tuyenXe.getMaTuyen(),
                "Thêm hoặc cập nhật tuyến xe"
        );

        return "redirect:/tuyen";
    }

    // =========================
    // FORM SỬA
    // =========================

    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        TuyenXe tuyenXe =
                tuyenXeService.timTheoMa(ma);

        if (tuyenXe == null) {

            return "redirect:/tuyen";
        }

        model.addAttribute(
                "tuyenXe",
                tuyenXe
        );

        return "tuyen/form";
    }

    // =========================
    // XOÁ
    // =========================

    @GetMapping("/xoa/{ma}")
    public String xoa(
            @PathVariable String ma
    ) {

        tuyenXeService.xoa(ma);

        nhatKyService.ghiLog(
                "Quản lý tuyến xe",
                "Tuyến xe",
                ma,
                "Xoá tuyến xe"
        );

        return "redirect:/tuyen";
    }

    // =========================
    // CHI TIẾT TUYẾN
    // =========================

    @GetMapping("/chi-tiet/{ma}")
    public String chiTietTuyen(
            @PathVariable String ma,
            Model model
    ) {

        TuyenXe tuyenXe =
                tuyenXeService.timTheoMa(ma);

        if (tuyenXe == null) {

            return "redirect:/tuyen";
        }

        List<ChiTietTuyen> dsChiTiet =
                chiTietTuyenRepository
                        .findByMaTuyenOrderByThuTuTram(ma);

        List<Tram> dsTram =
                new ArrayList<>();

        for (ChiTietTuyen ct : dsChiTiet) {

            tramRepository
                    .findById(ct.getMaTram())
                    .ifPresent(dsTram::add);
        }

        model.addAttribute(
                "tuyenXe",
                tuyenXe
        );

        model.addAttribute(
                "dsTram",
                dsTram
        );

        return "tuyen/chi-tiet";
    }
}