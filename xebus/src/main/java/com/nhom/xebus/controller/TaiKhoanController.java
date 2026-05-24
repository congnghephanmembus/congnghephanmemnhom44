package com.nhom.xebus.controller;

import com.nhom.xebus.entity.TaiKhoan;
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
    private NhatKyService nhatKyService;

    @GetMapping
    public String danhSach(Model model) {

        model.addAttribute(
                "danhSach",
                taiKhoanService.layTatCa()
        );

        return "tai-khoan/danh-sach";
    }

    @GetMapping("/them")
    public String formThem(Model model) {

        model.addAttribute(
                "taiKhoan",
                new TaiKhoan()
        );

        return "tai-khoan/form";
    }

    @PostMapping("/luu")
    public String luu(
            @ModelAttribute("taiKhoan")
            TaiKhoan taiKhoan
    ) {

        taiKhoanService.luu(taiKhoan);

        nhatKyService.ghiLog(
                "Quản lý tài khoản",
                "Tài khoản",
                taiKhoan.getTenDangNhap(),
                "Thêm hoặc cập nhật tài khoản"
        );

        return "redirect:/tai-khoan";
    }

    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        TaiKhoan taiKhoan =
                taiKhoanService.timTheoMa(ma);

        if (taiKhoan == null) {

            return "redirect:/tai-khoan";
        }

        model.addAttribute(
                "taiKhoan",
                taiKhoan
        );

        return "tai-khoan/form";
    }

    @GetMapping("/xoa/{ma}")
    public String xoa(@PathVariable String ma) {

        taiKhoanService.xoa(ma);

        nhatKyService.ghiLog(
                "Quản lý tài khoản",
                "Tài khoản",
                ma,
                "Xoá tài khoản"
        );

        return "redirect:/tai-khoan";
    }
}