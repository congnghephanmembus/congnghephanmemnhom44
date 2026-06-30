package com.nhom.xebus.controller;

import com.nhom.xebus.entity.XeBuyt;
import com.nhom.xebus.service.NhatKyService;
import com.nhom.xebus.service.XeBuytService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/xe")
public class XeBuytController {

    @Autowired
    private XeBuytService xeBuytService;

    @Autowired
    private NhatKyService nhatKyService;

    // =========================
    // DANH SÁCH
    // =========================
    @GetMapping
    public String danhSach(Model model) {

        model.addAttribute(
                "danhSach",
                xeBuytService.layTatCa()
        );

        return "xe/danh-sach";
    }

    // =========================
    // FORM THÊM (ĐÃ SỬA)
    // =========================
    @GetMapping("/them")
    public String formThem(Model model) {

        XeBuyt xe = new XeBuyt();

        // TỰ ĐỘNG SINH MÃ XE
        xe.setMaXe(xeBuytService.sinhMaXe());

        model.addAttribute("xe", xe);

        return "xe/form";
    }

    // =========================
    // FORM SỬA
    // =========================
    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        XeBuyt xe = xeBuytService.timTheoMa(ma);

        if (xe == null) {
            return "redirect:/xe";
        }

        model.addAttribute("xe", xe);

        return "xe/form";
    }

    // =========================
    // LƯU
    // =========================
    @PostMapping("/luu")
    public String luu(
            @ModelAttribute("xe") XeBuyt xe,
            Model model
    ) {

        System.out.println("===== BAT DAU LUU XE =====");
        System.out.println("MaXe: " + xe.getMaXe());
        System.out.println("BienSo: " + xe.getBienSo());
        System.out.println("SucChua: " + xe.getSucChua());
        System.out.println("LoaiXe: " + xe.getLoaiXe());
        System.out.println("TrangThai: " + xe.getTrangThai());
        System.out.println("==========================");

        try {
            xeBuytService.luu(xe);

            nhatKyService.ghiLog(
                    "Quản lý xe",
                    "Xe",
                    xe.getMaXe(),
                    "Thêm hoặc cập nhật xe"
            );

            System.out.println("===== LUU XE THANH CONG =====");

        } catch (Exception e) {
            System.out.println("===== LOI KHI LUU XE =====");
            e.printStackTrace();
        }

        model.addAttribute(
                "danhSach",
                xeBuytService.layTatCa()
        );

        return "xe/danh-sach";
    }

    // =========================
    // XOÁ
    // =========================
    @GetMapping("/xoa/{ma}")
    public String xoa(
            @PathVariable String ma,
            Model model
    ) {

        try {
            xeBuytService.xoa(ma);

            nhatKyService.ghiLog(
                    "Quản lý xe",
                    "Xe",
                    ma,
                    "Xoá xe"
            );

            System.out.println("===== XOA XE THANH CONG =====");

        } catch (Exception e) {
            System.out.println("===== LOI KHI XOA XE =====");
            e.printStackTrace();
        }

        model.addAttribute(
                "danhSach",
                xeBuytService.layTatCa()
        );

        return "xe/danh-sach";
    }

}