package com.nhom.xebus.controller;

import com.nhom.xebus.entity.ChuyenXe;
import com.nhom.xebus.service.ChuyenXeService;
import com.nhom.xebus.service.LichChayService;
import com.nhom.xebus.service.NhatKyService;
import com.nhom.xebus.service.TuyenXeService;
import com.nhom.xebus.service.XeBuytService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chuyen")
public class ChuyenXeController {

    @Autowired
    private ChuyenXeService chuyenXeService;

    @Autowired
    private NhatKyService nhatKyService;

    @Autowired
    private TuyenXeService tuyenXeService;

    @Autowired
    private XeBuytService xeBuytService;

    @Autowired
    private LichChayService lichChayService;

    // =========================
    // DANH SÁCH
    // =========================
    @GetMapping
    public String danhSach(Model model) {

        model.addAttribute(
                "danhSach",
                chuyenXeService.layTatCa()
        );

        return "chuyen/danh-sach";
    }

    // =========================
    // CHI TIẾT
    // =========================
    @GetMapping("/chi-tiet/{ma}")
    public String chiTiet(
            @PathVariable String ma,
            Model model
    ) {

        ChuyenXe chuyenXe = chuyenXeService.timTheoMa(ma);

        if (chuyenXe == null) {

            model.addAttribute(
                    "danhSach",
                    chuyenXeService.layTatCa()
            );

            return "chuyen/danh-sach";
        }

        model.addAttribute(
                "chuyenXe",
                chuyenXe
        );

        nhatKyService.ghiLog(
                "Quản lý chuyến xe",
                "Chuyến xe",
                ma,
                "Xem chi tiết chuyến xe"
        );

        return "chuyen/chi-tiet";
    }

    // =========================
    // FORM THÊM (ĐÃ SỬA)
    // =========================
    @GetMapping("/them")
    public String formThem(Model model) {

        ChuyenXe chuyenXe = new ChuyenXe();

        // TỰ ĐỘNG SINH MÃ CHUYẾN
        chuyenXe.setMaChuyen(chuyenXeService.sinhMaChuyen());

        model.addAttribute("chuyenXe", chuyenXe);

        model.addAttribute(
                "danhSachTuyen",
                tuyenXeService.layTatCa()
        );

        model.addAttribute(
                "danhSachXe",
                xeBuytService.layTatCa()
        );

        model.addAttribute(
                "danhSachLich",
                lichChayService.layTatCa()
        );

        return "chuyen/form";
    }

    // =========================
    // FORM SỬA
    // =========================
    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        ChuyenXe chuyenXe = chuyenXeService.timTheoMa(ma);

        System.out.println("===== FORM SUA =====");
        System.out.println(chuyenXe);
        System.out.println("====================");

        if (chuyenXe == null) {

            model.addAttribute(
                    "danhSach",
                    chuyenXeService.layTatCa()
            );

            return "chuyen/danh-sach";
        }

        model.addAttribute(
                "chuyenXe",
                chuyenXe
        );

        model.addAttribute(
                "danhSachTuyen",
                tuyenXeService.layTatCa()
        );

        model.addAttribute(
                "danhSachXe",
                xeBuytService.layTatCa()
        );

        model.addAttribute(
                "danhSachLich",
                lichChayService.layTatCa()
        );

        return "chuyen/form";
    }

    // =========================
    // LƯU - POST
    // =========================
    @PostMapping("/luu")
    public String luu(
            @ModelAttribute("chuyenXe") ChuyenXe chuyenXe,
            Model model
    ) {

        System.out.println("===== BAT DAU LUU =====");
        System.out.println(chuyenXe);
        System.out.println("=======================");

        try {

            chuyenXeService.luu(chuyenXe);

            System.out.println("===== LUU THANH CONG =====");

        } catch (Exception e) {

            System.out.println("===== LOI KHI LUU =====");
            e.printStackTrace();
        }

        nhatKyService.ghiLog(
                "Quản lý chuyến xe",
                "Chuyến xe",
                chuyenXe.getMaChuyen(),
                "Thêm hoặc cập nhật chuyến xe"
        );

        model.addAttribute(
                "danhSach",
                chuyenXeService.layTatCa()
        );

        return "chuyen/danh-sach";
    }

    // =========================
    // XOÁ
    // =========================
    @GetMapping("/xoa/{ma}")
    public String xoa(
            @PathVariable String ma,
            Model model
    ) {

        try {

            chuyenXeService.xoa(ma);

            System.out.println("===== XOA THANH CONG =====");

        } catch (Exception e) {

            System.out.println("===== LOI KHI XOA =====");
            e.printStackTrace();
        }

        nhatKyService.ghiLog(
                "Quản lý chuyến xe",
                "Chuyến xe",
                ma,
                "Xoá chuyến xe"
        );

        model.addAttribute(
                "danhSach",
                chuyenXeService.layTatCa()
        );

        return "chuyen/danh-sach";
    }

}