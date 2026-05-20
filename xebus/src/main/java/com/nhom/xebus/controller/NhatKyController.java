package com.nhom.xebus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NhatKyController {

    @GetMapping("/nhat-ky")
    public String nhatKy() {
        return "nhat-ky/index";
    }

}