package com.nhom.xebus.controller;

import com.nhom.xebus.entity.LoaiVe;
import com.nhom.xebus.entity.TuyenXe;
import com.nhom.xebus.entity.Ve;
import com.nhom.xebus.service.KhachHangService;
import com.nhom.xebus.service.LoaiVeService;
import com.nhom.xebus.service.NhatKyService;
import com.nhom.xebus.service.TuyenXeService;
import com.nhom.xebus.service.VeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ve")
public class VeController {

    @Autowired
    private VeService veService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private TuyenXeService tuyenXeService;

    @Autowired
    private LoaiVeService loaiVeService;

    @Autowired
    private NhatKyService nhatKyService;

    // =========================
    // DANH SÁCH
    // =========================
    @GetMapping
    public String danhSach(Model model) {

        model.addAttribute(
                "danhSach",
                veService.layTatCa()
        );

        return "ve/danh-sach";
    }

    // =========================
    // FORM THÊM
    // =========================
    @GetMapping("/them")
    public String formThem(Model model) {

        Ve ve = new Ve();
        ve.setMaVe(veService.sinhMaVe());
        ve.setNgayMua(LocalDateTime.now());

        model.addAttribute("ve", ve);
        model.addAttribute("danhSachKhachHang", khachHangService.layTatCa());
        model.addAttribute("danhSachTuyen", tuyenXeService.layTatCa());
        model.addAttribute("danhSachLoaiVe", loaiVeService.layTatCa());

        return "ve/form";
    }

    // =========================
    // FORM SỬA
    // =========================
    @GetMapping("/sua/{ma}")
    public String formSua(
            @PathVariable String ma,
            Model model
    ) {

        Ve ve = veService.timTheoMa(ma);

        if (ve == null) {
            return "redirect:/ve";
        }

        model.addAttribute("ve", ve);
        model.addAttribute("danhSachKhachHang", khachHangService.layTatCa());
        model.addAttribute("danhSachTuyen", tuyenXeService.layTatCa());
        model.addAttribute("danhSachLoaiVe", loaiVeService.layTatCa());

        // Xác định loại khách
        String loaiKhach = "";
        if (ve.getMaLoaiVe() != null) {
            if (ve.getMaLoaiVe().startsWith("VL") && !ve.getMaLoaiVe().equals("VL04")) {
                loaiKhach = "VANG_LAI";
            } else if (ve.getMaLoaiVe().equals("VL04")) {
                loaiKhach = "HSSV";
            } else if (ve.getMaLoaiVe().equals("VT01")) {
                loaiKhach = "VE_TAP";
            }
        }
        model.addAttribute("loaiKhach", loaiKhach);

        // Tên hiển thị loại vé
        String tenLoaiVeDisplay = "";
        if (ve.getMaLoaiVe() != null) {
            var loaiVe = loaiVeService.timTheoMa(ve.getMaLoaiVe());
            if (loaiVe != null) {
                tenLoaiVeDisplay = loaiVe.getMaLoaiVe() + " - " + loaiVe.getTenLoaiVe() + " (Giá: " + loaiVe.getDonGia() + "đ)";
            }
        }
        model.addAttribute("tenLoaiVeDisplay", tenLoaiVeDisplay);

        return "ve/form";
    }

    // =========================
    // API LẤY LOẠI VÉ THEO TUYẾN
    // =========================
    @GetMapping("/lay-loai-ve")
    @ResponseBody
    public Map<String, Object> layLoaiVeTheoTuyen(
            @RequestParam("maTuyen") String maTuyen
    ) {
        Map<String, Object> result = new HashMap<>();

        try {
            TuyenXe tuyen = tuyenXeService.timTheoMa(maTuyen);
            if (tuyen == null) {
                result.put("success", false);
                result.put("message", "Không tìm thấy tuyến");
                return result;
            }

            BigDecimal cuLy = tuyen.getCuLy();
            String maLoaiVe = "";
            String tenLoaiVe = "";
            BigDecimal donGia = BigDecimal.ZERO;

            if (cuLy.compareTo(new BigDecimal("15")) < 0) {
                maLoaiVe = "VL01";
            } else if (cuLy.compareTo(new BigDecimal("15")) >= 0 && cuLy.compareTo(new BigDecimal("25")) <= 0) {
                maLoaiVe = "VL02";
            } else {
                maLoaiVe = "VL03";
            }

            LoaiVe loaiVe = loaiVeService.timTheoMa(maLoaiVe);
            if (loaiVe != null) {
                tenLoaiVe = loaiVe.getTenLoaiVe();
                donGia = loaiVe.getDonGia();
            }

            result.put("success", true);
            result.put("maLoaiVe", maLoaiVe);
            result.put("tenLoaiVe", tenLoaiVe);
            result.put("donGia", donGia);
            result.put("display", maLoaiVe + " - " + tenLoaiVe + " (Giá: " + donGia + "đ)");

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }

        return result;
    }

    // =========================
    // LƯU
    // =========================
    @PostMapping("/luu")
    public String luu(
            @RequestParam(value = "maVe", required = false) String maVe,
            @RequestParam(value = "loaiKhach", required = false) String loaiKhach,
            @RequestParam(value = "maKH", required = false) String maKH,
            @RequestParam(value = "maLoaiVe", required = false) String maLoaiVe,
            @RequestParam(value = "maTuyen", required = false) String maTuyen,
            @RequestParam(value = "ngayMua", required = false) String ngayMuaStr,
            Model model
    ) {

        System.out.println("===== BAT DAU LUU VE =====");
        System.out.println("MaVe: " + maVe);
        System.out.println("LoaiKhach: " + loaiKhach);
        System.out.println("MaKH: " + maKH);
        System.out.println("MaLoaiVe (tu form): " + maLoaiVe);
        System.out.println("MaTuyen (tu form): " + maTuyen);
        System.out.println("NgayMua: " + ngayMuaStr);

        try {
            Ve ve = new Ve();

            // Mã vé
            if (maVe != null && !maVe.isEmpty()) {
                ve.setMaVe(maVe);
            } else {
                ve.setMaVe(veService.sinhMaVe());
            }

            // Khách hàng
            if (maKH != null && !maKH.isEmpty()) {
                ve.setMaKH(maKH);
            } else {
                ve.setMaKH(null);
            }

            // Ngày mua
            if (ngayMuaStr != null && !ngayMuaStr.isEmpty()) {
                LocalDate date = LocalDate.parse(ngayMuaStr, DateTimeFormatter.ISO_LOCAL_DATE);
                ve.setNgayMua(date.atStartOfDay());
            } else {
                ve.setNgayMua(LocalDateTime.now());
            }

            // =========================================
            // XỬ LÝ LOGIC THEO LOẠI KHÁCH
            // =========================================
            if (loaiKhach == null) {
                // Nếu không có loại khách, báo lỗi
                System.out.println("===== LOI: CHUA CHON LOAI KHACH");
                model.addAttribute("error", "Vui lòng chọn loại khách hàng");
                model.addAttribute("danhSach", veService.layTatCa());
                return "ve/danh-sach";
            }

            switch (loaiKhach) {
                case "VANG_LAI":
                    // Vãng lai: bắt buộc có tuyến
                    if (maTuyen == null || maTuyen.isEmpty()) {
                        System.out.println("===== LOI: VANG_LAI PHAI CO TUYEN");
                        model.addAttribute("error", "Vãng lai phải chọn tuyến");
                        model.addAttribute("danhSach", veService.layTatCa());
                        return "ve/danh-sach";
                    }
                    ve.setMaTuyen(maTuyen);

                    // Lấy maLoaiVe từ form, nếu rỗng thì tự tính từ tuyến
                    if (maLoaiVe != null && !maLoaiVe.isEmpty()) {
                        ve.setMaLoaiVe(maLoaiVe);
                        System.out.println("===== DUNG maLoaiVe TU FORM: " + maLoaiVe);
                    } else {
                        // Tự tính từ tuyến
                        TuyenXe tuyen = tuyenXeService.timTheoMa(maTuyen);
                        if (tuyen != null) {
                            BigDecimal cuLy = tuyen.getCuLy();
                            if (cuLy.compareTo(new BigDecimal("15")) < 0) {
                                ve.setMaLoaiVe("VL01");
                            } else if (cuLy.compareTo(new BigDecimal("15")) >= 0 && cuLy.compareTo(new BigDecimal("25")) <= 0) {
                                ve.setMaLoaiVe("VL02");
                            } else {
                                ve.setMaLoaiVe("VL03");
                            }
                            System.out.println("===== TU TINH MA LOAI VE: " + ve.getMaLoaiVe());
                        } else {
                            ve.setMaLoaiVe(null);
                        }
                    }
                    break;

                case "HSSV":
                    // HSSV: KHÔNG có tuyến, maLoaiVe = VL04
                    ve.setMaTuyen(null);
                    ve.setMaLoaiVe("VL04");
                    System.out.println("===== HSSV: maLoaiVe=VL04, maTuyen=null");
                    break;

                case "VE_TAP":
                    // Vé tập: KHÔNG có tuyến, maLoaiVe = VT01
                    ve.setMaTuyen(null);
                    ve.setMaLoaiVe("VT01");
                    System.out.println("===== VE_TAP: maLoaiVe=VT01, maTuyen=null");
                    break;

                default:
                    ve.setMaLoaiVe(null);
                    ve.setMaTuyen(null);
                    System.out.println("===== LOI: LOAI KHACH KHONG HOP LE");
                    break;
            }

            // =========================================
            // KIỂM TRA DỮ LIỆU TRƯỚC KHI LƯU
            // =========================================
            if (ve.getMaLoaiVe() == null) {
                System.out.println("===== LOI: maLoaiVe BI NULL");
                model.addAttribute("error", "Không xác định được loại vé");
                model.addAttribute("danhSach", veService.layTatCa());
                return "ve/danh-sach";
            }

            System.out.println("===== VE SAU KHI XU LY =====");
            System.out.println("MaVe: " + ve.getMaVe());
            System.out.println("MaLoaiVe: " + ve.getMaLoaiVe());
            System.out.println("MaKH: " + ve.getMaKH());
            System.out.println("MaTuyen: " + ve.getMaTuyen());
            System.out.println("NgayMua: " + ve.getNgayMua());
            System.out.println("=============================");

            veService.luu(ve);

            nhatKyService.ghiLog(
                    "Quản lý vé",
                    "Vé",
                    ve.getMaVe(),
                    "Thêm hoặc cập nhật vé"
            );

            System.out.println("===== LUU VE THANH CONG =====");

        } catch (Exception e) {
            System.out.println("===== LOI KHI LUU VE =====");
            e.printStackTrace();
        }

        model.addAttribute(
                "danhSach",
                veService.layTatCa()
        );

        return "ve/danh-sach";
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
            veService.xoa(ma);

            nhatKyService.ghiLog(
                    "Quản lý vé",
                    "Vé",
                    ma,
                    "Xoá vé"
            );

            System.out.println("===== XOA VE THANH CONG =====");

        } catch (Exception e) {
            System.out.println("===== LOI KHI XOA VE =====");
            e.printStackTrace();
        }

        model.addAttribute(
                "danhSach",
                veService.layTatCa()
        );

        return "ve/danh-sach";
    }

}