package com.nhom.xebus.controller;

import com.nhom.xebus.dto.PagingResponse;
import com.nhom.xebus.entity.ChiTietTuyen;
import com.nhom.xebus.entity.TuyenXe;
import com.nhom.xebus.entity.Tram;
import com.nhom.xebus.repository.ChiTietTuyenRepository;
import com.nhom.xebus.service.NhatKyService;
import com.nhom.xebus.service.TramService;
import com.nhom.xebus.service.TuyenXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tuyen")
public class TuyenXeController {

    @Autowired
    private TuyenXeService tuyenXeService;

    @Autowired
    private NhatKyService nhatKyService;

    @Autowired
    private TramService tramService;

    @Autowired
    private ChiTietTuyenRepository chiTietTuyenRepository;

    // =========================
    // DANH SÁCH TUYẾN (CÓ PHÂN TRANG)
    // =========================
    @GetMapping
    public String danhSach(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model
    ) {

        PagingResponse<TuyenXe> paging = tuyenXeService.layPhanTrang(page, size);

        model.addAttribute("danhSach", paging.getContent());
        model.addAttribute("currentPage", paging.getCurrentPage());
        model.addAttribute("pageSize", paging.getPageSize());
        model.addAttribute("totalElements", paging.getTotalElements());
        model.addAttribute("totalPages", paging.getTotalPages());
        model.addAttribute("hasNext", paging.isHasNext());
        model.addAttribute("hasPrevious", paging.isHasPrevious());

        return "tuyen/danh-sach";
    }

    // =========================
    // FORM THÊM
    // =========================
    @GetMapping("/them")
    public String formThem(Model model) {

        TuyenXe tuyenXe = new TuyenXe();

        // Tự động sinh mã tuyến
        tuyenXe.setMaTuyen(tuyenXeService.sinhMaTuyen());

        model.addAttribute("tuyenXe", tuyenXe);

        // THÊM DANH SÁCH TRẠM VÀO MODEL
        model.addAttribute("danhSachTram", tramService.layTatCa());

        return "tuyen/form";
    }

    // =========================
    // LƯU
    // =========================
    @PostMapping("/luu")
    public String luu(
            @ModelAttribute("tuyenXe") TuyenXe tuyenXe
    ) {

        tuyenXeService.luu(tuyenXe);

        nhatKyService.ghiLog(
                "Quản lý tuyến xe",
                "Tuyến xe",
                tuyenXe.getMaTuyen(),
                "Thêm hoặc cập nhật tuyến xe"
        );

        return "redirect:/tuyen";
    }

    // =========================
    // FORM SỬA
    // =========================
    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        TuyenXe tuyenXe = tuyenXeService.timTheoMa(ma);

        if (tuyenXe == null) {
            return "redirect:/tuyen";
        }

        model.addAttribute("tuyenXe", tuyenXe);

        // THÊM DANH SÁCH TRẠM VÀO MODEL
        model.addAttribute("danhSachTram", tramService.layTatCa());

        return "tuyen/form";
    }

    // =========================
    // XOÁ
    // =========================
    @GetMapping("/xoa/{ma}")
    public String xoa(
            @PathVariable String ma
    ) {

        tuyenXeService.xoa(ma);

        nhatKyService.ghiLog(
                "Quản lý tuyến xe",
                "Tuyến xe",
                ma,
                "Xoá tuyến xe"
        );

        return "redirect:/tuyen";
    }

// =========================
    // CHI TIẾT TUYẾN
    // =========================
    @GetMapping("/chi-tiet/{ma}")
    public String chiTietTuyen(
            @PathVariable String ma,
            Model model
    ) {
        // Lấy thông tin tuyến xe
        TuyenXe tuyenXe = tuyenXeService.timTheoMa(ma);
        if (tuyenXe == null) {
            return "redirect:/tuyen";
        }
        model.addAttribute("tuyenXe", tuyenXe);

        // Lấy danh sách trạm của tuyến (chi tiết tuyến)
        List<ChiTietTuyen> dsChiTiet = chiTietTuyenRepository.findByMaTuyenOrderByThuTuTram(ma);

        // Chuyển đổi sang danh sách Trams với thông tin đầy đủ
        List<Tram> dsTram = dsChiTiet.stream()
                .map(ct -> tramService.timTheoMa(ct.getMaTram()))
                .collect(Collectors.toList());

        model.addAttribute("dsTram", dsTram);

        return "tuyen/chi-tiet";
    }
}
