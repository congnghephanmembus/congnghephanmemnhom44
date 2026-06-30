package com.nhom.xebus.service;

import com.nhom.xebus.entity.LoaiVe;
import com.nhom.xebus.repository.LoaiVeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiVeService {

    @Autowired
    private LoaiVeRepository loaiVeRepository;

    public List<LoaiVe> layTatCa() {
        return loaiVeRepository.findAll();
    }

    public LoaiVe timTheoMa(String maLoaiVe) {
        return loaiVeRepository.findById(maLoaiVe).orElse(null);
    }
}