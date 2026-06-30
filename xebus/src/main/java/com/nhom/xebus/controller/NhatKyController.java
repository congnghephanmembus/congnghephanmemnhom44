package com.nhom.xebus.controller;

import com.nhom.xebus.entity.NhatKyHeThong;
import com.nhom.xebus.repository.NhatKyHeThongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/nhat-ky")
public class NhatKyController {

    @Autowired
    private NhatKyHeThongRepository nhatKyHeThongRepository;

    @GetMapping
    public String danhSach(Model model) {

        // Sắp xếp theo thời gian giảm dần (mới nhất trước)
        List<NhatKyHeThong> danhSach = nhatKyHeThongRepository.findAll(
                Sort.by(Sort.Direction.DESC, "thoiGian")
        );

        model.addAttribute("danhSach", danhSach);

        return "nhat-ky/index";
    }
}