package com.nhom.xebus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaoCaoController {

    @GetMapping("/bao-cao")
    public String baoCao() {
        return "bao-cao/index";
    }

}