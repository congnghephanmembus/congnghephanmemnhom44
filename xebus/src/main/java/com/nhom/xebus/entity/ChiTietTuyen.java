package com.nhom.xebus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ChiTietTuyen", schema = "dbo")
@Data
@IdClass(ChiTietTuyenId.class)
public class ChiTietTuyen {

    @Id
    @Column(name = "MaTuyen")
    private String maTuyen;

    @Id
    @Column(name = "MaTram")
    private String maTram;

    @Column(name = "ThuTuTram")
    private Integer thuTuTram;
}