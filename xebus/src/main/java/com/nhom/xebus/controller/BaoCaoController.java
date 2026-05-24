package com.nhom.xebus.controller;

import com.nhom.xebus.repository.ChuyenXeRepository;
import com.nhom.xebus.repository.ThanhToanRepository;
import com.nhom.xebus.repository.VeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaoCaoController {

    @Autowired
    private VeRepository veRepository;

    @Autowired
    private ThanhToanRepository thanhToanRepository;

    @Autowired
    private ChuyenXeRepository chuyenXeRepository;

    @GetMapping("/bao-cao")
    public String baoCao(Model model) {

        long tongVe =
                veRepository.count();

        Double doanhThu =
                thanhToanRepository.tongDoanhThu();

        long tongChuyen =
                chuyenXeRepository.count();

        model.addAttribute(
                "tongVe",
                tongVe
        );

        model.addAttribute(
                "doanhThu",
                doanhThu == null ? 0 : doanhThu
        );

        model.addAttribute(
                "tongChuyen",
                tongChuyen
        );

        return "bao-cao/index";
    }
}