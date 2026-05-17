package com.nhom.xebus.service;

import com.nhom.xebus.entity.TuyenXe;
import com.nhom.xebus.repository.TuyenXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TuyenXeService {

    @Autowired
    private TuyenXeRepository tuyenXeRepository;

    public List<TuyenXe> layTatCa() {
        return tuyenXeRepository.findAll();
    }

    public void luu(TuyenXe tuyenXe) {
        tuyenXeRepository.save(tuyenXe);
    }

    public void them(TuyenXe tuyenXe) {
        tuyenXeRepository.save(tuyenXe);
    }

    public TuyenXe timTheoMa(String ma) {
        return tuyenXeRepository.findById(ma).orElse(null);
    }

    public void xoa(String ma) {
        tuyenXeRepository.deleteById(ma);
    }
}