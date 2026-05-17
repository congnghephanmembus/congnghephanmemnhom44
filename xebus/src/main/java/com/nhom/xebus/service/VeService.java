package com.nhom.xebus.service;

import com.nhom.xebus.entity.Ve;
import com.nhom.xebus.repository.VeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeService {

    @Autowired
    private VeRepository veRepository;

    public List<Ve> layTatCa() {
        return veRepository.findAll();
    }

    public void luu(Ve ve) {
        veRepository.save(ve);
    }

    public Ve timTheoMa(String ma) {
        return veRepository.findById(ma).orElse(null);
    }

    public void xoa(String ma) {
        veRepository.deleteById(ma);
    }
}