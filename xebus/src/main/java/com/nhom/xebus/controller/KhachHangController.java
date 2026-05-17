package com.nhom.xebus.controller;

import com.nhom.xebus.entity.KhachHang;
import com.nhom.xebus.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/khach-hang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("danhSach", khachHangService.layTatCa());
        return "khach-hang/danh-sach";
    }

    @GetMapping("/them")
    public String formThem(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "khach-hang/form";
    }

    @PostMapping("/luu")
    public String luu(@ModelAttribute("khachHang") KhachHang khachHang) {
        khachHangService.luu(khachHang);
        return "redirect:/khach-hang";
    }

    @GetMapping("/sua/{ma}")
    public String formSua(@PathVariable String ma, Model model) {
        KhachHang khachHang = khachHangService.timTheoMa(ma);
        model.addAttribute("khachHang", khachHang);
        return "khach-hang/form";
    }

    @GetMapping("/xoa/{ma}")
    public String xoa(@PathVariable String ma) {
        khachHangService.xoa(ma);
        return "redirect:/khach-hang";
    }
}