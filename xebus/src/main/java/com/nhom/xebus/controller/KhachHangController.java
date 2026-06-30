package com.nhom.xebus.controller;

import com.nhom.xebus.entity.KhachHang;
import com.nhom.xebus.service.KhachHangService;
import com.nhom.xebus.service.NhatKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/khach-hang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private NhatKyService nhatKyService;

    // =========================
    // DANH SÁCH
    // =========================
    @GetMapping
    public String danhSach(Model model) {

        model.addAttribute(
                "danhSach",
                khachHangService.layTatCa()
        );

        return "khach-hang/danh-sach";
    }

    // =========================
    // FORM THÊM (ĐÃ SỬA)
    // =========================
    @GetMapping("/them")
    public String formThem(Model model) {

        KhachHang khachHang = new KhachHang();

        // TỰ ĐỘNG SINH MÃ KHÁCH HÀNG
        khachHang.setMaKH(khachHangService.sinhMaKH());

        model.addAttribute("khachHang", khachHang);

        return "khach-hang/form";
    }

    // =========================
    // FORM SỬA
    // =========================
    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        KhachHang khachHang = khachHangService.timTheoMa(ma);

        if (khachHang == null) {
            return "redirect:/khach-hang";
        }

        model.addAttribute("khachHang", khachHang);

        return "khach-hang/form";
    }

    // =========================
    // LƯU
    // =========================
    @PostMapping("/luu")
    public String luu(
            @ModelAttribute("khachHang") KhachHang khachHang,
            Model model
    ) {

        try {
            khachHangService.luu(khachHang);

            nhatKyService.ghiLog(
                    "Quản lý khách hàng",
                    "Khách hàng",
                    khachHang.getMaKH(),
                    "Thêm hoặc cập nhật khách hàng"
            );

            System.out.println("===== LUU KHACH HANG THANH CONG =====");

        } catch (Exception e) {
            System.out.println("===== LOI KHI LUU KHACH HANG =====");
            e.printStackTrace();
        }

        model.addAttribute(
                "danhSach",
                khachHangService.layTatCa()
        );

        return "khach-hang/danh-sach";
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
            khachHangService.xoa(ma);

            nhatKyService.ghiLog(
                    "Quản lý khách hàng",
                    "Khách hàng",
                    ma,
                    "Xoá khách hàng"
            );

            System.out.println("===== XOA KHACH HANG THANH CONG =====");

        } catch (Exception e) {
            System.out.println("===== LOI KHI XOA KHACH HANG =====");
            e.printStackTrace();
        }

        model.addAttribute(
                "danhSach",
                khachHangService.layTatCa()
        );

        return "khach-hang/danh-sach";
    }
}