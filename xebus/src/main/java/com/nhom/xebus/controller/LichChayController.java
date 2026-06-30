package com.nhom.xebus.controller;

import com.nhom.xebus.entity.LichChay;
import com.nhom.xebus.entity.TuyenXe;
import com.nhom.xebus.repository.LichChayRepository;
import com.nhom.xebus.repository.TuyenXeRepository;
import com.nhom.xebus.service.LichChayService;
import com.nhom.xebus.service.NhatKyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lich")
public class LichChayController {

    @Autowired
    private LichChayRepository lichChayRepository;

    @Autowired
    private TuyenXeRepository tuyenXeRepository;

    @Autowired
    private LichChayService lichChayService;

    @Autowired
    private NhatKyService nhatKyService;

    // =========================
    // TẤT CẢ LỊCH CHẠY
    // =========================
    @GetMapping
    public String tatCaLich(Model model) {

        List<LichChay> danhSach =
                lichChayRepository.findAll();

        model.addAttribute(
                "danhSach",
                danhSach
        );

        return "lich/danh-sach";
    }

    // =========================
    // LỊCH CHẠY THEO TUYẾN
    // =========================
    @GetMapping("/tuyen/{ma}")
    public String xemLichTheoTuyen(
            @PathVariable("ma") String ma,
            Model model
    ) {

        List<LichChay> danhSach =
                lichChayRepository.findByTuyenXe_MaTuyen(ma);

        model.addAttribute(
                "danhSach",
                danhSach
        );

        model.addAttribute(
                "maTuyen",
                ma
        );

        return "lich/danh-sach";
    }

    // =========================
    // CHI TIẾT
    // =========================
    @GetMapping("/chi-tiet/{ma}")
    public String chiTiet(
            @PathVariable("ma") String ma,
            Model model
    ) {

        LichChay lich =
                lichChayRepository
                        .findById(ma)
                        .orElse(null);

        if (lich == null) {

            return "redirect:/lich";
        }

        model.addAttribute(
                "lich",
                lich
        );

        return "lich/chi-tiet";
    }

    // =========================
    // FORM THÊM
    // =========================
    @GetMapping("/them")
    public String formThem(
            @RequestParam("maTuyen") String maTuyen,
            Model model
    ) {

        TuyenXe tuyenXe = tuyenXeRepository.findById(maTuyen).orElse(null);

        if (tuyenXe == null) {
            return "redirect:/tuyen";
        }

        LichChay lichChay = new LichChay();
        lichChay.setTuyenXe(tuyenXe);

        // Tự động sinh mã lịch
        lichChay.setMaLich(lichChayService.sinhMaLich());

        model.addAttribute("lichChay", lichChay);

        return "lich/form";
    }

    // =========================
    // FORM SỬA
    // =========================
    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable("ma") String ma,
            Model model
    ) {

        LichChay lichChay = lichChayRepository.findById(ma).orElse(null);

        if (lichChay == null) {
            return "redirect:/lich";
        }

        model.addAttribute("lichChay", lichChay);

        return "lich/form";
    }

    // =========================
    // LƯU
    // =========================
    @PostMapping("/luu")
    public String luu(
            @ModelAttribute("lichChay") LichChay lichChay,
            Model model
    ) {

        try {

            lichChayRepository.save(lichChay);

            nhatKyService.ghiLog(
                    "Quản lý lịch chạy",
                    "Lịch chạy",
                    lichChay.getMaLich(),
                    "Thêm hoặc cập nhật lịch chạy"
            );

            System.out.println("===== LUU LICH THANH CONG =====");

        } catch (Exception e) {

            System.out.println("===== LOI KHI LUU LICH =====");
            e.printStackTrace();
        }

        // Trả về danh sách lịch theo tuyến
        List<LichChay> danhSach =
                lichChayRepository.findByTuyenXe_MaTuyen(lichChay.getTuyenXe().getMaTuyen());

        model.addAttribute("danhSach", danhSach);
        model.addAttribute("maTuyen", lichChay.getTuyenXe().getMaTuyen());

        return "lich/danh-sach";
    }

    // =========================
    // XOÁ
    // =========================
    @GetMapping("/xoa/{ma}")
    public String xoa(
            @PathVariable("ma") String ma,
            Model model
    ) {

        LichChay lichChay = lichChayRepository.findById(ma).orElse(null);

        if (lichChay == null) {
            return "redirect:/lich";
        }

        String maTuyen = lichChay.getTuyenXe().getMaTuyen();

        try {

            lichChayRepository.deleteById(ma);

            nhatKyService.ghiLog(
                    "Quản lý lịch chạy",
                    "Lịch chạy",
                    ma,
                    "Xoá lịch chạy"
            );

            System.out.println("===== XOA LICH THANH CONG =====");

        } catch (Exception e) {

            System.out.println("===== LOI KHI XOA LICH =====");
            e.printStackTrace();
        }

        // Trả về danh sách lịch theo tuyến
        List<LichChay> danhSach =
                lichChayRepository.findByTuyenXe_MaTuyen(maTuyen);

        model.addAttribute("danhSach", danhSach);
        model.addAttribute("maTuyen", maTuyen);

        return "lich/danh-sach";
    }
}