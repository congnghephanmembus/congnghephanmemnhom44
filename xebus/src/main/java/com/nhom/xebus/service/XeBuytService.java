package com.nhom.xebus.service;

import com.nhom.xebus.entity.XeBuyt;
import com.nhom.xebus.repository.XeBuytRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XeBuytService {

    private final XeBuytRepository xeBuytRepository;

    public XeBuytService(XeBuytRepository xeBuytRepository) {
        this.xeBuytRepository = xeBuytRepository;
    }

    public List<XeBuyt> layTatCa() {
        return xeBuytRepository.findAll();
    }

    public XeBuyt timTheoMa(String ma) {
        return xeBuytRepository.findById(ma).orElse(null);
    }

    public void luu(XeBuyt xe) {
        xeBuytRepository.save(xe);
    }

    public void xoa(String ma) {
        xeBuytRepository.deleteById(ma);
    }
}