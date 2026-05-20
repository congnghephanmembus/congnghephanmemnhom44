package com.nhom.xebus.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChiTietTuyenId implements Serializable {

    private String maTuyen;
    private String maTram;
}