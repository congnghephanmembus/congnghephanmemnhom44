package com.nhom.xebus.controller;

import com.nhom.xebus.entity.ChuyenXe;
import com.nhom.xebus.service.ChuyenXeService;
import com.nhom.xebus.service.NhatKyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chuyen")
public class ChuyenXeController {

    @Autowired
    private ChuyenXeService chuyenXeService;

    @Autowired
    private NhatKyService nhatKyService;

    // =========================
    // DANH SÁCH
    // =========================

    @GetMapping
    public String danhSach(Model model) {

        model.addAttribute(
                "danhSach",
                chuyenXeService.layTatCa()
        );

        return "chuyen/danh-sach";
    }

    // =========================
    // CHI TIẾT
    // =========================

    @GetMapping("/chi-tiet/{ma}")
    public String chiTiet(
            @PathVariable String ma,
            Model model
    ) {

        ChuyenXe chuyenXe =
                chuyenXeService.timTheoMa(ma);

        if (chuyenXe == null) {

            return "redirect:/chuyen";
        }

        model.addAttribute(
                "chuyenXe",
                chuyenXe
        );

        nhatKyService.ghiLog(
                "Quản lý chuyến xe",
                "Chuyến xe",
                ma,
                "Xem chi tiết chuyến xe"
        );

        return "chuyen/chi-tiet";
    }

    // =========================
    // FORM THÊM
    // =========================

    @GetMapping("/them")
    public String formThem(Model model) {

        model.addAttribute(
                "chuyenXe",
                new ChuyenXe()
        );

        return "chuyen/form";
    }

    // =========================
    // LƯU
    // =========================

    @PostMapping("/luu")
    public String luu(
            @ModelAttribute("chuyenXe")
            ChuyenXe chuyenXe
    ) {

        chuyenXeService.luu(chuyenXe);

        nhatKyService.ghiLog(
                "Quản lý chuyến xe",
                "Chuyến xe",
                chuyenXe.getMaChuyen(),
                "Thêm hoặc cập nhật chuyến xe"
        );

        return "redirect:/chuyen";
    }

    // =========================
    // FORM SỬA
    // =========================

    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        ChuyenXe chuyenXe =
                chuyenXeService.timTheoMa(ma);

        if (chuyenXe == null) {

            return "redirect:/chuyen";
        }

        model.addAttribute(
                "chuyenXe",
                chuyenXe
        );

        return "chuyen/form";
    }

    // =========================
    // XOÁ
    // =========================

    @GetMapping("/xoa/{ma}")
    public String xoa(@PathVariable String ma) {

        chuyenXeService.xoa(ma);

        nhatKyService.ghiLog(
                "Quản lý chuyến xe",
                "Chuyến xe",
                ma,
                "Xoá chuyến xe"
        );

        return "redirect:/chuyen";
    }
}