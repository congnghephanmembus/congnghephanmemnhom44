package com.nhom.xebus.controller;

import com.nhom.xebus.entity.NhanVien;
import com.nhom.xebus.service.NhanVienService;
import com.nhom.xebus.service.NhatKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private NhatKyService nhatKyService;

    // =========================
    // DANH SÁCH
    // =========================
    @GetMapping
    public String danhSach(Model model) {

        model.addAttribute(
                "danhSach",
                nhanVienService.layTatCa()
        );

        return "nhan-vien/danh-sach";
    }

    // =========================
    // FORM THÊM (ĐÃ SỬA)
    // =========================
    @GetMapping("/them")
    public String formThem(Model model) {

        NhanVien nhanVien = new NhanVien();

        // TỰ ĐỘNG SINH MÃ NHÂN VIÊN
        nhanVien.setMaNV(nhanVienService.sinhMaNV());

        model.addAttribute("nhanVien", nhanVien);

        return "nhan-vien/form";
    }

    // =========================
    // FORM SỬA
    // =========================
    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        NhanVien nhanVien = nhanVienService.timTheoMa(ma);

        if (nhanVien == null) {
            return "redirect:/nhan-vien";
        }

        model.addAttribute("nhanVien", nhanVien);

        return "nhan-vien/form";
    }

    // =========================
    // LƯU
    // =========================
    @PostMapping("/luu")
    public String luu(
            @ModelAttribute("nhanVien") NhanVien nhanVien,
            Model model
    ) {

        System.out.println("===== BAT DAU LUU NHAN VIEN =====");
        System.out.println("MaNV: " + nhanVien.getMaNV());
        System.out.println("HoTen: " + nhanVien.getHoTen());
        System.out.println("ChucVu: " + nhanVien.getChucVu());
        System.out.println("================================");

        try {
            nhanVienService.luu(nhanVien);

            nhatKyService.ghiLog(
                    "Quản lý nhân viên",
                    "Nhân viên",
                    nhanVien.getMaNV(),
                    "Thêm hoặc cập nhật nhân viên"
            );

            System.out.println("===== LUU NHAN VIEN THANH CONG =====");

        } catch (Exception e) {
            System.out.println("===== LOI KHI LUU NHAN VIEN =====");
            e.printStackTrace();
        }

        model.addAttribute(
                "danhSach",
                nhanVienService.layTatCa()
        );

        return "nhan-vien/danh-sach";
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
            nhanVienService.xoa(ma);

            nhatKyService.ghiLog(
                    "Quản lý nhân viên",
                    "Nhân viên",
                    ma,
                    "Xoá nhân viên"
            );

            System.out.println("===== XOA NHAN VIEN THANH CONG =====");

        } catch (Exception e) {
            System.out.println("===== LOI KHI XOA NHAN VIEN =====");
            e.printStackTrace();
        }

        model.addAttribute(
                "danhSach",
                nhanVienService.layTatCa()
        );

        return "nhan-vien/danh-sach";
    }

}