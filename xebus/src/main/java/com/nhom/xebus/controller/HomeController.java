package com.nhom.xebus.controller;

import com.nhom.xebus.entity.NhatKyHeThong;
import com.nhom.xebus.entity.TuyenXe;
import com.nhom.xebus.service.ChuyenXeService;
import com.nhom.xebus.service.NhatKyService;
import com.nhom.xebus.service.TuyenXeService;
import com.nhom.xebus.service.VeService;
import com.nhom.xebus.service.XeBuytService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ChuyenXeService chuyenXeService;

    @Autowired
    private VeService veService;

    @Autowired
    private XeBuytService xeBuytService;

    @Autowired
    private TuyenXeService tuyenXeService;

    @Autowired
    private NhatKyService nhatKyService;

    @GetMapping("/")
    public String home(Model model) {
        // Lấy dữ liệu thực từ hệ thống
        // Tổng số chuyến xe
        long tongChuyen = chuyenXeService.layTatCa().size();
        model.addAttribute("tongChuyen", tongChuyen);

        // Tổng số vé đã bán
        long tongVe = veService.demTongSoVe();
        model.addAttribute("tongVe", tongVe);

        // Tổng doanh thu
        BigDecimal doanhThu = veService.tinhTongDoanhThu();
        model.addAttribute("doanhThu", doanhThu != null ? doanhThu : BigDecimal.ZERO);

        // Số xe đang bảo trì
        long xeBaoTri = xeBuytService.demXeDangBaoTri();
        model.addAttribute("xeBaoTri", xeBaoTri);

        // Danh sách tuyến xe
        List<TuyenXe> danhSachTuyen = tuyenXeService.layTatCa();
        model.addAttribute("danhSachTuyen", danhSachTuyen);

        // Thông báo hệ thống (hoạt động gần đây)
        List<NhatKyHeThong> hoatDongGanDay = nhatKyService.layHoatDongGanDay();
        model.addAttribute("hoatDongGanDay", hoatDongGanDay);

        return "index";
    }
}
