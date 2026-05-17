package com.nhom.xebus.controller;

import com.nhom.xebus.entity.TuyenXe;
import com.nhom.xebus.service.TuyenXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tuyen")
public class TuyenXeController {

    @Autowired
    private TuyenXeService tuyenXeService;

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("danhSach", tuyenXeService.layTatCa());
        return "tuyen/danh-sach";
    }

    @GetMapping("/them")
    public String formThem(Model model) {
        model.addAttribute("tuyenXe", new TuyenXe());
        return "tuyen/form";
    }

    @PostMapping("/luu")
    public String luu(@ModelAttribute("tuyenXe") TuyenXe tuyenXe) {
        tuyenXeService.luu(tuyenXe);
        return "redirect:/tuyen";
    }

    @GetMapping("/sua/{ma}")
    public String formSua(@PathVariable String ma, Model model) {
        TuyenXe tuyenXe = tuyenXeService.timTheoMa(ma);
        model.addAttribute("tuyenXe", tuyenXe);
        return "tuyen/form";
    }

    @GetMapping("/xoa/{ma}")
    public String xoa(@PathVariable String ma) {
        tuyenXeService.xoa(ma);
        return "redirect:/tuyen";
    }
}