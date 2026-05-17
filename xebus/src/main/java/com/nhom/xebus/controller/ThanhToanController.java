package com.nhom.xebus.controller;

import com.nhom.xebus.entity.ThanhToan;
import com.nhom.xebus.service.ThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/thanh-toan")
public class ThanhToanController {

    @Autowired
    private ThanhToanService thanhToanService;

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("danhSach", thanhToanService.layTatCa());
        return "thanh-toan/danh-sach";
    }

    @GetMapping("/them")
    public String formThem(Model model) {
        model.addAttribute("thanhToan", new ThanhToan());
        return "thanh-toan/form";
    }

    @PostMapping("/luu")
    public String luu(@ModelAttribute("thanhToan") ThanhToan thanhToan) {
        thanhToanService.luu(thanhToan);
        return "redirect:/thanh-toan";
    }

    @GetMapping("/sua/{ma}")
    public String formSua(@PathVariable String ma, Model model) {
        ThanhToan thanhToan = thanhToanService.timTheoMa(ma);
        model.addAttribute("thanhToan", thanhToan);
        return "thanh-toan/form";
    }

    @GetMapping("/xoa/{ma}")
    public String xoa(@PathVariable String ma) {
        thanhToanService.xoa(ma);
        return "redirect:/thanh-toan";
    }
}