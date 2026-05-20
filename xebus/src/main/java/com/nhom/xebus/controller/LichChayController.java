package com.nhom.xebus.controller;

import com.nhom.xebus.entity.LichChay;
import com.nhom.xebus.repository.LichChayRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lich")
public class LichChayController {

    @Autowired
    private LichChayRepository lichChayRepository;

    @GetMapping("/{ma}")
    public String xemChiTietLich(@PathVariable("ma") String ma,
                                 Model model) {

        LichChay lich = lichChayRepository
                .findById(ma)
                .orElse(null);

        if (lich == null) {
            return "redirect:/chuyen";
        }

        model.addAttribute("lich", lich);

        return "chuyen/chi-tiet";
    }
}