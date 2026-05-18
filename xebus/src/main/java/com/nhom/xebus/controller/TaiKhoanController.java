package com.nhom.xebus.controller;

import com.nhom.xebus.entity.TaiKhoan;
import com.nhom.xebus.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/taikhoan")
public class TaiKhoanController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("danhSach", taiKhoanService.layTatCa());
        return "taikhoan/danh-sach";
    }

    @GetMapping("/them")
    public String formThem(Model model) {
        model.addAttribute("taiKhoan", new TaiKhoan());
        return "taikhoan/form";
    }

    @PostMapping("/luu")
    public String luu(@ModelAttribute("taiKhoan") TaiKhoan taiKhoan) {
        taiKhoanService.luu(taiKhoan);
        return "redirect:/taikhoan";
    }

    @GetMapping("/sua/{ma}")
    public String formSua(@PathVariable String ma, Model model) {
        TaiKhoan taiKhoan = taiKhoanService.timTheoMa(ma);
        model.addAttribute("taiKhoan", taiKhoan);
        return "taikhoan/form";
    }

    @GetMapping("/xoa/{ma}")
    public String xoa(@PathVariable String ma) {
        taiKhoanService.xoa(ma);
        return "redirect:/taikhoan";
    }
}