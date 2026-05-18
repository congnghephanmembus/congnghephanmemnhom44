package com.nhom.xebus.controller;

import com.nhom.xebus.entity.NhanVien;
import com.nhom.xebus.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("danhSach", nhanVienService.layTatCa());
        return "nhanvien/danh-sach";
    }

    @GetMapping("/them")
    public String formThem(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        return "nhanvien/form";
    }

    @PostMapping("/luu")
    public String luu(@ModelAttribute("nhanVien") NhanVien nhanVien) {
        nhanVienService.luu(nhanVien);
        return "redirect:/nhanvien";
    }

    @GetMapping("/sua/{ma}")
    public String formSua(@PathVariable String ma, Model model) {
        NhanVien nhanVien = nhanVienService.timTheoMa(ma);
        model.addAttribute("nhanVien", nhanVien);
        return "nhanvien/form";
    }

    @GetMapping("/xoa/{ma}")
    public String xoa(@PathVariable String ma) {
        nhanVienService.xoa(ma);
        return "redirect:/nhanvien";
    }
}