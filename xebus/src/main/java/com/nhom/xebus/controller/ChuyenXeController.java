package com.nhom.xebus.controller;

import com.nhom.xebus.entity.ChuyenXe;
import com.nhom.xebus.service.ChuyenXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chuyen")
public class ChuyenXeController {

    @Autowired
    private ChuyenXeService chuyenXeService;

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("danhSach", chuyenXeService.layTatCa());
        return "chuyen/danh-sach";
    }

    @GetMapping("/them")
    public String formThem(Model model) {
        model.addAttribute("chuyenXe", new ChuyenXe());
        return "chuyen/form";
    }

    @PostMapping("/luu")
    public String luu(@ModelAttribute("chuyenXe") ChuyenXe chuyenXe) {
        chuyenXeService.luu(chuyenXe);
        return "redirect:/chuyen";
    }

    @GetMapping("/sua/{ma}")
    public String formSua(@PathVariable String ma, Model model) {
        ChuyenXe chuyenXe = chuyenXeService.timTheoMa(ma);
        model.addAttribute("chuyenXe", chuyenXe);
        return "chuyen/form";
    }

    @GetMapping("/xoa/{ma}")
    public String xoa(@PathVariable String ma) {
        chuyenXeService.xoa(ma);
        return "redirect:/chuyen";
    }
}