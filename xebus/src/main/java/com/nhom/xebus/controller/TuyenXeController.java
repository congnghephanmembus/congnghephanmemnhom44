package com.nhom.xebus.controller;

import com.nhom.xebus.entity.ChiTietTuyen;
import com.nhom.xebus.entity.Tram;
import com.nhom.xebus.entity.TuyenXe;
import com.nhom.xebus.repository.ChiTietTuyenRepository;
import com.nhom.xebus.repository.TramRepository;
import com.nhom.xebus.service.TuyenXeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tuyen")
public class TuyenXeController {

    @Autowired
    private TuyenXeService tuyenXeService;

    @Autowired
    private ChiTietTuyenRepository chiTietTuyenRepository;

    @Autowired
    private TramRepository tramRepository;

    @GetMapping
    public String danhSach(Model model) {

        model.addAttribute(
                "danhSach",
                tuyenXeService.layTatCa()
        );

        return "tuyen/danh-sach";
    }

    @GetMapping("/them")
    public String formThem(Model model) {

        model.addAttribute(
                "tuyenXe",
                new TuyenXe()
        );

        return "tuyen/form";
    }

    @PostMapping("/luu")
    public String luu(
            @ModelAttribute("tuyenXe")
            TuyenXe tuyenXe
    ) {

        tuyenXeService.luu(tuyenXe);

        return "redirect:/tuyen";
    }

    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        TuyenXe tuyenXe =
                tuyenXeService.timTheoMa(ma);

        model.addAttribute(
                "tuyenXe",
                tuyenXe
        );

        return "tuyen/form";
    }

    @GetMapping("/xoa/{ma}")
    public String xoa(
            @PathVariable String ma
    ) {

        tuyenXeService.xoa(ma);

        return "redirect:/tuyen";
    }

    @GetMapping("/chi-tiet/{ma}")
    public String chiTietTuyen(
            @PathVariable String ma,
            Model model
    ) {

        List<ChiTietTuyen> dsChiTiet =
                chiTietTuyenRepository
                        .findByMaTuyenOrderByThuTuTram(ma);

        List<Tram> dsTram =
                new ArrayList<>();

        for (ChiTietTuyen ct : dsChiTiet) {

            Tram tram =
                    tramRepository
                            .findById(
                                    ct.getMaTram()
                            )
                            .orElse(null);

            if (tram != null) {

                dsTram.add(tram);

            }
        }

        model.addAttribute(
                "dsTram",
                dsTram
        );

        return "tuyen/chi-tiet";
    }
}