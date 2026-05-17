package com.nhom.xebus.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Ve", schema = "dbo")
@Data
public class Ve {

    @Id
    @Column(name = "MaVe")
    private String maVe;

    @Column(name = "MaKH")
    private String maKH;

    @Column(name = "MaLoaiVe")
    private String maLoaiVe;

    @Column(name = "MaTuyen")
    private String maTuyen;

    @Column(name = "NgayMua")
    private LocalDateTime ngayMua;
}