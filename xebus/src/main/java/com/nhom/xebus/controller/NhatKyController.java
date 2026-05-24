package com.nhom.xebus.controller;

import com.nhom.xebus.repository.NhatKyHeThongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NhatKyController {

    @Autowired
    private NhatKyHeThongRepository nhatKyRepository;

    @GetMapping("/nhat-ky")
    public String nhatKy(Model model) {

        model.addAttribute(
                "danhSach",
                nhatKyRepository.findAll()
        );

        return "nhat-ky/index";
    }
}