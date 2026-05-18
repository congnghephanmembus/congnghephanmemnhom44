package com.nhom.xebus.service;

import com.nhom.xebus.entity.ChuyenXe;
import com.nhom.xebus.repository.ChuyenXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChuyenXeService {

    @Autowired
    private ChuyenXeRepository chuyenXeRepository;

    public List<ChuyenXe> layTatCa() {
        return chuyenXeRepository.findAll();
    }

    public void luu(ChuyenXe chuyenXe) {
        chuyenXeRepository.save(chuyenXe);
    }

    public void them(ChuyenXe chuyenXe) {
        chuyenXeRepository.save(chuyenXe);
    }

    public ChuyenXe timTheoMa(String maChuyen) {
        return chuyenXeRepository.findById(maChuyen).orElse(null);
    }

    public void xoa(String maChuyen) {
        chuyenXeRepository.deleteById(maChuyen);
    }
}