package com.nhom.xebus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TramDung", schema = "dbo")
@Data
public class Tram {

    @Id
    @Column(name = "MaTram")
    private String maTram;

    @Column(name = "TenTram")
    private String tenTram;

    @Column(name = "DiaChi")
    private String diaChi;
}