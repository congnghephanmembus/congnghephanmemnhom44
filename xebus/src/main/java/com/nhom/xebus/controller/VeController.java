package com.nhom.xebus.controller;

import com.nhom.xebus.entity.Ve;
import com.nhom.xebus.service.VeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ve")
public class VeController {

    @Autowired
    private VeService veService;

    @GetMapping
    public String danhSach(Model model) {
        model.addAttribute("danhSach", veService.layTatCa());
        return "ve/danh-sach";
    }

    @GetMapping("/them")
    public String formThem(Model model) {
        model.addAttribute("ve", new Ve());
        return "ve/form";
    }

    @PostMapping("/luu")
    public String luu(@ModelAttribute("ve") Ve ve) {
        veService.luu(ve);
        return "redirect:/ve";
    }

    @GetMapping("/sua/{ma}")
    public String formSua(@PathVariable String ma, Model model) {
        Ve ve = veService.timTheoMa(ma);
        model.addAttribute("ve", ve);
        return "ve/form";
    }

    @GetMapping("/xoa/{ma}")
    public String xoa(@PathVariable String ma) {
        veService.xoa(ma);
        return "redirect:/ve";
    }
}