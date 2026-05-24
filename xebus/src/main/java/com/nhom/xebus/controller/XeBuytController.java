package com.nhom.xebus.controller;

import com.nhom.xebus.entity.XeBuyt;
import com.nhom.xebus.service.NhatKyService;
import com.nhom.xebus.service.XeBuytService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/xe")
public class XeBuytController {

    private final XeBuytService xeBuytService;
    private final NhatKyService nhatKyService;

    public XeBuytController(
            XeBuytService xeBuytService,
            NhatKyService nhatKyService
    ) {

        this.xeBuytService = xeBuytService;
        this.nhatKyService = nhatKyService;
    }

    @GetMapping
    public String danhSach(Model model) {

        model.addAttribute(
                "danhSach",
                xeBuytService.layTatCa()
        );

        return "xe/danh-sach";
    }

    @GetMapping("/them")
    public String formThem(Model model) {

        model.addAttribute(
                "xe",
                new XeBuyt()
        );

        return "xe/form";
    }

    @PostMapping("/luu")
    public String luu(@ModelAttribute("xe") XeBuyt xe) {

        xeBuytService.luu(xe);

        nhatKyService.ghiLog(
                "Quản lý xe buýt",
                "Xe buýt",
                xe.getMaXe(),
                "Thêm hoặc cập nhật xe buýt"
        );

        return "redirect:/xe";
    }

    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        XeBuyt xe =
                xeBuytService.timTheoMa(ma);

        if (xe == null) {

            return "redirect:/xe";
        }

        model.addAttribute(
                "xe",
                xe
        );

        return "xe/form";
    }

    @GetMapping("/xoa/{ma}")
    public String xoa(@PathVariable String ma) {

        xeBuytService.xoa(ma);

        nhatKyService.ghiLog(
                "Quản lý xe buýt",
                "Xe buýt",
                ma,
                "Xoá xe buýt"
        );

        return "redirect:/xe";
    }
}