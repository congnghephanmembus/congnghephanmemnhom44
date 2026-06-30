CREATE DATABASE QuanLyVeXeBuytNoiDo;
GO

USE QuanLyVeXeBuytNoiDo;
GO

-- 1. TuyenXe
CREATE TABLE TuyenXe (
    MaTuyen     VARCHAR(10)     PRIMARY KEY,
    TenTuyen    NVARCHAR(100)   NOT NULL,
    DiemDau     NVARCHAR(100),
    DiemCuoi    NVARCHAR(100),
    CuLy        DECIMAL(8,2),
    TrangThai   NVARCHAR(30)
);

-- 2. TramDung
CREATE TABLE TramDung (
    MaTram      VARCHAR(10)     PRIMARY KEY,
    TenTram     NVARCHAR(100)   NOT NULL,
    DiaChi      NVARCHAR(255)
);

-- 3. ChiTietTuyen
CREATE TABLE ChiTietTuyen (
    MaTuyen     VARCHAR(10),
    MaTram      VARCHAR(10),
    ThuTuTram   INT             NOT NULL,
    PRIMARY KEY (MaTuyen, MaTram),
    FOREIGN KEY (MaTuyen) REFERENCES TuyenXe(MaTuyen),
    FOREIGN KEY (MaTram)  REFERENCES TramDung(MaTram)
);
ALTER TABLE ChiTietTuyen ADD CONSTRAINT UQ_ChiTietTuyen_ThuTu UNIQUE (MaTuyen, ThuTuTram);

-- 4. XeBuyt
CREATE TABLE XeBuyt (
    MaXe        VARCHAR(10)     PRIMARY KEY,
    BienSo      VARCHAR(20)     NOT NULL UNIQUE,
    SucChua     INT             NOT NULL,
    LoaiXe      NVARCHAR(50),
    TrangThai   NVARCHAR(50)
);

-- 5. NhanVien
CREATE TABLE NhanVien (
    MaNV        VARCHAR(10)     PRIMARY KEY,
    CCCD        CHAR(12)        UNIQUE,
    HoTen       NVARCHAR(100)   NOT NULL,
    NgaySinh    DATE,
    GioiTinh    NVARCHAR(10),
    SoDienThoai VARCHAR(15),
    Email       VARCHAR(100),
    ChucVu      NVARCHAR(50),
    Luong       DECIMAL(12,2)
);

-- 6. TaiKhoan
CREATE TABLE TaiKhoan (
    MaTaiKhoan  VARCHAR(10)     PRIMARY KEY,
    TenDangNhap VARCHAR(50)     NOT NULL UNIQUE,
    MatKhau     VARCHAR(255)    NOT NULL,
    VaiTro      NVARCHAR(30)    NOT NULL,
    MaNV        VARCHAR(10)     NULL,
    TrangThai   NVARCHAR(20)    NOT NULL DEFAULT N'Hoạt động',
    NgayTao     DATETIME        NOT NULL DEFAULT GETDATE(),
    NguoiCap    VARCHAR(10)     NULL,
    FOREIGN KEY (MaNV)     REFERENCES NhanVien(MaNV),
    FOREIGN KEY (NguoiCap) REFERENCES TaiKhoan(MaTaiKhoan),
    CONSTRAINT CK_TaiKhoan_VaiTro
        CHECK (VaiTro IN (N'NhanVienBanVe', N'QuanLy', N'QuanTriVien')),
    CONSTRAINT CK_TaiKhoan_TrangThai
        CHECK (TrangThai IN (N'Hoạt động', N'Bị khoá'))
);

-- 7. LichChay
CREATE TABLE LichChay (
    MaLich      VARCHAR(10)     PRIMARY KEY,
    MaTuyen     VARCHAR(10)     NOT NULL,
    GioBatDau   TIME,
    GioKetThuc  TIME,
    TanSuatPhut INT,
    FOREIGN KEY (MaTuyen) REFERENCES TuyenXe(MaTuyen)
);

-- 8. ChuyenXe
CREATE TABLE ChuyenXe (
    MaChuyen        VARCHAR(10)     PRIMARY KEY,
    MaTuyen         VARCHAR(10)     NOT NULL,
    MaXe            VARCHAR(10)     NOT NULL,
    MaLich          VARCHAR(10)     NULL,
    GioKhoiHanh     DATETIME        NOT NULL,
    GioDenDuKien    DATETIME,
    TrangThaiChuyen NVARCHAR(50),
    FOREIGN KEY (MaTuyen) REFERENCES TuyenXe(MaTuyen),
    FOREIGN KEY (MaXe)    REFERENCES XeBuyt(MaXe),
    FOREIGN KEY (MaLich)  REFERENCES LichChay(MaLich),
    CONSTRAINT CK_ChuyenXe_TrangThai
        CHECK (TrangThaiChuyen IN (N'Chờ khởi hành', N'Đang chạy', N'Hoàn thành', N'Huỷ')),
    CONSTRAINT CK_ChuyenXe_GioDen CHECK (GioDenDuKien > GioKhoiHanh)
);

-- 9. PhanCongNhanVien
CREATE TABLE PhanCongNhanVien (
    MaChuyen    VARCHAR(10),
    MaNV        VARCHAR(10),
    VaiTro      NVARCHAR(30),
    PRIMARY KEY (MaChuyen, MaNV),
    FOREIGN KEY (MaChuyen) REFERENCES ChuyenXe(MaChuyen),
    FOREIGN KEY (MaNV)     REFERENCES NhanVien(MaNV)
);

-- 10. KhachHang
CREATE TABLE KhachHang (
    MaKH        VARCHAR(10)     PRIMARY KEY,
    CCCD        CHAR(12)        UNIQUE,
    HoTen       NVARCHAR(100)   NOT NULL,
    NgaySinh    DATE,
    GioiTinh    NVARCHAR(10),
    SoDienThoai VARCHAR(15),
    Email       VARCHAR(100),
    DiaChi      NVARCHAR(255)
);

-- 11. LoaiVe
CREATE TABLE LoaiVe (
    MaLoaiVe    VARCHAR(10)     PRIMARY KEY,
    TenLoaiVe   NVARCHAR(50)    NOT NULL,
    MoTa        NVARCHAR(255),
    DonGia      DECIMAL(10,2)   NOT NULL
);

-- 12. Ve
CREATE TABLE Ve (
    MaVe        VARCHAR(10)     PRIMARY KEY,
    MaLoaiVe    VARCHAR(10)     NOT NULL,
    MaKH        VARCHAR(10)     NULL,
    MaTuyen     VARCHAR(10)     NULL,
    NgayMua     DATETIME        NOT NULL,
    NgayBatDau  DATE            NULL,
    NgayHetHan  DATE            NULL,
    TrangThaiVe NVARCHAR(50),
    FOREIGN KEY (MaLoaiVe) REFERENCES LoaiVe(MaLoaiVe),
    FOREIGN KEY (MaKH)     REFERENCES KhachHang(MaKH),
    FOREIGN KEY (MaTuyen)  REFERENCES TuyenXe(MaTuyen),
    CONSTRAINT CK_Ve_TrangThai
        CHECK (TrangThaiVe IN (N'Chưa thanh toán', N'Đã thanh toán', N'Đã sử dụng', N'Đã huỷ')),
    CONSTRAINT CK_Ve_LoaiVe_Tuyen CHECK (
        (MaLoaiVe IN ('VL01','VL02','VL03') AND MaTuyen IS NOT NULL) OR
        (MaLoaiVe IN ('VL04','VT01') AND MaTuyen IS NULL)
    ),
    CONSTRAINT CK_Ve_NgayHetHan CHECK (NgayHetHan IS NULL OR NgayBatDau IS NULL OR NgayHetHan > NgayBatDau)
);

-- 13. NhatKyHeThong
CREATE TABLE NhatKyHeThong (
    MaNhatKy        INT           IDENTITY(1,1) PRIMARY KEY,
    ThoiGian        DATETIME      NOT NULL DEFAULT GETDATE(),
    MaTaiKhoan      VARCHAR(10)   NULL,
    ChucNang        NVARCHAR(100) NOT NULL,
    DoiTuongTacDong NVARCHAR(50)  NULL,
    MaDoiTuong      VARCHAR(20)   NULL,
    NoiDungChiTiet  NVARCHAR(500) NULL,
    KetQua          NVARCHAR(20)  NULL,
    GhiChu          NVARCHAR(255) NULL,
    FOREIGN KEY (MaTaiKhoan) REFERENCES TaiKhoan(MaTaiKhoan)
);
GO