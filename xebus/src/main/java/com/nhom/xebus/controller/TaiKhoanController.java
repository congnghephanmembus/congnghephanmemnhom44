package com.nhom.xebus.controller;

import com.nhom.xebus.entity.TaiKhoan;
import com.nhom.xebus.service.NhanVienService;
import com.nhom.xebus.service.NhatKyService;
import com.nhom.xebus.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tai-khoan")
public class TaiKhoanController {

    @Autowired
    private TaiKhoanService taiKhoanService;

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
                taiKhoanService.layTatCa()
        );

        return "tai-khoan/danh-sach";
    }

    // =========================
    // FORM THÊM (ĐÃ SỬA)
    // =========================
    @GetMapping("/them")
    public String formThem(Model model) {

        TaiKhoan taiKhoan = new TaiKhoan();

        // TỰ ĐỘNG SINH MÃ TÀI KHOẢN
        taiKhoan.setMaTaiKhoan(taiKhoanService.sinhMaTaiKhoan());
        taiKhoan.setTrangThai("Hoạt động");

        model.addAttribute("taiKhoan", taiKhoan);

        // DANH SÁCH NHÂN VIÊN
        model.addAttribute(
                "danhSachNhanVien",
                nhanVienService.layTatCa()
        );

        return "tai-khoan/form";
    }

    // =========================
    // FORM SỬA
    // =========================
    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        TaiKhoan taiKhoan = taiKhoanService.timTheoMa(ma);

        if (taiKhoan == null) {
            return "redirect:/tai-khoan";
        }

        model.addAttribute("taiKhoan", taiKhoan);

        model.addAttribute(
                "danhSachNhanVien",
                nhanVienService.layTatCa()
        );

        return "tai-khoan/form";
    }

    // =========================
    // LƯU
    // =========================
    @PostMapping("/luu")
public String luu(
        @ModelAttribute("taiKhoan") TaiKhoan taiKhoan,
        Model model
) {

    System.out.println("===== BAT DAU LUU TAI KHOAN =====");
    System.out.println("MaTaiKhoan: " + taiKhoan.getMaTaiKhoan());
    System.out.println("TenDangNhap: " + taiKhoan.getTenDangNhap());
    System.out.println("VaiTro: " + taiKhoan.getVaiTro());
    System.out.println("MaNV: " + taiKhoan.getMaNV());
    System.out.println("TrangThai: " + taiKhoan.getTrangThai());
    System.out.println("=================================");

    try {
        // Nếu đang sửa (có mã tài khoản), tìm bản ghi cũ để giữ nguyên ngày tạo
        if (taiKhoan.getMaTaiKhoan() != null && !taiKhoan.getMaTaiKhoan().isEmpty()) {
            TaiKhoan existing = taiKhoanService.timTheoMa(taiKhoan.getMaTaiKhoan());
            if (existing != null) {
                // Giữ nguyên ngày tạo
                taiKhoan.setNgayTao(existing.getNgayTao());
            }
        }

        taiKhoanService.luu(taiKhoan);

        nhatKyService.ghiLog(
                "Quản lý tài khoản",
                "Tài khoản",
                taiKhoan.getMaTaiKhoan(),
                "Thêm hoặc cập nhật tài khoản: " + taiKhoan.getTenDangNhap()
        );

        System.out.println("===== LUU TAI KHOAN THANH CONG =====");

    } catch (Exception e) {
        System.out.println("===== LOI KHI LUU TAI KHOAN =====");
        e.printStackTrace();
    }

    model.addAttribute(
            "danhSach",
            taiKhoanService.layTatCa()
    );

    return "tai-khoan/danh-sach";
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
            // Lấy tên đăng nhập trước khi xóa
            TaiKhoan taiKhoan = taiKhoanService.timTheoMa(ma);
            String tenDangNhap = taiKhoan != null ? taiKhoan.getTenDangNhap() : ma;

            taiKhoanService.xoa(ma);

            nhatKyService.ghiLog(
                    "Quản lý tài khoản",
                    "Tài khoản",
                    ma,
                    "Xoá tài khoản: " + tenDangNhap
            );

            System.out.println("===== XOA TAI KHOAN THANH CONG =====");

        } catch (Exception e) {
            System.out.println("===== LOI KHI XOA TAI KHOAN =====");
            e.printStackTrace();
        }

        model.addAttribute(
                "danhSach",
                taiKhoanService.layTatCa()
        );

        return "tai-khoan/danh-sach";
    }

}