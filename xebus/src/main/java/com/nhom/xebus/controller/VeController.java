package com.nhom.xebus.controller;

import com.nhom.xebus.entity.Ve;
import com.nhom.xebus.service.NhatKyService;
import com.nhom.xebus.service.VeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ve")
public class VeController {

    @Autowired
    private VeService veService;

    @Autowired
    private NhatKyService nhatKyService;

    // =========================
    // DANH SÁCH
    // Admin + Manager + Staff
    // =========================
    @GetMapping
    public String danhSach(Model model) {

        model.addAttribute(
                "danhSach",
                veService.layTatCa()
        );

        return "ve/danh-sach";
    }

    // =========================
    // FORM THÊM
    // Admin + Staff
    // =========================
    @GetMapping("/them")
    public String formThem(Model model) {

        model.addAttribute(
                "ve",
                new Ve()
        );

        return "ve/form";
    }

    // =========================
    // LƯU
    // Admin + Staff
    // =========================
    @PostMapping("/luu")
    public String luu(
            @ModelAttribute("ve")
            Ve ve
    ) {

        veService.luu(ve);

        nhatKyService.ghiLog(
                "Quản lý vé",
                "Vé",
                ve.getMaVe(),
                "Thêm hoặc cập nhật vé"
        );

        return "redirect:/ve";
    }

    // =========================
    // FORM SỬA
    // Admin + Staff
    // =========================
    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        Ve ve = veService.timTheoMa(ma);

        if (ve == null) {

            return "redirect:/ve";
        }

        model.addAttribute(
                "ve",
                ve
        );

        return "ve/form";
    }

    // =========================
    // XOÁ
    // Chỉ Admin
    // =========================
    @GetMapping("/xoa/{ma}")
    public String xoa(@PathVariable String ma) {

        veService.xoa(ma);

        nhatKyService.ghiLog(
                "Quản lý vé",
                "Vé",
                ma,
                "Xoá vé"
        );

        return "redirect:/ve";
    }
}