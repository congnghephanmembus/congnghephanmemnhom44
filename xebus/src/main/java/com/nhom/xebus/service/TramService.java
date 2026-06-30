package com.nhom.xebus.service;

import com.nhom.xebus.entity.Tram;
import com.nhom.xebus.repository.TramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TramService {

    @Autowired
    private TramRepository tramRepository;

    public List<Tram> layTatCa() {
        return tramRepository.findAll();
    }

    public Tram timTheoMa(String maTram) {
        return tramRepository.findById(maTram).orElse(null);
    }
}